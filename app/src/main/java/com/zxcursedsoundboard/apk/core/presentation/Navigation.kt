package com.zxcursedsoundboard.apk.core.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
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

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screens.NavigationScreen.route
) {
    val mainViewModel = viewModel<MainViewModel>()
    val currentPosition = mainViewModel.currentPosition.collectAsState()
    val isPlaying = mainViewModel.isPlaying.collectAsState()
    val songName = mainViewModel.songName.collectAsState()
    val songImage = mainViewModel.songImage.collectAsState()

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
        }
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter
        ) {
            AnimatedVisibility(songName.value != 0, enter = slideInVertically { offset ->
                offset
            }) {
                Box(modifier = Modifier
                    .background(MaterialTheme.colors.surface)
                    .clickable {

                    }) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
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
                            Text(text = "music subtitle")
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
    }
}
