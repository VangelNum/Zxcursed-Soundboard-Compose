package com.zxcursedsoundboard.apk.feature_favourite.presentation

import android.media.MediaPlayer
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.zxcursedsoundboard.apk.core.common.Resource
import com.zxcursedsoundboard.apk.feature_favourite.data.model.FavouriteEntity

@Composable
fun FavouriteScreen(
    favouriteViewModel: FavouriteViewModel = hiltViewModel()
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
            FavouriteScreenItems(state.value.data!!)
        }
    }
}

@Composable
fun FavouriteScreenItems(data: List<FavouriteEntity>) {
    val context = LocalContext.current
    LazyColumn() {
        items(data) {
            OutlinedButton(onClick = {
                val media0: MediaPlayer? = MediaPlayer.create(
                    context,
                    it.songAudioRes
                )
                media0?.start()
            }) {
                Text(text = it.songName)
            }
        }
    }
}
