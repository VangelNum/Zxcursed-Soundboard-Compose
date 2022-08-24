package com.empty.zxcursed_soundboard_compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter


@Composable
fun DrawerLayout(imageLoader: ImageLoader) {
    Card(
        shape = CircleShape,
        elevation = 10.dp,
        modifier = Modifier
            .padding(25.dp, 25.dp, 0.dp, 0.dp)
            .size(80.dp),
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = R.drawable.zxcursed,
                imageLoader = imageLoader),
            contentDescription = null
        )
    }
    Text("Zxcursed SoundBoard",
        color = Color.Black,
        fontSize = 20.sp,
        fontFamily = FontFamily(Font(R.font.jostregular)),
        modifier = Modifier.padding(25.dp, 5.dp, 0.dp, 0.dp)
    )
    Text("VangelNum",
        color = Color.Black,
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.atypdisplaynew)),
        modifier = Modifier.padding(25.dp, 0.dp, 0.dp, 15.dp)
    )

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.DarkGray)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .padding(25.dp, 25.dp, 25.dp, 0.dp)) {
            Row {
                OutlinedButton(onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .border(1.dp, Color.Black,
                            RoundedCornerShape(15.dp)),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(
                        0xB024231F))
                ) {
                    Image(painter = painterResource(id = R.drawable.ic_baseline_message_24),
                        contentDescription = "null"
                    )
                    Text(text = "Контакты", modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                        color = Color.White
                    )
                }

            }
            Row {
                OutlinedButton(onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .border(1.dp, Color.Black,
                            RoundedCornerShape(15.dp)),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(
                        0xB024231F))
                ) {
                    Image(painter = painterResource(id = R.drawable.ic_baseline_share_24),
                        contentDescription = "null"
                    )
                    Text(text = "Поделиться", modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                        color = Color.White
                    )
                }

            }
            Row {
                OutlinedButton(onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .border(1.dp, Color.Black,
                            RoundedCornerShape(15.dp)),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(
                        0xB024231F))
                ) {
                    Image(painter = painterResource(id = R.drawable.ic_baseline_star_24),
                        contentDescription = "null"
                    )
                    Text(text = "Оценить", modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                        color = Color.White
                    )
                }

            }
        }
    }
}