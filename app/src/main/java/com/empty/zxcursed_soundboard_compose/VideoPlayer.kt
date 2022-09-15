package com.empty.zxcursed_soundboard_compose

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.ui.StyledPlayerView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@com.ramcosta.composedestinations.annotation.Destination
@Composable
fun VideoPlayerScreen() {

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Box(modifier = Modifier.fillMaxSize()) {
        var showSnackbar by remember {
            mutableStateOf(true)
        }
        if (showSnackbar) {
          Snackbar(modifier = Modifier.align(Alignment.Center),
                action = {
                    Text(text = "Action",
                        color = Color(0xffCE93D8),
                        modifier = Modifier.clickable {
                            showSnackbar = false
                        }
                    )
                }
          ) {
                Text("Message", textAlign = TextAlign.Center,color = Color.White)
            }
        }
    }



//    Scaffold (
//        scaffoldState = scaffoldState
//    ) {
//        it.calculateBottomPadding()
//        LaunchedEffect(key1 = Unit) {
//            scope.launch {
//               scaffoldState.snackbarHostState.showSnackbar(
//                   message = "check"
//               )
//            }
//        }
//
//
//    }

    val context = LocalContext.current
    var playWhenReady by remember { mutableStateOf(true) }
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri("https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/i/J2HFVonfmbbwAA"))
            playWhenReady = !playWhenReady
            prepare()
            play()
        }
    }
    DisposableEffect(key1 = exoPlayer) {
        onDispose {
            exoPlayer.release()
        }
    }

    AndroidView(modifier = Modifier
        .fillMaxSize(), factory = {
        StyledPlayerView(context).apply {
            player = exoPlayer
            useController = false
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
      }
    )
}