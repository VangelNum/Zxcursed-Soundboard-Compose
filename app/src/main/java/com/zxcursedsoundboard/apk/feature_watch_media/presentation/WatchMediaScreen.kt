package com.zxcursedsoundboard.apk.feature_watch_media.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
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
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zxcursedsoundboard.apk.R
import com.zxcursedsoundboard.apk.core.presentation.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchMediaScreen(
    mainViewModel: MainViewModel,
    isPlaying: State<Boolean>,
    songName: State<Int>,
    songImage: State<Int>,
    songAuthor: State<Int>,
    duration: State<Int>,
    currentTimeMedia: State<Int>,
    looping: State<Boolean>
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Card(shape = MaterialTheme.shapes.extraLarge) {
            Image(
                painter = painterResource(id = songImage.value),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(0.5f)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = stringResource(id = songAuthor.value),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = stringResource(id = songName.value),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .alpha(0.5f)
                .padding(start = 4.dp)
        )
        CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
            Slider(
                value = currentTimeMedia.value.toFloat(),
                onValueChange = {
                    mainViewModel.setCurrentTime(it.toInt())
                },
                valueRange = 0f..duration.value.toFloat(),
                modifier = Modifier.fillMaxWidth(1f)
            )
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp)) {
            Text(
                text = currentTimeMedia.value.formatTime(),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(text = duration.value.formatTime(), style = MaterialTheme.typography.titleMedium)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                mainViewModel.setLopping()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_loop_24),
                    contentDescription = "looping",
                    modifier = Modifier.size(30.dp),
                    tint = if (looping.value) MaterialTheme.colorScheme.primaryContainer else LocalContentColor.current
                )
            }
            IconButton(onClick = { mainViewModel.playPreviousMedia(context) }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_skip_previous_24),
                    contentDescription = "previous",
                    modifier = Modifier.size(30.dp)
                )
            }
            if (isPlaying.value) {
                IconButton(onClick = { mainViewModel.pause() }, modifier = Modifier.size(60.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_pause_circle_24),
                        contentDescription = "play/pause",
                        modifier = Modifier.size(60.dp)
                    )
                }
            } else {
                IconButton(onClick = { mainViewModel.play() }, modifier = Modifier.size(60.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_play_circle_24),
                        contentDescription = "play/pause",
                        modifier = Modifier.size(60.dp)
                    )
                }
            }
            IconButton(onClick = { mainViewModel.playNextMedia(context) }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_skip_next_24),
                    contentDescription = "next",
                    modifier = Modifier.size(30.dp)
                )
            }
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_format_list_bulleted_24),
                    contentDescription = "listOfMusic",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}

fun Int.formatTime(): String {
    val durationOfMedia = this / 1000
    val minutes = durationOfMedia / 60
    val seconds = durationOfMedia % 60
    return String.format("%02d:%02d", minutes, seconds)
}
