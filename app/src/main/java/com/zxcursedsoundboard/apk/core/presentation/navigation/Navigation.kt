package com.zxcursedsoundboard.apk.core.presentation.navigation

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.SubcomposeAsyncImage
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.zxcursedsoundboard.apk.R
import com.zxcursedsoundboard.apk.core.presentation.MainViewModel
import com.zxcursedsoundboard.apk.core.presentation.drawer_layout.DrawerBody
import com.zxcursedsoundboard.apk.core.presentation.drawer_layout.DrawerHeader
import com.zxcursedsoundboard.apk.feature_alwayswannafly.presentation.AlwaysWannaFlyScreen
import com.zxcursedsoundboard.apk.feature_contacts.presentation.ContactScreen
import com.zxcursedsoundboard.apk.feature_favourite.presentation.FavouriteScreen
import com.zxcursedsoundboard.apk.feature_favourite.presentation.FavouriteViewModel
import com.zxcursedsoundboard.apk.feature_main.presentation.ZxcursedMainScreen
import com.zxcursedsoundboard.apk.feature_settings.presentation.SettingsScreen
import com.zxcursedsoundboard.apk.feature_snail.presentation.SnailScreen
import com.zxcursedsoundboard.apk.feature_sounds_zxcursed.presentation.ZxcursedSoundScreen
import com.zxcursedsoundboard.apk.feature_watch_media.presentation.WatchMediaScreen
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun Navigation(
    startDestination: String = Screens.NavigationScreen.route,
    favouriteViewModel: FavouriteViewModel = hiltViewModel(),
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val navController: NavHostController = rememberAnimatedNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    val isPlaying = mainViewModel.isPlaying.collectAsStateWithLifecycle()
    val routeOfPlayingSong = mainViewModel.routeOfPlayingSong.collectAsStateWithLifecycle()
    val currentSong = mainViewModel.currentSong.collectAsStateWithLifecycle()
    val duration = mainViewModel.duration.collectAsStateWithLifecycle()
    val currentTimeMedia = mainViewModel.currentTimeMedia.collectAsState(0L)
    val looping = mainViewModel.looping.collectAsStateWithLifecycle()
    val listOfMedia = mainViewModel.songList.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val sharedPreferences = context.getSharedPreferences("my_app_prefs", Context.MODE_PRIVATE)
    val currentBackground = remember {
        mutableStateOf(
            sharedPreferences.getInt(
                "background",
                0
            )
        )
    }


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            if (drawerState.isOpen) {
                BackHandler {
                    scope.launch {
                        drawerState.close()
                    }
                }
            }
            ModalDrawerSheet {
                DrawerHeader()
                DrawerBody(navController = navController, drawerState)
            }
        }
    ) {
        Scaffold(
            topBar = {
                if (currentDestination == Screens.NavigationScreen.route) {
                    TopAppBar(
                        title = { },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Outlined.Menu,
                                    contentDescription = "menu"
                                )
                            }
                        })

                } else if (currentDestination != null) {
                    TopAppBar(title = { }, navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                navController.popBackStack()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Outlined.ArrowBack,
                                contentDescription = "menu"
                            )
                        }
                    })
                }
            },

            ) { padding ->

            AnimatedNavHost(
                navController,
                startDestination = startDestination,
                modifier = if (currentBackground.value != 0) with(Modifier) {
                    fillMaxSize()
                        .padding(padding)
                        .paint(
                            painterResource(id = currentBackground.value),
                            contentScale = ContentScale.Crop
                        )
                } else {
                    Modifier
                        .fillMaxSize()
                        .padding(padding)
                }
            ) {
                composable(
                    Screens.WatchMediaScreen.route,
                    enterTransition = {
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Up,
                            animationSpec = tween(400)
                        )
                    },
                    exitTransition = {
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Down,
                            animationSpec = tween(400)
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
                        routeOfPlayingSong = routeOfPlayingSong.value,
                        navController = navController
                    )
                }
                composable(
                    Screens.NavigationScreen.route,
                ) { NavigationScreen(navController = navController, currentSong.value) }
                composable(
                    Screens.ZxcursedMainScreen.route,
                    enterTransition = {
                        when (initialState.destination.route) {
                            Screens.WatchMediaScreen.route -> {
                                null
                            }

                            else -> {
                                slideIntoContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(350)
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
                                    AnimatedContentTransitionScope.SlideDirection.Right,
                                    animationSpec = tween(350)
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
                        routeOfPlayingSong = routeOfPlayingSong.value,
                        currentSong = currentSong.value
                    )

                }
                composable(
                    Screens.FavouriteScreen.route,
                    enterTransition = {
                        when (initialState.destination.route) {
                            Screens.WatchMediaScreen.route -> {
                                null
                            }

                            else -> {
                                slideIntoContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(350)
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
                                    AnimatedContentTransitionScope.SlideDirection.Right,
                                    animationSpec = tween(350)
                                )
                            }
                        }
                    },
                ) {
                    FavouriteScreen(
                        favouriteViewModel,
                        mainViewModel,
                        isPlaying.value,
                        routeOfPlayingSong.value,
                        currentSong = currentSong.value
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
                                    AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(350)
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
                                    AnimatedContentTransitionScope.SlideDirection.Right,
                                    animationSpec = tween(350)
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
                        routeOfPlayingSong = routeOfPlayingSong.value,
                        currentSong = currentSong.value
                    )
                }
                composable(
                    Screens.SnailScreen.route,
                    enterTransition = {
                        when (initialState.destination.route) {
                            Screens.WatchMediaScreen.route -> {
                                null
                            }

                            else -> {
                                slideIntoContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(350)
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
                                    AnimatedContentTransitionScope.SlideDirection.Right,
                                    animationSpec = tween(350)
                                )
                            }
                        }
                    },
                ) {
                    SnailScreen(
                        mainViewModel = mainViewModel,
                        isPlaying = isPlaying.value,
                        favouriteViewModel = favouriteViewModel,
                        currentDestination = currentDestination,
                        routeOfPlayingSong = routeOfPlayingSong.value,
                        currentSong = currentSong.value
                    )
                }
                composable(
                    Screens.AlwaysWannaFlyScreen.route,
                    enterTransition = {
                        when (initialState.destination.route) {
                            Screens.WatchMediaScreen.route -> {
                                null
                            }

                            else -> {
                                slideIntoContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(350)
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
                                    AnimatedContentTransitionScope.SlideDirection.Right,
                                    animationSpec = tween(350)
                                )
                            }
                        }
                    },
                ) {
                    AlwaysWannaFlyScreen(
                        mainViewModel = mainViewModel,
                        isPlaying = isPlaying.value,
                        favouriteViewModel = favouriteViewModel,
                        currentDestination = currentDestination,
                        routeOfPlayingSong = routeOfPlayingSong.value,
                        currentSong = currentSong.value
                    )
                }
                composable(Screens.ContactScreen.route) {
                    ContactScreen(currentSong = currentSong.value)
                }
                composable(Screens.SettingsScreen.route) {
                    SettingsScreen(
                        currentBackground,
                        sharedPreferences,
                        currentSong = currentSong.value
                    )
                }
            }
            AnimatedVisibility(
                currentSong.value.author != "" && currentDestination != Screens.WatchMediaScreen.route,
                enter = slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(durationMillis = 300)
                ),
                exit = fadeOut(),
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
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
                                SubcomposeAsyncImage(
                                    model = currentSong.value.image,
                                    contentDescription = null,
                                    modifier = Modifier.size(64.dp),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = currentSong.value.name,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1
                                )
                                Text(
                                    text = currentSong.value.author,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1
                                )
                            }
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
                                        painter = painterResource(id = R.drawable.baseline_play_arrow_24),
                                        contentDescription = "play"
                                    )
                                }
                            }
                            IconButton(onClick = {
                                mainViewModel.playNextMedia(
                                    mainViewModel.songList.value,
                                    routeOfPlayingSong.value,
                                    context
                                )
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_skip_next_24),
                                    contentDescription = "pause"
                                )
                            }
                        }

                    }
                }
            }
        }
    }

}

