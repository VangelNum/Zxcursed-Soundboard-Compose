package com.zxcursedsoundboard.apk

import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.zxcursedsoundboard.apk.ui.theme.ZxcursedSoundboardComposeTheme
import com.ramcosta.composedestinations.DestinationsNavHost

class MainActivity : ComponentActivity() {

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








