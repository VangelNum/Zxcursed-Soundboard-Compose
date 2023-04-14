package com.zxcursedsoundboard.apk.core.presentation

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Environment
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.zxcursedsoundboard.apk.core.common.ResourceFirebase
import com.zxcursedsoundboard.apk.core.data.model.MediaItems
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


class MainViewModel : ViewModel() {

    private val _currentPositionIndex = MutableStateFlow(-1)
    val currentPositionIndex: StateFlow<Int> = _currentPositionIndex.asStateFlow()

    private val _routeOfPlayingSong = MutableStateFlow("")
    val routeOfPlayingSong = _routeOfPlayingSong.asStateFlow()

    private val _currentSong = MutableStateFlow(MediaItems("", "", "", ""))
    val currentSong: StateFlow<MediaItems> = _currentSong.asStateFlow()

    private val _duration = MutableStateFlow(0L)
    val duration = _duration.asStateFlow()

    private val _currentTimeMedia = MutableSharedFlow<Long>()
    val currentTimeMedia: SharedFlow<Long> = _currentTimeMedia.asSharedFlow()

    private val _looping = MutableStateFlow(false)
    val looping = _looping.asStateFlow()

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _songList = MutableStateFlow<List<MediaItems>>(emptyList())
    val songList = _songList.asStateFlow()

    private val _songMain =
        MutableStateFlow<ResourceFirebase<List<MediaItems>>>(ResourceFirebase.Empty())
    val songMain = _songMain.asStateFlow()

    private val _soundsZxcursed =
        MutableStateFlow<ResourceFirebase<List<MediaItems>>>(ResourceFirebase.Empty())
    val soundsZxcursed = _soundsZxcursed.asStateFlow()

    private val _soundsSnail =
        MutableStateFlow<ResourceFirebase<List<MediaItems>>>(ResourceFirebase.Empty())
    val soundsSnail = _soundsSnail.asStateFlow()

    private val _soundsFly =
        MutableStateFlow<ResourceFirebase<List<MediaItems>>>(ResourceFirebase.Empty())
    val soundsFly = _soundsFly.asStateFlow()

    fun getSoundsZxcursed() {
        viewModelScope.launch {
            _soundsZxcursed.value = ResourceFirebase.Loading()
            try {
                val db = Firebase.firestore
                val query = db.collection("audioSound").get().await()
                val items = query.documents.mapNotNull {
                    it.toObject(MediaItems::class.java)
                }
                _soundsZxcursed.value = ResourceFirebase.Success(items)
            } catch (e: Exception) {
                _soundsZxcursed.value = ResourceFirebase.Error(e.message.toString())
            }
        }
    }

    fun getFlySounds() {
        viewModelScope.launch {
            _soundsFly.value = ResourceFirebase.Loading()
            try {
                val db = Firebase.firestore
                val query = db.collection("audioFly").get().await()
                val items = query.documents.mapNotNull {
                    it.toObject(MediaItems::class.java)
                }
                _soundsFly.value = ResourceFirebase.Success(items)
            } catch (e: Exception) {
                _soundsFly.value = ResourceFirebase.Error(e.message.toString())
            }
        }
    }

    fun getSnailSounds() {
        viewModelScope.launch {
            _soundsSnail.value = ResourceFirebase.Loading()
            try {
                val db = Firebase.firestore
                val query = db.collection("audioUlitka").get().await()
                val items = query.documents.mapNotNull {
                    it.toObject(MediaItems::class.java)
                }
                _soundsSnail.value = ResourceFirebase.Success(items)
            } catch (e: Exception) {
                _soundsSnail.value = ResourceFirebase.Error(e.message.toString())
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
                    it.toObject(MediaItems::class.java)
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
        songList: List<MediaItems>,
        context: Context
    ) {
        _songList.value = songList
        val mediaItem = MediaItem.fromUri(songRes)
        _currentSong.value = MediaItems(songAuthor, songName, songImage, songRes)
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
                                playNextMedia(_songList.value, _routeOfPlayingSong.value, context)
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

    fun playNextMedia(songList: List<MediaItems>, currentRoute: String, context: Context) {
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

    fun playNextMediaFavourite(songList: List<MediaItems>, currentRoute: String, context: Context) {
        val nextIndex = (_currentPositionIndex.value + 1) % _songList.value.size
        setMediaFavourite(
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

    private fun setMediaFavourite(
        index: Int,
        songRes: String,
        songName: String,
        songAuthor: String,
        songImage: String,
        routeOfPlayingSong: String,
        songList: List<MediaItems>,
        context: Context
    ) {
        _songList.value = songList
        val mediaItem = MediaItem.fromUri(songRes)
        _currentSong.value = MediaItems(songAuthor, songName, songImage, songRes)
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
                        _currentPositionIndex.value = index - 1
                        _duration.value = player?.duration ?: 0
                    }
                    if (state == Player.STATE_ENDED) {
                        if (_looping.value) {
                            player?.seekTo(0)
                            player?.play()
                        } else {
                            playNextMedia(_songList.value, _routeOfPlayingSong.value, context)
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
        _routeOfPlayingSong.value = routeOfPlayingSong
    }


    fun playPreviousMedia(
        songList: List<MediaItems>,
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

    fun setTimeOfMedia(position: Long) {
        player?.seekTo(position)
        player?.play()
    }

    fun play() {
        togglePlayback()
    }

    fun pause() {
        togglePlayback()
    }

    fun updateSongList(list: List<MediaItems>) {
        _songList.value = list
    }

    fun updatePosition(index: Int, currentDestination: String?) {
        if (_currentPositionIndex.value > index && currentDestination == _routeOfPlayingSong.value) {
            _currentPositionIndex.value--
        }
    }

    override fun onCleared() {
        super.onCleared()
        player?.release()
    }

    fun downloadFile(url: String, fileName: String, context: Context) {
        val downloadManager = context.getSystemService(DownloadManager::class.java)
        val request = DownloadManager.Request(url.toUri())
            .setMimeType("audio/mp3")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle("$fileName.mp3")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "$fileName.mp3")
        downloadManager.enqueue(request)
    }

    private var onCompleteReceiver: BroadcastReceiver? = null

    fun share(context: Context, audioUrl: String, fileName: String) {
        onCompleteReceiver?.let {
            context.unregisterReceiver(it)
        }

        val downloadManager = context.getSystemService(DownloadManager::class.java)
        val request = DownloadManager.Request(audioUrl.toUri())
            .setMimeType("audio/mp3")
            .setTitle("$fileName.mp3")
            .setDescription("Downloading $fileName")

        val downloadId = downloadManager.enqueue(request)

        onCompleteReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val fileUri = downloadManager.getUriForDownloadedFile(downloadId)
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "audio/mp3"
                    putExtra(Intent.EXTRA_STREAM, fileUri)
                }
                context?.startActivity(Intent.createChooser(shareIntent, "Share MP3"))
                context?.unregisterReceiver(this)
                onCompleteReceiver = null
            }
        }
        val filter = IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        context.registerReceiver(onCompleteReceiver, filter)
    }

}