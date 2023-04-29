package com.zxcursedsoundboard.apk.feature_additionally

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.zxcursedsoundboard.apk.R

@Composable
fun ZxcursedBackScreen() {
    val context = LocalContext.current
    val exoPlayer = ExoPlayer.Builder(context).build()
    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = { context ->
                StyledPlayerView(context).apply {
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
                    player = exoPlayer
                    layoutParams = FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )

                }
            },
        )
        val videoUri = "android.resource://${context.packageName}/${R.raw.videoplayback}"
        val mediaItem = MediaItem.fromUri(videoUri)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.play()
        DisposableEffect(key1 = exoPlayer) {
            onDispose {
                exoPlayer.release()
            }
        }
    }
}