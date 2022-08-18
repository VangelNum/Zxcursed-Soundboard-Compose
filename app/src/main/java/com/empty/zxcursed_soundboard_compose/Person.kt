package com.empty.zxcursed_soundboard_compose

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.empty.zxcursed_soundboard_compose.destinations.FirstDestination
import com.empty.zxcursed_soundboard_compose.destinations.SecondDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Destination
@Composable
fun Person(
    navigator: DestinationsNavigator,
    color1: Color,
    color2: Color,
    color3: Color, color4: Color, scope: CoroutineScope, scaffoldState: ScaffoldState,
) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .padding(5.dp)
    ) {
        val imageLoader = ImageLoader.Builder(context)
            .components {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()
        Spacer(modifier = Modifier.width(5.dp))

        Image(
            painter = painterResource(id = R.drawable.photo8),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .weight(1f)
                .aspectRatio(1f)
                .border(1.dp, color1, CircleShape)
                .clickable(onClick = { navigator.navigate(FirstDestination) }),
            contentScale = ContentScale.Crop,

            )
        Spacer(modifier = Modifier.width(5.dp))

        Image(
            painter = rememberAsyncImagePainter(model = R.drawable.zxc2,
                imageLoader = imageLoader),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .weight(1f)
                .aspectRatio(1f)
                .border(1.dp, color2, CircleShape)
                .clickable(onClick = {
                    navigator.navigate(SecondDestination)
                }),
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.width(5.dp))
        Image(
            painter = painterResource(id = R.drawable.photo12),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .weight(1f)
                .aspectRatio(1f)
                .border(1.dp, color3, CircleShape),
            contentScale = ContentScale.Crop

        )
        Spacer(modifier = Modifier.width(5.dp))
        Image(
            painter = rememberAsyncImagePainter(model = R.drawable.zxc,
                imageLoader = imageLoader),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .weight(1f)
                .aspectRatio(1f)
                .border(1.dp, color4, CircleShape),
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.width(5.dp))
        Image(
            painter = painterResource(id = R.drawable.photo22),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .weight(1f)
                .aspectRatio(1f)
                .border(1.dp, Color.White, CircleShape)
                .background(Color.Black)
                .padding(10.dp)
                .clickable {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                },
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.width(5.dp))

    }
    Spacer(modifier = Modifier.height(5.dp))


}
