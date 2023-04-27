package com.zxcursedsoundboard.apk.core.presentation

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Environment
import android.support.v4.media.session.MediaSessionCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.zxcursedsoundboard.apk.R
import com.zxcursedsoundboard.apk.core.common.ResourceFirebase
import com.zxcursedsoundboard.apk.core.data.model.MediaItems
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
    private var notificationManager: NotificationManager? = null
    private var notificationBuilder: NotificationCompat.Builder? = null

    private fun initDeviceNotification(context: Context) {
        if (notificationManager == null) {
            notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    "my_channel_id",
                    "My Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                notificationManager!!.createNotificationChannel(channel)
            }
            val broadcastReceiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    when (intent.action) {
                        "my_action_play_pause" -> togglePlayback()
                        "my_action_next" -> playNextMedia(
                            _songList.value,
                            _routeOfPlayingSong.value,
                            context
                        )
                    }
                }
            }
            ContextCompat.registerReceiver(context, broadcastReceiver, IntentFilter().apply {
                addAction("my_action_play_pause")
                addAction("my_action_next")
            }, ContextCompat.RECEIVER_EXPORTED)
        }
        if (notificationBuilder == null) {
            val playPauseAction = NotificationCompat.Action(
                R.drawable.outline_pause_24, "Pause",
                PendingIntent.getBroadcast(
                    context,
                    0,
                    Intent("my_action_play_pause"),
                    PendingIntent.FLAG_UPDATE_CURRENT or FLAG_IMMUTABLE
                )
            )
            val playbackActionNext = NotificationCompat.Action(
                R.drawable.baseline_skip_next_24, "Next",
                PendingIntent.getBroadcast(
                    context,
                    0,
                    Intent("my_action_next"),
                    PendingIntent.FLAG_UPDATE_CURRENT or FLAG_IMMUTABLE
                )
            )
            val mediaSession = MediaSessionCompat(context, "tag")
            notificationBuilder = NotificationCompat.Builder(context, "my_channel_id")
                .setSmallIcon(R.drawable.outline_play_arrow_24)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setStyle(
                    androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(
                            0,
                            1,
                        )
                        .setMediaSession(mediaSession.sessionToken)
                )
                .addAction(playPauseAction)
                .addAction(playbackActionNext)
        }

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

        initDeviceNotification(context)
        notificationBuilder?.let {
            it.setContentTitle(_currentSong.value.name)
            it.setContentText(_currentSong.value.author)
            Glide.with(context)
                .asBitmap()
                .load(_currentSong.value.image)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap?>?
                    ) {
                        notificationBuilder!!.setLargeIcon(resource)
                    }
                    override fun onLoadCleared(placeholder: Drawable?) {

                    }
                })
        }

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
                            if (fromFavourite == true) {
                                _currentPositionIndex.value = index - 1
                            } else {
                                _currentPositionIndex.value = index
                            }
                            _duration.value = player?.duration ?: 0
                            notificationManager?.notify(1, notificationBuilder?.build())
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

                    private var job: Job? = null

                    @SuppressLint("RestrictedApi")
                    override fun onIsPlayingChanged(isPlaying: Boolean) {

                        val playPauseAction = if (isPlaying) {
                            NotificationCompat.Action(
                                R.drawable.outline_pause_24, "Pause",
                                PendingIntent.getBroadcast(
                                    context,
                                    0,
                                    Intent("my_action_play_pause"),
                                    PendingIntent.FLAG_UPDATE_CURRENT or FLAG_IMMUTABLE
                                )
                            )
                        } else {
                            NotificationCompat.Action(
                                R.drawable.outline_play_arrow_24, "Play",
                                PendingIntent.getBroadcast(
                                    context,
                                    0,
                                    Intent("my_action_play_pause"),
                                    PendingIntent.FLAG_UPDATE_CURRENT or FLAG_IMMUTABLE
                                )
                            )
                        }
                        notificationBuilder?.let {
                            it.mActions[0] = playPauseAction // update play/pause action
                            notificationManager?.notify(1, it.build()) // update notification
                        }

                        if (isPlaying) {
                            job = viewModelScope.launch(Dispatchers.Main) {
                                while (true) {
                                    val currentPosition = player?.currentPosition ?: 0
                                    _currentTimeMedia.emit(currentPosition)
                                    delay(16)
                                    if (player?.isPlaying == false) {
                                        job?.cancel()
                                        job = null
                                    }
                                }
                            }
                        } else {
                            job?.cancel()
                            job = null
                        }
                    }


                })
            }
        }
        _routeOfPlayingSong.value = routeOfPlayingSong

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
        player?.play()
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


    private fun showNotification(context: Context) {


//        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel = NotificationChannel(
//                NOTIFICATION_CHANNEL_ID,
//                "Current Song",
//                NotificationManager.IMPORTANCE_LOW
//            )
//            notificationManager.createNotificationChannel(channel)
//        }
//
//        val song = currentSong.value
//        val playbackState = player?.playbackState
//
//        //val isPlaying = playbackState != null && playbackState != Player.STATE_IDLE && playbackState != Player.STATE_ENDED && player?.playWhenReady ?: false
//
//        val pauseIntent = Intent(context, MainViewModel::class.java).apply {
//            action = "pause"
//        }
//        val pausePendingIntent = PendingIntent.getService(
//            context,
//            0,
//            pauseIntent,
//            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
//        )
//
//        val playIntent = Intent(context, MainViewModel::class.java).apply {
//            action = "play"
//        }
//        val playPendingIntent = PendingIntent.getService(
//            context,
//            0,
//            playIntent,
//            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
//        )
//        val stopIntent = Intent(context, MainViewModel::class.java).apply {
//            action = "stop"
//        }
//        val stopPendingIntent = PendingIntent.getService(
//            context,
//            0,
//            stopIntent,
//            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
//        )
//        val mediaSession = MediaSessionCompat(context, "your_media_session_tag")
//        val notificationBuilder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
//            .setSmallIcon(R.drawable.outline_play_arrow_24)
//            .setContentTitle(song.author)
//            .setContentText(song.name)
//            .addAction(
//                if (isPlaying.value) R.drawable.outline_pause_24 else R.drawable.outline_play_arrow_24,
//                if (isPlaying.value) "Pause" else "Play",
//                if (isPlaying.value) pausePendingIntent else playPendingIntent
//            )
//            .addAction(R.drawable.baseline_stop_24, "Stop", stopPendingIntent)
//            .setStyle(androidx.media.app.NotificationCompat.MediaStyle()
//                .setMediaSession(mediaSession.sessionToken)
//                .setShowActionsInCompactView(0, 1)
//                .setShowCancelButton(true)
//                .setCancelButtonIntent(stopPendingIntent)
//            )
//
//        notificationManager.notify(1, notificationBuilder.build())
    }


}