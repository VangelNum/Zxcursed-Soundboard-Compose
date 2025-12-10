package com.zxcursedsoundboard.apk.feature_favourite.presentation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.zxcursedsoundboard.apk.R
import com.zxcursedsoundboard.apk.core.common.Resource
import com.zxcursedsoundboard.apk.core.data.model.MediaItems
import com.zxcursedsoundboard.apk.core.presentation.MainViewModel
import com.zxcursedsoundboard.apk.core.presentation.navigation.Screens
import com.zxcursedsoundboard.apk.feature_favourite.data.model.FavouriteEntity

@Composable
fun FavouriteScreen(
    favouriteViewModel: FavouriteViewModel,
    mainViewModel: MainViewModel,
    isPlaying: Boolean,
    routeOfPlayingSong: String,
    currentSong: MediaItems,
) {
    val state = favouriteViewModel.favouriteState.collectAsStateWithLifecycle()

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
            if (state.value.data?.isEmpty() == true) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Color(0xCB0B283F),
                        )
                        .padding(16.dp), contentAlignment = Alignment.Center
                ) {
                    Text(text = stringResource(id = R.string.empty_favourite))
                }
            } else {
                FavouriteItem(
                    items = state.value.data!!,
                    favouriteViewModel = favouriteViewModel,
                    mainViewModel = mainViewModel,
                    isPlaying = isPlaying,
                    routeOfPlayingSong = routeOfPlayingSong,
                    currentSong = currentSong
                )
            }
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
    routeOfPlayingSong: String,
    currentSong: MediaItems
) {
    val currentPosition = mainViewModel.currentPositionIndex.collectAsState()
    val context = LocalContext.current

    var expandedIndex by remember { mutableStateOf(-1) }

    LaunchedEffect(key1 = items) {
        if (routeOfPlayingSong == Screens.FavouriteScreen.route) {
            mainViewModel.updateSongList(items.map { entity ->
                MediaItems(
                    author = entity.songAuthor,
                    name = entity.songName,
                    image = entity.songImageRes,
                    audio = entity.songAudioRes
                )
            })
        }
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(0xCB0B283F),
            ),
        contentPadding = if (currentSong.author != "") PaddingValues(
            top = 16.dp,
            start = 16.dp,
            end = 16.dp,
            bottom = 108.dp
        ) else PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        itemsIndexed(
            items,
            key = { _, mediaItem -> mediaItem.id }) { index, mediaItem ->
            Box(modifier = Modifier.animateItem()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            mainViewModel.setMedia(
                                index,
                                mediaItem.songAudioRes,
                                mediaItem.songName,
                                mediaItem.songAuthor,
                                mediaItem.songImageRes,
                                Screens.FavouriteScreen.route,
                                items.map { entity ->
                                    MediaItems(
                                        author = entity.songAuthor,
                                        name = entity.songName,
                                        image = entity.songImageRes,
                                        audio = entity.songAudioRes
                                    )
                                },
                                context
                            )
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(modifier = Modifier.size(64.dp), contentAlignment = Alignment.Center) {
                        Card(shape = RoundedCornerShape(16.dp), modifier = Modifier.size(64.dp)) {
                            SubcomposeAsyncImage(
                                model = mediaItem.songImageRes,
                                contentDescription = null,
                                modifier = Modifier.size(64.dp),
                                contentScale = ContentScale.Crop
                            )

                        }
                        //not work with standard AnimatedVisibility
                        androidx.compose.animation.AnimatedVisibility(
                            index == currentPosition.value && isPlaying && routeOfPlayingSong == Screens.FavouriteScreen.route,
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
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = mediaItem.songName,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(mediaItem.songAuthor, Modifier.alpha(0.5f))
                    }
                    IconButton(onClick = {
                        favouriteViewModel.deleteSong(mediaItem.songName)
                        mainViewModel.updatePosition(index, Screens.FavouriteScreen.route)
                        if (index == currentPosition.value) {
                            mainViewModel.playNextMedia(items.map { entity ->
                                MediaItems(
                                    author = entity.songAuthor,
                                    name = entity.songName,
                                    image = entity.songImageRes,
                                    audio = entity.songAudioRes
                                )
                            }, Screens.FavouriteScreen.route, context, true)
                        }
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
                                        mainViewModel.downloadFile(
                                            url = mediaItem.songAudioRes,
                                            fileName = mediaItem.songName,
                                            context = context
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
                                            mediaItem.songAudioRes,
                                            mediaItem.songName
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
