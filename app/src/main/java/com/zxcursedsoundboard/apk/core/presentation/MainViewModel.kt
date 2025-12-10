package com.zxcursedsoundboard.apk.core.presentation

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.os.Environment
import androidx.annotation.OptIn
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.cache.CacheDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.session.MediaSession
import androidx.media3.ui.PlayerNotificationManager
import com.zxcursedsoundboard.apk.R
import com.zxcursedsoundboard.apk.core.common.ResourceFirebase
import com.zxcursedsoundboard.apk.core.data.model.MediaItems
import com.zxcursedsoundboard.apk.feature_alwayswannafly.domain.FlyRepository
import com.zxcursedsoundboard.apk.feature_main.domain.ZxcursedMainRepository
import com.zxcursedsoundboard.apk.feature_snail.domain.SnailRepository
import com.zxcursedsoundboard.apk.feature_sounds_zxcursed.domain.ZxcursedSoundsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val snailRepository: SnailRepository,
    private val flyRepository: FlyRepository,
    private val zxcursedMainRepository: ZxcursedMainRepository,
    private val zxcursedSoundsRepository: ZxcursedSoundsRepository,
    @SuppressLint("UnsafeOptInUsageError") private val cacheDataSourceFactory: CacheDataSource.Factory
) : ViewModel() {

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

    private var positionUpdateJob: Job? = null

    fun getSoundsZxcursed() {
        viewModelScope.launch {
            zxcursedSoundsRepository.getZxcursedSounds().collect {
                _soundsZxcursed.value = it
            }
        }
    }

    fun getAudioMain() {
        viewModelScope.launch {
            zxcursedMainRepository.getZxcursedMain().collect {
                _songMain.value = it
            }
        }
    }

    fun getFlySounds() {
        viewModelScope.launch {
            flyRepository.getFlySounds().collect {
                _soundsFly.value = it
            }
        }
    }

    fun getSnailSounds() {
        viewModelScope.launch {
            snailRepository.getSnailSounds().collect {
                _soundsSnail.value = it
            }
        }
    }


    private var player: ExoPlayer? = null

    @UnstableApi
    private var notificationManager: PlayerNotificationManager? = null
    private var mediaSession: MediaSession? = null
    private var broadcastReceiver: BroadcastReceiver? = null

    @OptIn(UnstableApi::class)
    private fun initDeviceNotification(context: Context) {
        if (mediaSession == null) {
            mediaSession = MediaSession.Builder(context, player!!).build()
        }
        notificationManager = PlayerNotificationManager.Builder(
            context,
            1,
            "my_channel_id"
        )
            .setChannelNameResourceId(R.string.app_name)
            .setChannelDescriptionResourceId(R.string.app_name)
            .setNotificationListener(object : PlayerNotificationManager.NotificationListener {
                override fun onNotificationPosted(
                    notificationId: Int,
                    notification: android.app.Notification,
                    ongoing: Boolean
                ) {
                    super.onNotificationPosted(notificationId, notification, ongoing)
                }
            })
            .build()
        notificationManager?.setPlayer(player)
        notificationManager?.setMediaSessionToken(mediaSession!!.platformToken)
    }

    fun setMedia(
        index: Int,
        songRes: String,
        songName: String,
        songAuthor: String,
        songImage: String,
        routeOfPlayingSong: String,
        songList: List<MediaItems>,
        context: Context,
        fromFavourite: Boolean? = false
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
            stopPositionUpdates()
            player = ExoPlayer.Builder(context)
                .setMediaSourceFactory(
                    DefaultMediaSourceFactory(context).setDataSourceFactory(cacheDataSourceFactory)
                )
                .build()
                .apply {
                setMediaItem(mediaItem)
                prepare()
                play()

                addListener(object : Player.Listener {
                    override fun onPlaybackStateChanged(state: Int) {
                        if (state == Player.STATE_READY) { // STATE_BUFFERING можно убрать, чтобы isPlaying не был true во время буферизации
                            _isPlaying.value = player?.isPlaying ?: false
                            if (fromFavourite == true) {
                                _currentPositionIndex.value = index - 1
                            } else {
                                _currentPositionIndex.value = index
                            }
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
                        stopPositionUpdates()
                    }

                    @SuppressLint("RestrictedApi")
                    override fun onIsPlayingChanged(isPlaying: Boolean) {
                        _isPlaying.value = isPlaying
                        if (isPlaying) {
                            startPositionUpdates()
                        } else {
                            stopPositionUpdates()
                        }
                    }
                })
            }
        }
        initDeviceNotification(context)
        _routeOfPlayingSong.value = routeOfPlayingSong
    }

    private fun startPositionUpdates() {
        stopPositionUpdates() // На всякий случай останавливаем предыдущую задачу
        positionUpdateJob = viewModelScope.launch {
            while (true) {
                val currentPosition = player?.currentPosition ?: 0
                _currentTimeMedia.emit(currentPosition)
                delay(250) // Обновляем 4 раза в секунду, этого достаточно для UI
            }
        }
    }

    private fun stopPositionUpdates() {
        positionUpdateJob?.cancel()
        positionUpdateJob = null
    }

    fun playNextMedia(
        songList: List<MediaItems>,
        currentRoute: String,
        context: Context,
        fromFavourite: Boolean? = false
    ) {
        if (songList.isNotEmpty()) {
            val nextIndex = (_currentPositionIndex.value + 1) % songList.size
            setMedia(
                nextIndex,
                songList[nextIndex].audio,
                songList[nextIndex].name,
                songList[nextIndex].author,
                songList[nextIndex].image,
                currentRoute,
                songList,
                context,
                fromFavourite
            )
        }
    }


    fun playPreviousMedia(
        songList: List<MediaItems>,
        currentRoute: String,
        context: Context
    ) {
        if (songList.isNotEmpty()) {
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
    }

    fun setTimeOfMedia(position: Long) {
        player?.seekTo(position)
    }

    private fun togglePlayback() {
        if (player?.isPlaying == true) {
            player!!.pause()
            _isPlaying.value = false
        } else {
            player?.play()
            _isPlaying.value = true
        }
    }

    fun toggleLooping() {
        _looping.value = !_looping.value
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

    @UnstableApi
    fun closeNotification() {
        _currentSong.value.author = ""
        mediaSession?.release()
        mediaSession = null
        notificationManager?.setPlayer(null)
        player?.release()
        player = null
    }

    @UnstableApi
    override fun onCleared() {
        super.onCleared()
        stopPositionUpdates()
        mediaSession?.release()
        mediaSession = null
        notificationManager?.setPlayer(null)
        player?.release()
        player = null
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


    fun share(context: Context, audioUrl: String, fileName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val safeFileName = "${fileName.replace("[^a-zA-Z0-9а-яА-Я .]".toRegex(), "_")}.mp3"
                val file = File(context.cacheDir, safeFileName)

                val url = URL(audioUrl)
                val connection = url.openConnection()
                connection.connect()

                val inputStream = connection.getInputStream()
                val outputStream = FileOutputStream(file)

                val buffer = ByteArray(1024)
                var len: Int
                while (inputStream.read(buffer).also { len = it } > 0) {
                    outputStream.write(buffer, 0, len)
                }
                outputStream.close()
                inputStream.close()

                withContext(Dispatchers.Main) {
                    shareFileUsingProvider(context, file)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun shareFileUsingProvider(context: Context, file: File) {
        try {
            val uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                file
            )

            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "audio/mpeg"
                putExtra(Intent.EXTRA_STREAM, uri)
                clipData = ClipData.newRawUri(null, uri)

                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }

            val chooser = Intent.createChooser(intent, "Поделиться звуком")

            chooser.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            chooser.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            context.startActivity(chooser)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}