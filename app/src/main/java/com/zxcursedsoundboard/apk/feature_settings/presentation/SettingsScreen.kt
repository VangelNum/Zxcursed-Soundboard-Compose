package com.zxcursedsoundboard.apk.feature_settings.presentation

import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.zxcursedsoundboard.apk.R
import com.zxcursedsoundboard.apk.core.data.model.MediaItems
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SettingsScreen(
    currentBackground: MutableState<Int>,
    sharedPreferences: SharedPreferences,
    currentSong: MediaItems
) {
    val backgrounds = listOf(
        R.drawable.photo11,
        R.drawable.photo12,
        R.drawable.photo8,
        R.drawable.nenado,
        R.drawable.never_enough,
        R.drawable.photo13,
        R.drawable.photo18,
        R.drawable.madmyazel
    )
    val onBackgroundSelected: (Int) -> Unit = { background ->
        currentBackground.value = background
        sharedPreferences.edit().putInt("background", background).apply()
    }
    val pagerState = rememberPagerState()
    Column(
        modifier = Modifier.fillMaxSize().padding(if (currentSong.author != "") PaddingValues(
            bottom = 96.dp
        ) else PaddingValues(0.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            count = backgrounds.size,
            state = pagerState,
            contentPadding = PaddingValues(start = 32.dp, end = 32.dp, top = 16.dp),

            ) { page ->
            Box(contentAlignment = Alignment.BottomEnd) {
                Card(
                    shape = MaterialTheme.shapes.extraLarge,
                    modifier = Modifier
                        .graphicsLayer {
                            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                            lerp(
                                start = 0.9f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            ).also { scale ->
                                scaleX = scale
                                scaleY = scale
                            }
                            alpha = lerp(
                                start = 0.5f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                        }
                ) {
                    Image(
                        painter = painterResource(id = backgrounds[page]),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxHeight(0.9f)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop,
                    )
                }
                if (currentBackground.value != backgrounds[page]) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp, end = 16.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(onClick = { onBackgroundSelected(backgrounds[page]) }) {
                            Text(text = stringResource(id = R.string.choose))
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_check_circle_outline_24),
                                contentDescription = "apply"
                            )
                        }
                    }
                } else {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp, end = 16.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
                        ) {
                            Text(text = stringResource(id = R.string.choose_apply))
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_check_circle_outline_24),
                                contentDescription = "apply",
                            )
                        }
                    }
                }
            }

        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            HorizontalPagerIndicator(
                activeColor = Color.Green,
                pagerState = pagerState,
            )
        }
    }
}

