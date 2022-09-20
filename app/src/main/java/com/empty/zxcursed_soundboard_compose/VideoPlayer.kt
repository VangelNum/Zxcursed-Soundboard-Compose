package com.empty.zxcursed_soundboard_compose

import android.net.Uri
import android.widget.VideoView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.empty.zxcursed_soundboard_compose.destinations.FirstDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination
@Composable
fun VideoPlayerScreen(navigator: DestinationsNavigator) {
    val context = LocalContext.current
    AndroidView(
        factory = {
            VideoView(it, null).apply {
                setVideoURI(Uri.parse("android.resource://${context.packageName}/${R.raw.videoplayback}"))
                start()
                setOnCompletionListener {
                    navigator.navigate(FirstDestination)
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    )
//
//    val scope = rememberCoroutineScope()
//    val scaffoldState = rememberScaffoldState()
//
//    Box(modifier = Modifier.fillMaxSize()) {
//        var showSnackbar by remember {
//            mutableStateOf(true)
//        }
//        if (showSnackbar) {
//          Snackbar(modifier = Modifier.align(Alignment.Center),
//                action = {
//                    Text(text = "Action",
//                        color = Color(0xffCE93D8),
//                        modifier = Modifier.clickable {
//                            showSnackbar = false
//                        }
//                    )
//                }
//          ) {
//                Text("Message", textAlign = TextAlign.Center,color = Color.White)
//            }
//        }
//    }
//
//
//    val context = LocalContext.current
//    var playWhenReady by remember { mutableStateOf(true) }
//    val exoPlayer = remember {
//        ExoPlayer.Builder(context).build().apply {
//            setMediaItem(MediaItem.fromUri("https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/i/J2HFVonfmbbwAA"))
//            playWhenReady = !playWhenReady
//            prepare()
//            play()
//        }
//    }
//    DisposableEffect(key1 = exoPlayer) {
//        onDispose {
//            exoPlayer.release()
//        }
//    }
//
//    AndroidView(modifier = Modifier
//        .fillMaxSize(), factory = {
//        StyledPlayerView(context).apply {
//            player = exoPlayer
//            useController = false
//            layoutParams = FrameLayout.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//            )
//        }
//      }
//    )

}