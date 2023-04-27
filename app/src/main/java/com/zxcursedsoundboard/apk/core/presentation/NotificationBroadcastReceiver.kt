package com.zxcursedsoundboard.apk.core.presentation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.android.exoplayer2.ExoPlayer

class MediaBroadcastReceiver(private val player: ExoPlayer) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            "play" -> {
                // handle play action
                player.play()
            }
            "pause" -> {
                // handle pause action
                player.pause()
            }
            "play_next" -> {
                // handle play next action
               // playNextMedia(_songList.value, _routeOfPlayingSong.value, context)
            }
        }
    }
}
