package com.zxcursedsoundboard.apk.feature_Test

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.support.v4.media.session.MediaSessionCompat
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.zxcursedsoundboard.apk.R


class MediaService : Service() {
    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.zxcursed70)
        mediaPlayer.setOnCompletionListener {
            stopSelf()
        }
    }

    private fun createNotification(): Notification {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "Media Channel",
                "Media Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val channelId = "Media Channel"
        val playIntent = Intent(this, MediaService::class.java).apply {
            action = "play"
        }
        val pauseIntent = Intent(this, MediaService::class.java).apply {
            action = "pause"
        }
        val playPendingIntent = PendingIntent.getService(
            this,
            0,
            playIntent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val pausePendingIntent = PendingIntent.getService(
            this,
            0,
            pauseIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val mediaSession = MediaSessionCompat(this, "MediaService")

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Song Title")
            .setContentText("Artist Name")
            .setSmallIcon(R.drawable.baseline_play_arrow_24)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            //.setContentIntent(pendingIntent)
            .setStyle(
                androidx.media.app.NotificationCompat.MediaStyle()
                    .setMediaSession(mediaSession.sessionToken)
                    .setShowActionsInCompactView(0, 1, 2) // index of media control actions
            )
            .addAction(R.drawable.baseline_skip_previous_24, "Previous", playPendingIntent)
            .addAction(R.drawable.outline_pause_24, "Pause", pausePendingIntent)
            .addAction(R.drawable.baseline_skip_next_24, "Next", null)
            .build()
        
        return notification
    }


    private fun updateNotification() {
        val notificationManager = NotificationManagerCompat.from(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notificationManager.notify(NOTIFICATION_ID, createNotification())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            "play" -> {
                if (!mediaPlayer.isPlaying) {
                    mediaPlayer.start()
                    updateNotification()
                }
            }

            "pause" -> {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.pause()
                    updateNotification()
                }
            }
        }
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return LocalBinder()
    }


    companion object {
        private const val NOTIFICATION_ID = 1
    }

    inner class LocalBinder : Binder() {
        fun getService(): MediaService = this@MediaService
    }


    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }

}