package com.empty.zxcursed_soundboard_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.empty.zxcursed_soundboard_compose.ui.theme.ZxcursedSoundboardComposeTheme
import com.ramcosta.composedestinations.DestinationsNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZxcursedSoundboardComposeTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }

    }

}





