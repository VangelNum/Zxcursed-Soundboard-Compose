package com.zxcursedsoundboard.apk

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.zxcursedsoundboard.apk.core.presentation.Navigation
import com.zxcursedsoundboard.apk.feature_Test.MediaService
import com.zxcursedsoundboard.apk.ui.theme.ZxcursedSoundboardComposeTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var mediaService: MediaService
    private var isBound = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MediaService.LocalBinder
            mediaService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }
    }

    override fun onStart() {
        super.onStart()
        Intent(this, MediaService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        if (isBound) {
            unbindService(connection)
            isBound = false
        }
    }

    fun playMedia() {
        val playIntent = Intent(this, MediaService::class.java).apply {
            action = "play"
        }
        startService(playIntent)
    }

    fun pauseMedia() {
        val pauseIntent = Intent(this, MediaService::class.java).apply {
            action = "pause"
        }
        startService(pauseIntent)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZxcursedSoundboardComposeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Navigation({ playMedia() })
                }
            }
        }
    }
}








