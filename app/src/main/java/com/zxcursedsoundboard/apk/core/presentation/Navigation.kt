package com.zxcursedsoundboard.apk.core.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zxcursedsoundboard.apk.R
import com.zxcursedsoundboard.apk.ZxcursedSoundScreen
import com.zxcursedsoundboard.apk.feature_favourite.presentation.FavouriteScreen
import com.zxcursedsoundboard.apk.feature_main.presentation.ZxcursedMainScreen
import com.zxcursedsoundboard.apk.feature_watch_media.presentation.WatchMediaScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screens.NavigationScreen.route
) {
    val mainViewModel = viewModel<MainViewModel>()
    val currentPosition = mainViewModel.currentPositionIndex.collectAsState()
    val isPlaying = mainViewModel.isPlaying.collectAsState()
    val songName = mainViewModel.songName.collectAsState()
    val songImage = mainViewModel.songImage.collectAsState()
    val songAuthor = mainViewModel.songAuthor.collectAsState()
    val duration = mainViewModel.duration.collectAsState()
    val currentTimeMedia = mainViewModel.currentTimeMedia.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { /*TODO*/ },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Outlined.Menu, contentDescription = "menu")
                    }
                })
        },
        bottomBar = {
                AnimatedVisibility(songName.value != 0, enter = slideInVertically { offset ->
                    offset
                }) {
                    Card(colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer),modifier = Modifier
                        .clickable {
                            navController.navigate(Screens.WatchMediaScreen.route)
                        }, shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Card {
                                Image(
                                    painter = painterResource(id = songImage.value),
                                    contentDescription = null,
                                    modifier = Modifier.size(60.dp)
                                )
                            }
                            Column {
                                Text(text = stringResource(id = songName.value))
                                Text(text = stringResource(id = songAuthor.value))
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            if (isPlaying.value) {
                                IconButton(onClick = { mainViewModel.pause() }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.outline_pause_24),
                                        contentDescription = "pause"
                                    )
                                }
                            } else {
                                IconButton(onClick = { mainViewModel.play() }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.outline_play_arrow_24),
                                        contentDescription = "play"
                                    )
                                }
                            }
                        }
                    }
                }

        }
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(it)
        ) {
            composable(Screens.NavigationScreen.route) {
                NavigationScreen(navController)
            }
            composable(Screens.ZxcursedMainScreen.route) {
                ZxcursedMainScreen(mainViewModel)
            }
            composable(Screens.ZxcursedSoundScreen.route) {
                ZxcursedSoundScreen()
            }
            composable(Screens.FavouriteScreen.route) {
                FavouriteScreen()
            }
            composable(Screens.WatchMediaScreen.route) {
                WatchMediaScreen(
                    mainViewModel = mainViewModel,
                    isPlaying,
                    songName,
                    songImage,
                    songAuthor,
                    duration,
                    currentTimeMedia
                )
            }
        }

    }
}
