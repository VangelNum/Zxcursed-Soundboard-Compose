package com.zxcursedsoundboard.apk.feature_test

import android.media.MediaPlayer
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zxcursedsoundboard.apk.core.common.Resource

@Composable
fun TestScreen(testViewModel: TestViewModel = hiltViewModel()) {
    val state = testViewModel.song.collectAsStateWithLifecycle()
    val mediaPlayer = MediaPlayer()
    when (state.value) {
        is Resource.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = state.value.message.toString())
            }
        }

        is Resource.Success -> {
            LazyColumn() {
                items(state.value.data!!) { song ->
                    OutlinedButton(onClick = {
                        mediaPlayer.apply {
                            reset()
                            setDataSource(song.audio)
                            setOnPreparedListener {
                                start()
                            }
                            prepareAsync()
                        }
                    }) {
                        Text(text = song.name)
                    }
                }

            }
        }

        is Resource.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}