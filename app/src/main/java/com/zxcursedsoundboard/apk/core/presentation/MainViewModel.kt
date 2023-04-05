package com.zxcursedsoundboard.apk.core.presentation

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.zxcursedsoundboard.apk.BuildConfig
import com.zxcursedsoundboard.apk.core.common.ResourceFirebase
import com.zxcursedsoundboard.apk.core.data.model.DownloadStatus
import com.zxcursedsoundboard.apk.core.domain.repository.FileRepository
import com.zxcursedsoundboard.apk.feature_test.ItemsFirebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
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

    private val _currentSong = MutableStateFlow(ItemsFirebase("", "", "", ""))
    val currentSong: StateFlow<ItemsFirebase> = _currentSong.asStateFlow()

    private val _duration = MutableStateFlow(0)
    val duration = _duration.asStateFlow()

    private val _currentTimeMedia = MutableSharedFlow<Int>(0)
    val currentTimeMedia: SharedFlow<Int> = _currentTimeMedia.asSharedFlow()

    private val _looping = MutableStateFlow(false)
    val looping = _looping.asStateFlow()

    private val _downloadStatus = MutableSharedFlow<DownloadStatus>()
    val downloadStatus: SharedFlow<DownloadStatus> = _downloadStatus.asSharedFlow()

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _songList = MutableStateFlow<List<ItemsFirebase>>(emptyList())
    val songList = _songList.asStateFlow()

    private val _song =
        MutableStateFlow<ResourceFirebase<List<ItemsFirebase>>>(ResourceFirebase.Empty())
    val song = _song.asStateFlow()

    fun getAudio() {
        viewModelScope.launch {
            _song.value = ResourceFirebase.Loading()
            try {
                val db = Firebase.firestore
                val query = db.collection("audio").get().await()
                val items = query.documents.mapNotNull {
                    it.toObject(ItemsFirebase::class.java)
                }
                _song.value = ResourceFirebase.Success(items)
            } catch (e: Exception) {
                _song.value = ResourceFirebase.Error(e.message.toString())
            }
        }
    }

    fun setMedia(
        index: Int,
        songRes: String,
        songName: String,
        songAuthor: String,
        songImage: String,
        routeOfPlayingSong: String,
        songList: List<ItemsFirebase>
    ) {
        _songList.value = songList
        _routeOfPlayingSong.value = routeOfPlayingSong
        val song = ItemsFirebase(songAuthor, songName, songImage, songRes)
        _currentSong.value = song
        if (index == _currentPositionIndex.value && mediaPlayer != null) {
            togglePlayback()
        } else {
            mediaPlayer?.let {
                it.stop()
                it.release()
            }
            mediaPlayer = MediaPlayer().apply {
                reset()
                setDataSource(songRes)
                prepareAsync()
                setOnCompletionListener { media ->
                    if (_looping.value) {
                        media.start()
                    } else {
                        val nextIndex = index + 1
                        if (nextIndex < songList.size) {
                            val nextMediaItem = songList[nextIndex]
                            setMedia(
                                nextIndex,
                                nextMediaItem.audio,
                                nextMediaItem.name,
                                nextMediaItem.author,
                                nextMediaItem.image,
                                routeOfPlayingSong,
                                songList
                            )
                        } else {
                            _isPlaying.value = false
                        }
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
        if (mediaPlayer!!.isPlaying) {
            mediaPlayer?.pause()
            _isPlaying.value = false
        } else {
            mediaPlayer?.start()
            _isPlaying.value = true
        }
    }

    private fun updateCurrentTimePosition() {
        viewModelScope.launch(Dispatchers.IO) {
            while (_isPlaying.value) {
                val currentPosition = mediaPlayer?.currentPosition ?: 0
                _currentTimeMedia.emit(currentPosition)
            }
        }
    }

    fun toggleLooping() {
        _looping.value = !_looping.value
    }

    fun playNextMedia(songList: List<ItemsFirebase>, currentRoute: String) {
        val nextIndex = (_currentPositionIndex.value + 1) % songList.size
        setMedia(
            nextIndex,
            songList[nextIndex].audio,
            songList[nextIndex].name,
            songList[nextIndex].author,
            songList[nextIndex].image,
            currentRoute,
            songList
        )
    }

    fun playPreviousMedia(
        songList: List<ItemsFirebase>,
        currentRoute: String
    ) {
        var newIndex = _currentPositionIndex.value - 1
        if (newIndex < 0) {
            newIndex = songList.size - 1
        }
        setMedia(
            newIndex,
            songList[newIndex].audio,
            songList[newIndex].name,
            songList[newIndex].author,
            songList[newIndex].image,
            currentRoute,
            songList
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