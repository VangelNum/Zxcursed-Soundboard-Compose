package com.zxcursedsoundboard.apk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.zxcursedsoundboard.apk.core.presentation.MainViewModel
import com.zxcursedsoundboard.apk.core.presentation.navigation.Navigation
import com.zxcursedsoundboard.apk.feature_favourite.presentation.FavouriteViewModel
import com.zxcursedsoundboard.apk.ui.theme.ZxcursedSoundboardComposeTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private val favouriteViewModel: FavouriteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZxcursedSoundboardComposeTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Navigation(
                        mainViewModel = mainViewModel,
                        favouriteViewModel = favouriteViewModel
                    )
                }
            }
        }
    }
}
