package com.zxcursedsoundboard.apk.lifecycle

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

@Composable
fun LifeOfApplication(context: Context) {
    @Composable
    fun ComposableLifecycle(
        lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
        onEvent: (LifecycleOwner, Lifecycle.Event) -> Unit,
    ) {
        DisposableEffect(lifecycleOwner) {
            val observer = LifecycleEventObserver { source, event ->
                onEvent(source, event)
            }
            lifecycleOwner.lifecycle.addObserver(observer)
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }
    }

    ComposableLifecycle { _, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                //something to do
                Log.d("Tag", "CREATE")
            }
            Lifecycle.Event.ON_RESUME -> {
                //something to do
                Log.d("Tag", "RESUME")
            }
            Lifecycle.Event.ON_DESTROY -> {
                //MusicStop()
                Log.d("Tag", "DESTROY")
            }
            Lifecycle.Event.ON_PAUSE -> {
                //musicStop()
                Log.d("Tag", "PAUSE")
            }
            Lifecycle.Event.ON_STOP -> {
                //MusicStop()
                Log.d("Tag", "ONSTOP")
            }
            else -> {
                //do something
            }
        }
    }
}