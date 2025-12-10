package com.zxcursedsoundboard.apk.feature_watch_media.presentation

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.zxcursedsoundboard.apk.R
import com.zxcursedsoundboard.apk.core.data.model.MediaItems
import com.zxcursedsoundboard.apk.core.presentation.MainViewModel
import com.zxcursedsoundboard.apk.feature_favourite.data.model.FavouriteEntity
import com.zxcursedsoundboard.apk.feature_favourite.presentation.FavouriteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchMediaScreen(
    mainViewModel: MainViewModel,
    isPlaying: Boolean,
    duration: Long,
    currentTimeMedia: Long,
    looping: Boolean,
    currentSong: MediaItems,
    listOfMedia: List<MediaItems>,
    favouriteViewModel: FavouriteViewModel,
    routeOfPlayingSong: String,
    navController: NavController
) {

    var sliderPosition by remember { mutableStateOf(0f) }
    var isSeeking by remember { mutableStateOf(false) }

    LaunchedEffect(currentTimeMedia) {
        if (!isSeeking) {
            sliderPosition = currentTimeMedia.toFloat()
        }
    }

    val context = LocalContext.current
    val favouriteState = favouriteViewModel.favouriteState.collectAsState()
    var isFavourite by remember {
        mutableStateOf(false)
    }
    var scrollState by remember { mutableStateOf(Offset(0F, 0F)) }
    var differenceScroll by remember { mutableStateOf(Offset(0F, 0F)) }
    LaunchedEffect(key1 = currentSong) {
        isFavourite = favouriteState.value.data?.toString()?.contains(currentSong.name) == true
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(0xCB0B283F),
            )
            .pointerInput(Unit) {
                detectVerticalDragGestures(
                    onDragStart = { touch ->
                        scrollState = touch
                    },
                    onVerticalDrag = { change, dragAmount ->
                        differenceScroll = change.position
                    },
                    onDragEnd = {
                        if (scrollState.y - differenceScroll.y < -300F) {
                            navController.popBackStack()
                        }
                    }
                )
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CardItem(
                image = currentSong.image,
                mainViewModel = mainViewModel,
                context = context,
                audioRes = currentSong.audio,
                songNameRes = currentSong.name
            )
            Spacer(modifier = Modifier.height(4.dp))
            AuthorAndTitleItem(currentSong.name, currentSong.author)

            CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
                Slider(
                    value = sliderPosition,
                    onValueChange = { newPosition ->
                        isSeeking = true
                        sliderPosition = newPosition
                    },
                    onValueChangeFinished = {
                        mainViewModel.setTimeOfMedia(sliderPosition.toLong())
                        isSeeking = false
                    },
                    valueRange = 0f..duration.toFloat(),
                    modifier = Modifier.fillMaxWidth(1f)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, end = 4.dp)
            ) {
                Text(
                    text = if(isSeeking) sliderPosition.toLong().formatTime() else currentTimeMedia.formatTime(),
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(text = duration.formatTime(), style = MaterialTheme.typography.titleMedium)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    mainViewModel.toggleLooping()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_loop_24),
                        contentDescription = "looping",
                        modifier = Modifier.size(30.dp),
                        tint = if (looping) MaterialTheme.colorScheme.primaryContainer else LocalContentColor.current
                    )
                }
                IconButton(onClick = {
                    mainViewModel.playPreviousMedia(listOfMedia, routeOfPlayingSong, context)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_skip_previous_24),
                        contentDescription = "previous",
                        modifier = Modifier.size(30.dp)
                    )
                }
                if (isPlaying) {
                    IconButton(
                        onClick = { mainViewModel.pause() },
                        modifier = Modifier.size(60.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_pause_circle_24),
                            contentDescription = "play/pause",
                            modifier = Modifier.size(60.dp)
                        )
                    }
                } else {
                    IconButton(
                        onClick = { mainViewModel.play() },
                        modifier = Modifier.size(60.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_play_circle_24),
                            contentDescription = "play/pause",
                            modifier = Modifier.size(60.dp)
                        )
                    }
                }
                IconButton(onClick = {
                    mainViewModel.playNextMedia(listOfMedia, routeOfPlayingSong, context)
                })
                {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_skip_next_24),
                        contentDescription = "next",
                        modifier = Modifier.size(30.dp)
                    )
                }
                if (isFavourite) {
                    IconButton(onClick = {
                        favouriteViewModel.deleteSong(currentSong.name)
                        isFavourite = false
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Favorite,
                            contentDescription = "delete from favourite",
                            modifier = Modifier.size(30.dp),
                            tint = Color.Red
                        )
                    }
                } else {
                    IconButton(onClick = {
                        favouriteViewModel.addSong(
                            FavouriteEntity(
                                id = 0,
                                currentSong.name,
                                currentSong.author,
                                currentSong.image,
                                currentSong.audio
                            )
                        )
                        isFavourite = true
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_favorite_border_24),
                            contentDescription = "add to favourite",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            }
        }

    }

}

@Composable
fun AuthorAndTitleItem(name: String, author: String) {
    Text(
        modifier = Modifier.padding(start = 4.dp),
        text = name,
        style = MaterialTheme.typography.titleMedium
    )
    Text(
        text = author,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier
            .alpha(0.5f)
            .padding(start = 4.dp)
    )
}

@Composable
fun CardItem(
    image: String,
    mainViewModel: MainViewModel,
    context: Context,
    audioRes: String,
    songNameRes: String
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(0.5f), contentAlignment = Alignment.BottomEnd
    ) {
        Card(shape = MaterialTheme.shapes.extraLarge) {
            SubcomposeAsyncImage(
                model = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Box() {
            IconButton(onClick = {
                expanded = !expanded
            }, modifier = Modifier.padding(end = 8.dp)) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(id = R.drawable.baseline_more_horiz_24),
                    contentDescription = "more"
                )
            }
            if (expanded) {
                DropdownMenu(
                    expanded = true,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text(text = stringResource(id = R.string.download)) },
                        onClick = {
                            mainViewModel.downloadFile(
                                url = audioRes,
                                fileName = songNameRes,
                                context = context,
                            )
                        },
                        leadingIcon = {
                            Icon(
                                painterResource(id = R.drawable.ic_baseline_download_24),
                                contentDescription = null
                            )
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = stringResource(id = R.string.share)) },
                        onClick = {
                            mainViewModel.share(
                                context = context,
                                audioUrl = audioRes,
                                fileName = songNameRes
                            )
                        },
                        leadingIcon = {
                            Icon(
                                painterResource(id = R.drawable.ic_baseline_share_24),
                                contentDescription = null
                            )
                        }
                    )
                }
            }
        }
    }
}

fun Long.formatTime(): String {
    val durationOfMedia = this / 1000
    val minutes = durationOfMedia / 60
    val seconds = durationOfMedia % 60
    return String.format("%02d:%02d", minutes, seconds)
}
