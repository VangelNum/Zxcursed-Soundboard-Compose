package com.zxcursedsoundboard.apk.core.presentation

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zxcursedsoundboard.apk.BuildConfig
import com.zxcursedsoundboard.apk.core.data.model.DownloadStatus
import com.zxcursedsoundboard.apk.core.data.model.MediaItems
import com.zxcursedsoundboard.apk.core.data.model.Song
import com.zxcursedsoundboard.apk.core.domain.repository.FileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val fileRepository: FileRepository
) : ViewModel() {
    private var mediaPlayer: MediaPlayer? = null

    private val _currentPositionIndex = MutableStateFlow(-1)
    val currentPositionIndex: StateFlow<Int> = _currentPositionIndex.asStateFlow()

    private val _routeOfPlayingSong = MutableStateFlow("")
    val routeOfPlayingSong = _routeOfPlayingSong.asStateFlow()

    private val _currentSong = MutableStateFlow(Song(-1, -1, -1, -1))
    val currentSong: StateFlow<Song> = _currentSong.asStateFlow()

    private val _duration = MutableStateFlow(0)
    val duration = _duration.asStateFlow()

    private val _currentTimeMedia = MutableSharedFlow<Int>()
    val currentTimeMedia: SharedFlow<Int> = _currentTimeMedia.asSharedFlow()

    private val _looping = MutableStateFlow(false)
    val looping = _looping.asStateFlow()

    private val _downloadStatus = MutableSharedFlow<DownloadStatus>()
    val downloadStatus: SharedFlow<DownloadStatus> = _downloadStatus.asSharedFlow()

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _songList = MutableStateFlow<List<MediaItems>>(emptyList())
    val songList = _songList.asStateFlow()

    fun setMedia(
        index: Int,
        context: Context,
        songRes: Int,
        songName: Int,
        songAuthor: Int,
        songImage: Int,
        listMedia: List<MediaItems>,
        routeOfPlayingSong: String
    ) {
        _routeOfPlayingSong.value = routeOfPlayingSong
        _songList.value = listMedia
        val song = Song(songAuthor, songName, songImage, songRes)
        _currentSong.value = song
        if (index == _currentPositionIndex.value && mediaPlayer != null) {
            togglePlayback()
        } else {
            mediaPlayer?.let {
                it.stop()
                it.release()
            }
            mediaPlayer = MediaPlayer
                .create(context, songRes)
                .apply {
                    setOnCompletionListener { media ->
                        if (_looping.value) {
                            media.start()
                        } else {
                            playNextMedia(context, mediaItemsMain = listMedia, routeOfPlayingSong)
                        }
                    }
                    setOnPreparedListener { media ->
                        media.start()
                        _isPlaying.value = true
                        _currentPositionIndex.value = index
                        _duration.value = media.duration
                    }
                }
        }
        updateCurrentTimePosition()
    }

    private fun togglePlayback() {
        if (mediaPlayer?.isPlaying == true) {
            _isPlaying.value = false
            mediaPlayer?.pause()
        } else {
            _isPlaying.value = true
            mediaPlayer?.start()
        }
    }


    //work only in dispatcher.main
    private fun updateCurrentTimePosition() {
        viewModelScope.launch(Dispatchers.Main) {
            while (_isPlaying.value) {
                val currentPosition = mediaPlayer?.currentPosition ?: 0
                _currentTimeMedia.emit(currentPosition)
            }
        }
    }

    fun toggleLooping() {
        _looping.value = !_looping.value
    }


    fun playNextMedia(context: Context, mediaItemsMain: List<MediaItems>, currentRoute: String) {
        val nextIndex = (_currentPositionIndex.value + 1) % mediaItemsMain.size
        setMedia(
            nextIndex,
            context,
            mediaItemsMain[nextIndex].audioResId,
            mediaItemsMain[nextIndex].songNameRes,
            mediaItemsMain[nextIndex].songAuthor,
            mediaItemsMain[nextIndex].imageRes,
            mediaItemsMain,
            currentRoute
        )
    }

    fun playPreviousMedia(
        context: Context,
        mediaItemsMain: List<MediaItems>,
        currentRoute: String
    ) {
        var newIndex = _currentPositionIndex.value - 1
        if (newIndex < 0) {
            newIndex = mediaItemsMain.size - 1
        }
        setMedia(
            newIndex,
            context,
            mediaItemsMain[newIndex].audioResId,
            mediaItemsMain[newIndex].songNameRes,
            mediaItemsMain[newIndex].songAuthor,
            mediaItemsMain[newIndex].imageRes,
            mediaItemsMain,
            currentRoute
        )
    }

    fun setCurrentTime(position: Int) {
        mediaPlayer?.seekTo(position)
        viewModelScope.launch(Dispatchers.Main) {
            _currentTimeMedia.emit(position)
        }
    }

    fun play() {
        togglePlayback()
        updateCurrentTimePosition()
    }

    fun pause() {
        togglePlayback()
        updateCurrentTimePosition()
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayer?.release()
    }

    fun share(context: Context, resourceId: Int, fileName: String) {
        val mediaFile = context.resources.openRawResourceFd(resourceId)
        val mediaUri = FileProvider.getUriForFile(
            context,
            "${BuildConfig.APPLICATION_ID}.fileprovider",
            File(context.cacheDir, " $fileName.mp3")
        )
        val outputStream = context.contentResolver.openOutputStream(mediaUri)
        mediaFile.createInputStream().use { input ->
            outputStream.use { output ->
                if (output != null) {
                    input.copyTo(output)
                }
            }
        }
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "audio/mp3"
            putExtra(Intent.EXTRA_STREAM, mediaUri)
        }
        val chooser = Intent.createChooser(intent, "Share media file")
        context.startActivity(chooser)
    }

    fun downloadRawFile(context: Context, rawResId: Int, fileName: String) {
        viewModelScope.launch(Dispatchers.Main) {
            _downloadStatus.emit(DownloadStatus.Loading)
            val isSuccess = fileRepository.downloadRawFile(context, rawResId, fileName)
            if (isSuccess) {
                _downloadStatus.emit(DownloadStatus.Success)
            } else {
                _downloadStatus.emit(DownloadStatus.Error("Error, file already exists or permission is required"))
            }
        }
    }

}