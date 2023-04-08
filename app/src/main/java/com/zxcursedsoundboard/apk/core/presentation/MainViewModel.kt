package com.zxcursedsoundboard.apk.core.presentation

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.zxcursedsoundboard.apk.BuildConfig
import com.zxcursedsoundboard.apk.core.common.ResourceFirebase
import com.zxcursedsoundboard.apk.core.data.model.DownloadStatus
import com.zxcursedsoundboard.apk.core.domain.repository.FileRepository
import com.zxcursedsoundboard.apk.feature_test.ItemsFirebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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
    private val fileRepository: FileRepository,
) : ViewModel() {

    private val _currentPositionIndex = MutableStateFlow(-1)
    val currentPositionIndex: StateFlow<Int> = _currentPositionIndex.asStateFlow()

    private val _routeOfPlayingSong = MutableStateFlow("")
    val routeOfPlayingSong = _routeOfPlayingSong.asStateFlow()

    private val _currentSong = MutableStateFlow(ItemsFirebase("", "", "", ""))
    val currentSong: StateFlow<ItemsFirebase> = _currentSong.asStateFlow()

    private val _duration = MutableStateFlow(0L)
    val duration = _duration.asStateFlow()

    private val _currentTimeMedia = MutableSharedFlow<Long>()
    val currentTimeMedia: SharedFlow<Long> = _currentTimeMedia.asSharedFlow()

    private val _looping = MutableStateFlow(false)
    val looping = _looping.asStateFlow()

    private val _downloadStatus = MutableSharedFlow<DownloadStatus>()
    val downloadStatus: SharedFlow<DownloadStatus> = _downloadStatus.asSharedFlow()

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _songList = MutableStateFlow<List<ItemsFirebase>>(emptyList())
    val songList = _songList.asStateFlow()

    private val _songMain =
        MutableStateFlow<ResourceFirebase<List<ItemsFirebase>>>(ResourceFirebase.Empty())
    val songMain = _songMain.asStateFlow()

    private val _soundsZxcursed =
        MutableStateFlow<ResourceFirebase<List<ItemsFirebase>>>(ResourceFirebase.Empty())
    val soundsZxcursed = _soundsZxcursed.asStateFlow()

    fun getSoundsZxcursed() {
        viewModelScope.launch {
            _soundsZxcursed.value = ResourceFirebase.Loading()
            try {
                val db = Firebase.firestore
                val query = db.collection("audioSound").get().await()
                val items = query.documents.mapNotNull {
                    it.toObject(ItemsFirebase::class.java)
                }
                _soundsZxcursed.value = ResourceFirebase.Success(items)
            } catch (e: Exception) {
                _soundsZxcursed.value = ResourceFirebase.Error(e.message.toString())
            }
        }
    }

    fun getAudioMain() {
        viewModelScope.launch {
            _songMain.value = ResourceFirebase.Loading()
            try {
                val db = Firebase.firestore
                val query = db.collection("audio").get().await()
                val items = query.documents.mapNotNull {
                    it.toObject(ItemsFirebase::class.java)
                }
                _songMain.value = ResourceFirebase.Success(items)
            } catch (e: Exception) {
                _songMain.value = ResourceFirebase.Error(e.message.toString())
            }
        }
    }

    private var player: ExoPlayer? = null

    fun setMedia(
        index: Int,
        songRes: String,
        songName: String,
        songAuthor: String,
        songImage: String,
        routeOfPlayingSong: String,
        songList: List<ItemsFirebase>,
        context: Context
    ) {
        _songList.value = songList
        val mediaItem = MediaItem.fromUri(songRes)
        _currentSong.value = ItemsFirebase(songAuthor, songName, songImage, songRes)
        if (index == _currentPositionIndex.value && player != null && _routeOfPlayingSong.value == routeOfPlayingSong) {
            togglePlayback()
        } else {
            player?.let {
                it.stop()
                it.release()
            }
            player = ExoPlayer.Builder(context).build().apply {
                setMediaItem(mediaItem)
                prepare()
                play()
                addListener(object : Player.Listener {
                    override fun onPlaybackStateChanged(state: Int) {
                        if (state == Player.STATE_READY || state == Player.STATE_BUFFERING) {
                            _isPlaying.value = true
                            _currentPositionIndex.value = index
                            _duration.value = player?.duration ?: 0
                        }
                        if (state == Player.STATE_ENDED) {
                            if (_looping.value) {
                                player?.seekTo(0)
                                player?.play()
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
                                        songList,
                                        context
                                    )
                                } else {
                                    _isPlaying.value = false
                                }
                            }
                        }
                    }

                    override fun onPlayerError(error: PlaybackException) {
                        _isPlaying.value = false
                    }

                    override fun onIsPlayingChanged(isPlaying: Boolean) {
                        viewModelScope.launch(Dispatchers.Main) {
                            while (isPlaying) {
                                val currentPosition = player?.currentPosition ?: 0
                                _currentTimeMedia.emit(currentPosition)
                                delay(16)
                            }
                        }
                    }
                })

            }
        }
        _routeOfPlayingSong.value = routeOfPlayingSong
    }


    private fun togglePlayback() {
        if (player!!.isPlaying) {
            player!!.pause()
            _isPlaying.value = false
        } else {
            player!!.play()
            _isPlaying.value = true
        }
    }

    fun toggleLooping() {
        _looping.value = !_looping.value
    }

    fun playNextMedia(songList: List<ItemsFirebase>, currentRoute: String, context: Context) {
        val nextIndex = (_currentPositionIndex.value + 1) % songList.size
        setMedia(
            nextIndex,
            songList[nextIndex].audio,
            songList[nextIndex].name,
            songList[nextIndex].author,
            songList[nextIndex].image,
            currentRoute,
            songList,
            context
        )
    }

    fun playPreviousMedia(
        songList: List<ItemsFirebase>,
        currentRoute: String,
        context: Context
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
            songList,
            context
        )
    }

    fun setCurrentTime(position: Long) {
        player?.seekTo(position)
        player?.play()
    }

    fun play() {
        togglePlayback()
    }

    fun pause() {
        togglePlayback()
    }

    override fun onCleared() {
        super.onCleared()
        player?.release()
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