package com.zxcursedsoundboard.apk.feature_Test

import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun MediaControl(startMedia: () -> Unit) {
    val context = LocalContext.current

    OutlinedButton(onClick = startMedia) {
        
    }
}

