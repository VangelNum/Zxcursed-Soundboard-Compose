package com.empty.zxcursed_soundboard_compose

import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.empty.zxcursed_soundboard_compose.ui.theme.ZxcursedSoundboardComposeTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import kotlinx.android.synthetic.main.activity_for_video.videoView
import kotlinx.coroutines.*

class MainActivity : ComponentActivity() {
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_for_video)
        val simpleVideoView = findViewById<VideoView>(R.id.videoView)
        simpleVideoView.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.video2))
        simpleVideoView.requestFocus()
        simpleVideoView.start()
        simpleVideoView.setOnCompletionListener {
            setContent {
                ZxcursedSoundboardComposeTheme {
                    DestinationsNavHost(navGraph = NavGraphs.root)
                }
            }
        }
    }
}








