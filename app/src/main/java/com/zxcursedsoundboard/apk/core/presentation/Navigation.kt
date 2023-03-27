package com.zxcursedsoundboard.apk.core.presentation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.zxcursedsoundboard.apk.R
import com.zxcursedsoundboard.apk.feature_Test.MediaControl
import com.zxcursedsoundboard.apk.feature_favourite.presentation.FavouriteScreen
import com.zxcursedsoundboard.apk.feature_favourite.presentation.FavouriteViewModel
import com.zxcursedsoundboard.apk.feature_main.presentation.ZxcursedMainScreen
import com.zxcursedsoundboard.apk.feature_sounds_zxcursed.presentation.ZxcursedSoundScreen
import com.zxcursedsoundboard.apk.feature_watch_media.presentation.WatchMediaScreen




//startDestination: String = Screens.NavigationScreen.route,
//favouriteViewModel: FavouriteViewModel = hiltViewModel(),
//mainViewModel: MainViewModel = hiltViewModel()
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun Navigation(
    startMedia:() -> Unit,
    startDestination: String = Screens.NavigationScreen.route,
    favouriteViewModel: FavouriteViewModel = hiltViewModel(),
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val navController: NavHostController = rememberAnimatedNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    val isPlaying = mainViewModel.isPlaying.collectAsState()
    val currentSong = mainViewModel.currentSong.collectAsState()
    val duration = mainViewModel.duration.collectAsState()
    val currentTimeMedia = mainViewModel.currentTimeMedia.collectAsState(0)
    val looping = mainViewModel.looping.collectAsState()
    val listOfMedia = mainViewModel.songList.collectAsState()
    val routeOfPlayingSong = mainViewModel.routeOfPlayingSong.collectAsState()

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
        AnimatedNavHost(
            navController,
            startDestination = startDestination,
            modifier = Modifier.padding(it)
        ) {
            composable(Screens.TestScreen.route) {
                MediaControl(startMedia)
            }
            composable(
                Screens.WatchMediaScreen.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Up,
                        animationSpec = tween(800)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Down,
                        animationSpec = tween(800)
                    )
                },
            ) {
                WatchMediaScreen(
                    mainViewModel = mainViewModel,
                    isPlaying = isPlaying.value,
                    duration = duration.value,
                    currentTimeMedia = currentTimeMedia.value,
                    looping = looping.value,
                    currentSong = currentSong.value,
                    listOfMedia = listOfMedia.value,
                    favouriteViewModel = favouriteViewModel,
                    routeOfPlayingSong = routeOfPlayingSong.value
                )
            }
            composable(
                Screens.NavigationScreen.route,
            ) { NavigationScreen(navController = navController) }
            composable(
                Screens.ZxcursedMainScreen.route,
                enterTransition = {
                    when (initialState.destination.route) {
                        Screens.WatchMediaScreen.route -> {
                            null
                        }

                        else -> {
                            slideIntoContainer(
                                AnimatedContentScope.SlideDirection.Left,
                                animationSpec = tween(700)
                            )
                        }
                    }
                },
                exitTransition = {
                    when (targetState.destination.route) {
                        Screens.WatchMediaScreen.route -> {
                            null
                        }

                        else -> {
                            slideOutOfContainer(
                                AnimatedContentScope.SlideDirection.Right,
                                animationSpec = tween(700)
                            )
                        }
                    }
                },
            ) {
                ZxcursedMainScreen(
                    mainViewModel = mainViewModel,
                    isPlaying = isPlaying.value,
                    favouriteViewModel = favouriteViewModel,
                    currentDestination = currentDestination,
                    routeOfPlayingSong = routeOfPlayingSong.value
                )

            }
            composable(Screens.FavouriteScreen.route) {
                FavouriteScreen(
                    favouriteViewModel,
                    mainViewModel,
                    isPlaying.value,
                    currentDestination,
                    routeOfPlayingSong.value
                )
            }
            composable(
                Screens.ZxcursedSoundScreen.route,
                enterTransition = {
                    when (initialState.destination.route) {
                        Screens.WatchMediaScreen.route -> {
                            null
                        }

                        else -> {
                            slideIntoContainer(
                                AnimatedContentScope.SlideDirection.Left,
                                animationSpec = tween(700)
                            )
                        }
                    }
                },
                exitTransition = {
                    when (targetState.destination.route) {
                        Screens.WatchMediaScreen.route -> {
                            null
                        }

                        else -> {
                            slideOutOfContainer(
                                AnimatedContentScope.SlideDirection.Right,
                                animationSpec = tween(700)
                            )
                        }
                    }
                },
            ) {
                ZxcursedSoundScreen(
                    mainViewModel = mainViewModel,
                    isPlaying = isPlaying.value,
                    favouriteViewModel = favouriteViewModel,
                    currentDestination = currentDestination,
                    routeOfPlayingSong = routeOfPlayingSong.value
                )
            }
        }
        AnimatedVisibility(
            currentSong.value.author != -1 && currentDestination != Screens.WatchMediaScreen.route,
            enter = slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(durationMillis = 400)
            ),
            exit = fadeOut(),
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                Card(
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer),
                    modifier = Modifier
                        .clickable {
                            navController.navigate(Screens.WatchMediaScreen.route)
                        },
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card {
                            Image(
                                painter = painterResource(id = currentSong.value.image),
                                contentDescription = null,
                                modifier = Modifier.size(60.dp)
                            )
                        }
                        Column {
                            Text(text = stringResource(id = currentSong.value.name))
                            Text(text = stringResource(id = currentSong.value.author))
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
