package com.zxcursedsoundboard.apk.feature_sounds_zxcursed.presentation

import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.zxcursedsoundboard.apk.R
import com.zxcursedsoundboard.apk.core.common.ResourceFirebase
import com.zxcursedsoundboard.apk.core.data.model.MediaItems
import com.zxcursedsoundboard.apk.core.presentation.MainViewModel
import com.zxcursedsoundboard.apk.feature_favourite.data.model.FavouriteEntity
import com.zxcursedsoundboard.apk.feature_favourite.presentation.FavouriteViewModel

@Composable
fun ZxcursedSoundScreen(
    mainViewModel: MainViewModel,
    isPlaying: Boolean?,
    favouriteViewModel: FavouriteViewModel,
    currentDestination: String?,
    routeOfPlayingSong: String,
    currentSong: MediaItems,
) {
    val favouriteState = favouriteViewModel.favouriteState.collectAsStateWithLifecycle()
    val currentPosition = mainViewModel.currentPositionIndex.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val mediaItems = mainViewModel.soundsZxcursed.collectAsStateWithLifecycle()

    when (mediaItems.value) {
        is ResourceFirebase.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(5) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Card(
                                modifier = Modifier.size(64.dp),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color.Gray)
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(20.dp)
                                    .background(Color.Gray, RoundedCornerShape(4.dp))
                            )
                        }
                    }
                }
            }
        }

        is ResourceFirebase.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = mediaItems.value.message.toString())
            }
        }

        is ResourceFirebase.Empty -> {
            LaunchedEffect(key1 = Unit) {
                mainViewModel.getSoundsZxcursed()
            }
        }

        is ResourceFirebase.Success -> {
            var expandedIndex by remember { mutableStateOf(-1) }
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
                itemsIndexed(mediaItems.value.data!!) { index, item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                mainViewModel.setMedia(
                                    index = index,
                                    songRes = item.audio,
                                    songName = item.name,
                                    songAuthor = item.author,
                                    songImage = item.image,
                                    routeOfPlayingSong = currentDestination ?: "",
                                    songList = mediaItems.value.data!!,
                                    context = context
                                )
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.size(64.dp), contentAlignment = Alignment.Center) {
                            Card(
                                shape = RoundedCornerShape(16.dp),
                            ) {
                                AsyncImage(
                                    modifier = Modifier.size(64.dp),
                                    model = item.image,
                                    contentDescription = "photo",
                                    contentScale = ContentScale.Crop,
                                )
                            }
                            val alpha by animateFloatAsState(
                                targetValue = if (index == currentPosition.value && isPlaying == true && routeOfPlayingSong == currentDestination) 1f else 0f,
                                label = "playPauseIconAlpha"
                            )
                            Icon(
                                painter = painterResource(R.drawable.outline_pause_24),
                                contentDescription = "Play/Pause",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(30.dp)
                                    .alpha(alpha)
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = item.name,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(text = item.author, Modifier.alpha(0.5f))
                        }

                        val isFavourite = favouriteState.value.data?.toString()?.contains(item.name)
                        IconButton(onClick = {
                            val song = FavouriteEntity(
                                id = 0,
                                songName = item.name,
                                songAuthor = item.author,
                                songImageRes = item.image,
                                songAudioRes = item.audio
                            )
                            if (isFavourite == true) {
                                favouriteViewModel.deleteSong(item.name)
                            } else {
                                favouriteViewModel.addSong(song)
                            }
                        }) {
                            if (isFavourite == true) {
                                Icon(
                                    tint = Color.Red,
                                    modifier = Modifier.size(30.dp),
                                    contentDescription = "favourite",
                                    imageVector = Icons.Outlined.Favorite,
                                )
                            } else {
                                Icon(
                                    modifier = Modifier.size(30.dp),
                                    painter = painterResource(id = R.drawable.baseline_favorite_border_24),
                                    contentDescription = "favourite"
                                )
                            }
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
                                                url = item.audio,
                                                fileName = item.name,
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
                                                item.audio,
                                                item.name
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
}