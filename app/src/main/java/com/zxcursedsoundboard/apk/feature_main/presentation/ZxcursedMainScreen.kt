package com.zxcursedsoundboard.apk.feature_main.presentation

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.zxcursedsoundboard.apk.R
import com.zxcursedsoundboard.apk.core.data.MediaItem
import com.zxcursedsoundboard.apk.core.presentation.MainViewModel
import com.zxcursedsoundboard.apk.feature_favourite.data.model.FavouriteEntity
import com.zxcursedsoundboard.apk.feature_favourite.presentation.FavouriteViewModel


@Composable
fun ZxcursedMainScreen(
    mainViewModel: MainViewModel,
    favouriteViewModel: FavouriteViewModel = hiltViewModel()
) {
    val favouriteState = favouriteViewModel.favouriteState.collectAsState()
    val currentPosition = mainViewModel.currentPositionIndex.collectAsState()
    val isPlaying = mainViewModel.isPlaying.collectAsState()
    val context = LocalContext.current
    val mediaItems = remember {
        mutableStateListOf(
            MediaItem(R.raw.cursed2, R.string.pivo, R.string.zxcursed, R.drawable.pivo),
            MediaItem(R.raw.cursed3, R.string.molchat, R.string.zxcursed, R.drawable.molchat),
            MediaItem(R.raw.cursed4, R.string.traxat, R.string.zxcursed, R.drawable.traxat),
            MediaItem(R.raw.cursed5, R.string.pikaper, R.string.zxcursed, R.drawable.pikaper),
            MediaItem(R.raw.cursed6, R.string.sydalut, R.string.zxcursed, R.drawable.sydalut),
            MediaItem(R.raw.cursed7, R.string.madmyazel, R.string.zxcursed, R.drawable.madmyazel),
            MediaItem(
                R.raw.cursed8,
                R.string.chtoetoblyat,
                R.string.zxcursed,
                R.drawable.chtoetoblyat
            ),
            MediaItem(R.raw.cursed9, R.string.spasibo, R.string.zxcursed, R.drawable.spasibo),
            MediaItem(R.raw.cursed10, R.string.denegnet, R.string.zxcursed, R.drawable.denegnet),
            MediaItem(R.raw.cursed11, R.string.minuspivo, R.string.zxcursed, R.drawable.minuspivo),
        )
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        itemsIndexed(mediaItems) { index, item ->
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
                            item.imageRes
                        )
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(shape = RoundedCornerShape(16.dp), modifier = Modifier.size(64.dp)) {
                    Image(
                        painter = painterResource(id = item.imageRes),
                        contentDescription = null,
                        modifier = Modifier.size(64.dp),
                        contentScale = ContentScale.Crop
                    )
                    AnimatedVisibility(
                        index == currentPosition.value && isPlaying.value,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.outline_pause_24),
                                contentDescription = "Play/Pause",
                                modifier = Modifier.size(30.dp)
                            )
                        }
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

                val songNameRes = context.getString(item.songNameRes)
                val isFavourite =
                    favouriteState.value.data?.toString()?.contains(songNameRes) ?: false
                IconButton(onClick = {
                    val song = FavouriteEntity(
                        songName = songNameRes,
                        songRes = mediaItems[index].audioResId
                    )
                    if (isFavourite) {
                        favouriteViewModel.deleteSong(songNameRes)
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
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(id = R.drawable.baseline_more_horiz_24),
                        contentDescription = "more"
                    )
                }
            }
        }
    }
}
