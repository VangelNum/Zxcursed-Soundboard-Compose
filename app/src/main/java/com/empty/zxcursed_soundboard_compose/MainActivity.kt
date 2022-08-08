package com.empty.zxcursed_soundboard_compose

import android.content.Context
import android.content.res.Resources
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.res.TypedArrayUtils.getText
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.empty.zxcursed_soundboard_compose.ui.theme.First

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = this
            Column(modifier = Modifier.background(Color.Black)) {
                ItemsPerson(context)
                //Buttons(6)
                First(context)
            }


        }
    }

}

@Composable
private fun ItemsPerson(context: Context) {
        Row(
            modifier = Modifier
                .padding(5.dp)) {

            val imageLoader = ImageLoader.Builder(context)
                .components {
                    if (SDK_INT >= 28) {
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
                    .border(1.dp, Color.White, CircleShape),
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
                    .border(1.dp, Color.White, CircleShape),
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
                    .border(1.dp, Color.White, CircleShape),
                contentScale = ContentScale.Crop,
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
                    .border(1.dp, Color.White, CircleShape),
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
                    .padding(10.dp),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.width(5.dp))
        }
        Spacer(modifier = Modifier.height(5.dp))

    }


@Composable
private fun RowIcons() {
    Box {
        Row {
            Image(painter = painterResource(id = R.drawable.photo8),
                contentDescription = null,
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp))
        }
    }

}

@Composable
fun Buttons(count: Int) {
    Box {
        val image: Painter = painterResource(id = R.drawable.photo11)
        Image(painter = image, contentDescription = "")
    }
    Box {
        LazyVerticalGrid(columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(10.dp)


        ) {
            items(count) { i ->

                Button(onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(Color(0xFFBB86FC)),
                    modifier = Modifier.height(70.dp),
                    shape = RoundedCornerShape(15.dp)) {
                    Text(text = "chekc")
                }


            }
        }
    }

}
