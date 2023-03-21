package com.zxcursedsoundboard.apk

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun ZxcursedSoundScreen() {
    val items = remember {
        mutableStateListOf(
            R.string.CS,
            R.string.kalim,
            R.string.ulitka,
            R.string.sorokonojka,
            R.string.pokorit,
            R.string.sinandshcool,
            R.string.grandmother,
            R.string.online,
            R.string.simpledimple,
            R.string.femlove,
            R.string.in_my_mind,
            R.string.bankai,
            R.string.killua,
            R.string.neverenough,
            R.string.quinque,
            R.string.fxckoff,
        )
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.photo12),
            contentDescription = "zxcursed",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items) { text ->
                OutlinedButton(
                    shape = RoundedCornerShape(25.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .alpha(0.9f),
                    onClick = {

                    },
                    border = BorderStroke(1.dp, Color.Gray),
                ) {
                    Text(
                        text = stringResource(id = text).uppercase(),
                        style = MaterialTheme.typography.h4
                    )
                }
            }
        }
    }

}