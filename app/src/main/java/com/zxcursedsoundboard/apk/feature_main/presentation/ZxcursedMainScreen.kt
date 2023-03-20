package com.zxcursedsoundboard.apk.feature_main.presentation

import android.media.MediaPlayer
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.zxcursedsoundboard.apk.R
import com.zxcursedsoundboard.apk.core.data.MediaItem
import com.zxcursedsoundboard.apk.feature_favourite.data.model.FavouriteEntity
import com.zxcursedsoundboard.apk.feature_favourite.presentation.FavouriteViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


//@Composable
//fun ZxcursedMainScreen(favouriteViewModel: FavouriteViewModel = hiltViewModel()) {
//    val lazyListState = rememberLazyListState()
//    val favouriteState = favouriteViewModel.favouriteState.collectAsState()
//    val context = LocalContext.current
//    val scope = rememberCoroutineScope()
//    val isVisible = remember {
//        derivedStateOf {
//            lazyListState.firstVisibleItemIndex > 0
//        }
//
//    }
//    var mediaPlayer: MediaPlayer? = null
//    var isPlaying by remember { mutableStateOf(false) }
//    var currentPlayingIndex by remember { mutableStateOf(-1) }
//    val progress = remember { mutableStateOf(0F) }
//    val mediaItems = remember {
//        mutableStateListOf(
//            MediaItem(R.raw.cursed2, R.string.pivo, R.drawable.pivo),
//            MediaItem(R.raw.cursed3, R.string.molchat, R.drawable.molchat),
//            MediaItem(R.raw.cursed4, R.string.traxat, R.drawable.traxat),
//            MediaItem(R.raw.cursed5, R.string.pikaper, R.drawable.pikaper),
//            MediaItem(R.raw.cursed6, R.string.sydalut, R.drawable.sydalut),
//            MediaItem(R.raw.cursed7, R.string.madmyazel, R.drawable.madmyazel),
//            MediaItem(R.raw.cursed8, R.string.chtoetoblyat, R.drawable.chtoetoblyat),
//            MediaItem(R.raw.cursed9, R.string.spasibo, R.drawable.spasibo),
//            MediaItem(R.raw.cursed10, R.string.denegnet, R.drawable.denegnet),
//            MediaItem(R.raw.cursed11, R.string.minuspivo, R.drawable.minuspivo),
//        )
//    }
//    Box(modifier = Modifier.fillMaxSize()) {
//        Image(
//            painter = painterResource(id = R.drawable.photo11),
//            contentDescription = "zxcursed",
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.Crop
//        )
//        LazyColumn(
//            state = lazyListState,
//            modifier = Modifier.fillMaxSize(),
//            contentPadding = PaddingValues(
//                start = 16.dp,
//                end = 16.dp,
//                top = 16.dp,
//                bottom = 100.dp
//            ),
//            verticalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            itemsIndexed(mediaItems) { index, item ->
//                OutlinedButton(
//                    shape = RoundedCornerShape(25.dp),
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(80.dp)
//                        .alpha(0.9f),
//                    onClick = {
//                        if (index == currentPlayingIndex && mediaPlayer != null) {
//                            isPlaying = if (mediaPlayer?.isPlaying == true) {
//                                mediaPlayer?.pause()
//                                false
//                            } else {
//                                mediaPlayer?.start()
//                                true
//                            }
//                        } else {
//                            mediaPlayer?.apply {
//                                stop()
//                                release()
//                            }
//                            mediaPlayer = MediaPlayer.create(context, item.audioResId).apply {
//                                setOnCompletionListener {
//                                    isPlaying = false
//                                    progress.value = 0F
//                                }
//                                setOnPreparedListener {
//                                    progress.value = 0F
//                                    it.start()
//                                    isPlaying = true
//                                    currentPlayingIndex = index
//                                }
//                            }
//                        }
//                    },
//                    border = BorderStroke(1.dp, Color.Gray),
//                ) {
//                    val songNameRes = context.getString(item.songNameRes)
//                    val isFavourite =
//                        favouriteState.value.data?.toString()?.contains(songNameRes) ?: false
//                    val iconTint = if (isFavourite) Color.Red else Color.White
//
//                    Icon(
//                        painter = if (index == currentPlayingIndex && isPlaying) {
//                            painterResource(R.drawable.outline_pause_24)
//                        } else {
//                            painterResource(R.drawable.outline_play_arrow_24)
//                        },
//                        contentDescription = "Play/Pause"
//                    )
//
//                    Column(
//                        modifier = Modifier
//                            .weight(1f)
//                            .padding(start = 8.dp, end = 8.dp),
//                        verticalArrangement = Arrangement.spacedBy(16.dp),
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Text(
//                            textAlign = TextAlign.Center,
//                            text = stringResource(id = item.songNameRes).uppercase(),
//                            style = MaterialTheme.typography.h4.copy(),
//                            maxLines = 2,
//                            overflow = TextOverflow.Ellipsis,
//                        )
//                        if (index == currentPlayingIndex && isPlaying && mediaPlayer != null) {
//                            LaunchedEffect(Unit) {
//                                while (isPlaying && (mediaPlayer?.currentPosition
//                                        ?: 0) < (mediaPlayer?.duration ?: 0)
//                                ) {
//                                    progress.value = (mediaPlayer?.currentPosition
//                                        ?: 0) * 100F / (mediaPlayer?.duration ?: 1)
//                                    delay(16L)
//                                }
//                            }
//                            LinearProgressIndicator(
//                                progress = progress.value / 100,
//                                modifier = Modifier
//                                    .fillMaxWidth(),
//                            )
//                        } else if (index == currentPlayingIndex && !isPlaying && mediaPlayer != null && progress.value != 0F) {
//                            LinearProgressIndicator(
//                                progress = progress.value / 100,
//                                modifier = Modifier
//                                    .fillMaxWidth(),
//                            )
//                        }
//                    }
//                    IconButton(
//                        onClick = {
//                            val song = FavouriteEntity(
//                                songName = songNameRes,
//                                songRes = mediaItems[index].audioResId
//                            )
//                            if (isFavourite) {
//                                favouriteViewModel.deleteSong(songNameRes)
//                            } else {
//                                favouriteViewModel.addSong(song)
//                            }
//                        },
//                    ) {
//                        Icon(
//                            tint = iconTint,
//                            imageVector = Icons.Outlined.Favorite,
//                            contentDescription = "favourite"
//                        )
//                    }
//
//                }
//            }
//        }
//        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
//            FloatingActionButton(modifier = Modifier.padding(16.dp), onClick = {
//                mediaPlayer?.apply {
//                    stop()
//                    release()
//                }
//                isPlaying = false
//                progress.value = 0F
//                mediaPlayer = null
//            }) {
//                Icon(
//                    painter = painterResource(id = R.drawable.baseline_stop_24),
//                    contentDescription = "volume_off"
//                )
//            }
//            AnimatedVisibility(visible = isVisible.value,
//                enter = slideInVertically {
//                    it
//                }, exit = slideOutVertically {
//                    it
//                }) {
//                FloatingActionButton(
//                    modifier = Modifier
//                        .padding(end = 80.dp, bottom = 16.dp),
//                    onClick = {
//                        scope.launch {
//                            lazyListState.animateScrollToItem(0)
//                        }
//                    }) {
//                    Icon(imageVector = Icons.Outlined.KeyboardArrowUp, contentDescription = "up")
//                }
//            }
//        }
//    }
//}

@Composable
fun ZxcursedMainScreen() {
    val context = LocalContext.current
    var mediaPlayer: MediaPlayer? = null
    val mediaItems = remember {
        mutableStateListOf(
            MediaItem(R.raw.cursed2, R.string.pivo, R.drawable.pivo),
            MediaItem(R.raw.cursed3, R.string.molchat, R.drawable.molchat),
            MediaItem(R.raw.cursed4, R.string.traxat, R.drawable.traxat),
            MediaItem(R.raw.cursed5, R.string.pikaper, R.drawable.pikaper),
            MediaItem(R.raw.cursed6, R.string.sydalut, R.drawable.sydalut),
            MediaItem(R.raw.cursed7, R.string.madmyazel, R.drawable.madmyazel),
            MediaItem(R.raw.cursed8, R.string.chtoetoblyat, R.drawable.chtoetoblyat),
            MediaItem(R.raw.cursed9, R.string.spasibo, R.drawable.spasibo),
            MediaItem(R.raw.cursed10, R.string.denegnet, R.drawable.denegnet),
            MediaItem(R.raw.cursed11, R.string.minuspivo, R.drawable.minuspivo),
        )
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 100.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        itemsIndexed(mediaItems) {index, item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        mediaPlayer = MediaPlayer
                            .create(context, item.audioResId)
                            .apply {
                                setOnPreparedListener {
                                    it.start()
                                }
                                setOnCompletionListener {

                                }
                            }
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(shape = RoundedCornerShape(16.dp)) {
                    Image(
                        painter = painterResource(id = item.imageRes),
                        contentDescription = null,
                        modifier = Modifier.size(64.dp)
                    )
                    if (mediaPlayer?.isPlaying==true) {
                        Log.d("tag","check")
                        Icon(painterResource(id = R.drawable.outline_pause_24), contentDescription = "pause")
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = stringResource(id = item.songNameRes))
                    Text(text = "Zxcursed",Modifier.alpha(0.5f))
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(modifier = Modifier.size(30.dp),painter = painterResource(id = R.drawable.baseline_favorite_border_24), contentDescription = "favourite")
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(modifier = Modifier.size(30.dp), painter = painterResource(id = R.drawable.baseline_more_horiz_24), contentDescription = "more")
                }
            }
        }
    }
}

