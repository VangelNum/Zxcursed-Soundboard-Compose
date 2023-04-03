package com.zxcursedsoundboard.apk.feature_favourite.presentation

import android.widget.Toast
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.zxcursedsoundboard.apk.R
import com.zxcursedsoundboard.apk.core.common.Resource
import com.zxcursedsoundboard.apk.core.data.model.DownloadStatus
import com.zxcursedsoundboard.apk.core.data.model.MediaItems
import com.zxcursedsoundboard.apk.core.presentation.MainViewModel
import com.zxcursedsoundboard.apk.core.presentation.Screens
import com.zxcursedsoundboard.apk.feature_favourite.data.model.FavouriteEntity

@Composable
fun FavouriteScreen(
    favouriteViewModel: FavouriteViewModel,
    mainViewModel: MainViewModel,
    isPlaying: Boolean,
    currentDestination: String?,
    routeOfPlayingSong: String
) {
    val state = favouriteViewModel.favouriteState.collectAsState()

    when (state.value) {
        is Resource.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is Resource.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = state.value.message.toString(), textAlign = TextAlign.Center)
            }
        }

        is Resource.Success -> {
            FavouriteItem(
                items = state.value.data!!,
                favouriteViewModel = favouriteViewModel,
                mainViewModel = mainViewModel,
                isPlaying = isPlaying,
                currentDestination = currentDestination,
                routeOfPlayingSong = routeOfPlayingSong
            )
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavouriteItem(
    items: List<FavouriteEntity>,
    favouriteViewModel: FavouriteViewModel,
    mainViewModel: MainViewModel,
    isPlaying: Boolean,
    currentDestination: String?,
    routeOfPlayingSong: String
) {
    val currentPosition = mainViewModel.currentPositionIndex.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        mainViewModel.downloadStatus.collect { downloadStatus ->
            when (downloadStatus) {
                is DownloadStatus.Success -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.download_complete_notification_title),
                        Toast.LENGTH_LONG
                    ).show()
                }

                is DownloadStatus.Error -> {
                    Toast.makeText(
                        context,
                        downloadStatus.message,
                        Toast.LENGTH_LONG
                    ).show()
                }

                else -> {
                    Unit
                }
            }
        }
    }

    var expandedIndex by remember { mutableStateOf(-1) }


    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 108.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        itemsIndexed(
            items,
            key = { _, mediaItem -> mediaItem.id }) { index, mediaItem ->
            Box(modifier = Modifier.animateItemPlacement()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            mainViewModel.setMedia(
                                index,
                                context,
                                mediaItem.songAudioRes,
                                mediaItem.songName,
                                mediaItem.songAuthor,
                                mediaItem.songImageRes,
                                items.map { entity ->
                                    MediaItems(
                                        entity.songAudioRes,
                                        entity.songName,
                                        entity.songAuthor,
                                        entity.songImageRes
                                    )
                                },
                                Screens.FavouriteScreen.route
                            )
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.size(64.dp), contentAlignment = Alignment.Center) {
                        Card(shape = RoundedCornerShape(16.dp), modifier = Modifier.size(64.dp)) {
                            Image(
                                painter = painterResource(id = mediaItem.songImageRes),
                                contentDescription = null,
                                modifier = Modifier.size(64.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                        //not work with standard AnimatedVisibility
                        androidx.compose.animation.AnimatedVisibility(
                            index == currentPosition.value && isPlaying && routeOfPlayingSong == currentDestination,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.outline_pause_24),
                                contentDescription = "Play/Pause",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = stringResource(id = mediaItem.songName),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(text = stringResource(id = mediaItem.songAuthor), Modifier.alpha(0.5f))
                    }
                    Spacer(modifier = Modifier.weight(1f))

                    IconButton(onClick = {
                        val song = FavouriteEntity(
                            id = mediaItem.id,
                            songName = mediaItem.songName,
                            songAuthor = mediaItem.songAuthor,
                            songImageRes = mediaItem.songImageRes,
                            songAudioRes = mediaItem.songAudioRes
                        )
                        favouriteViewModel.deleteSong(mediaItem.songName)
                    }) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            contentDescription = "delete",
                            imageVector = Icons.Outlined.Close,
                        )
                    }
                    Box {
                        IconButton(onClick = {
                            expandedIndex = index
                        }) {
                            Icon(
                                modifier = Modifier.size(30.dp),
                                painter = painterResource(id = R.drawable.baseline_more_horiz_24),
                                contentDescription = "more"
                            )
                        }
                        if (expandedIndex == index) {
                            DropdownMenu(
                                expanded = true,
                                onDismissRequest = { expandedIndex = -1 }
                            ) {
                                DropdownMenuItem(
                                    text = { Text(text = stringResource(id = R.string.download)) },
                                    onClick = {
                                        mainViewModel.downloadRawFile(
                                            context,
                                            mediaItem.songAudioRes,
                                            context.getString(mediaItem.songName)
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
                                            resourceId = mediaItem.songAudioRes,
                                            fileName = context.getString(mediaItem.songName)
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
        }
    }
}
