package com.zxcursedsoundboard.apk.feature_main.presentation

import android.widget.Toast
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.zxcursedsoundboard.apk.R
import com.zxcursedsoundboard.apk.core.data.model.DownloadStatus
import com.zxcursedsoundboard.apk.core.data.model.MediaItem
import com.zxcursedsoundboard.apk.core.presentation.MainViewModel
import com.zxcursedsoundboard.apk.feature_favourite.data.model.FavouriteEntity
import com.zxcursedsoundboard.apk.feature_favourite.presentation.FavouriteViewModel

@Composable
fun ZxcursedMainScreen(
    mainViewModel: MainViewModel,
    isPlaying: Boolean?,
    favouriteViewModel: FavouriteViewModel,
    currentDestination: String?,
    routeOfPlayingSong: String,
) {
    val favouriteState = favouriteViewModel.favouriteState.collectAsState()
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

    val mediaItemsMain = mutableListOf(
        MediaItem(R.raw.cursed2, R.string.pivo, R.string.zxcursed, R.drawable.pivo),
        MediaItem(R.raw.cursed3, R.string.molchat, R.string.zxcursed, R.drawable.molchat),
        MediaItem(R.raw.cursed4, R.string.traxat, R.string.zxcursed, R.drawable.traxat),
        MediaItem(R.raw.cursed6, R.string.sydalut, R.string.zxcursed, R.drawable.sydalut),
        MediaItem(R.raw.cursed5, R.string.pikaper, R.string.zxcursed, R.drawable.pikaper),
        MediaItem(R.raw.cursed7, R.string.madmyazel, R.string.zxcursed, R.drawable.madmyazel),
        MediaItem(R.raw.cursed8, R.string.chtoetoblyat, R.string.zxcursed, R.drawable.chtoetoblyat),
        MediaItem(R.raw.cursed9, R.string.spasibo, R.string.zxcursed, R.drawable.spasibo),
        MediaItem(R.raw.cursed10, R.string.denegnet, R.string.zxcursed, R.drawable.denegnet),
        MediaItem(R.raw.cursed11, R.string.minuspivo, R.string.zxcursed, R.drawable.minuspivo),
    )

    var expandedIndex by remember { mutableStateOf(-1) }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 108.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        itemsIndexed(mediaItemsMain) { index, item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        mainViewModel.setMedia(
                            index,
                            context,
                            songRes = item.audioResId,
                            item.songNameRes,
                            item.songAuthor,
                            item.imageRes,
                            mediaItemsMain,
                            currentDestination ?: ""
                        )
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.size(64.dp), contentAlignment = Alignment.Center) {
                    Card(shape = RoundedCornerShape(16.dp), modifier = Modifier.size(64.dp)) {
                        Image(
                            painter = painterResource(id = item.imageRes),
                            contentDescription = null,
                            modifier = Modifier.size(64.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                    //not work with standard AnimatedVisibility
                    androidx.compose.animation.AnimatedVisibility(
                        index == currentPosition.value && isPlaying == true && routeOfPlayingSong == currentDestination,
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
                        text = stringResource(id = item.songNameRes),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(text = stringResource(id = item.songAuthor), Modifier.alpha(0.5f))
                }
                Spacer(modifier = Modifier.weight(1f))

                val isFavourite = favouriteState.value.data?.toString()?.contains(item.songNameRes.toString()) ?: false
                IconButton(onClick = {
                    val song = FavouriteEntity(
                        songName = item.songNameRes,
                        songAuthor = item.songAuthor,
                        songImageRes = item.imageRes,
                        item.audioResId
                    )
                    if (isFavourite) {
                        favouriteViewModel.deleteSong(item.songNameRes)
                    } else {
                        favouriteViewModel.addSong(song)
                    }
                }) {
                    if (isFavourite) {
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
                                    mainViewModel.downloadRawFile(
                                        context,
                                        item.audioResId,
                                        context.getString(item.songNameRes)
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
                                        resourceId = item.audioResId,
                                        fileName = context.getString(item.songNameRes)
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

