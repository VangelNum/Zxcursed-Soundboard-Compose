package com.empty.zxcursed_soundboard_compose.Pages

import android.media.MediaPlayer
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.empty.zxcursed_soundboard_compose.Person
import com.empty.zxcursed_soundboard_compose.R
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun Second(navigator: DestinationsNavigator) {
    Column(
        modifier = Modifier.background(Color.Black)
    ) {
        Person(navigator = navigator)

        val context = LocalContext.current
        Box {
            Image(painter = painterResource(id = R.drawable.photo11),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(25.dp, 25.dp, 0.dp, 0.dp)),
                contentScale = ContentScale.Crop
            )
            val list = listOf(
                context.getString(R.string.pivo),
                context.getString(R.string.molchat),
                context.getString(R.string.traxat),
                context.getString(R.string.pikaper),
                context.getString(R.string.sydalut),
                context.getString(R.string.madmyazel),
                context.getString(R.string.chtoetoblyat),
                context.getString(R.string.spasibo),
                context.getString(R.string.denegnet),
                context.getString(R.string.minuspivo),
                context.getString(R.string.anekdot),
                context.getString(R.string.dirkavsire),
                context.getString(R.string.buyback),
                context.getString(R.string.kvakvakva),
                context.getString(R.string._0_10),
                context.getString(R.string.mamont),
                context.getString(R.string.gerichabi),
                context.getString(R.string.jenarojala),
                context.getString(R.string.onrad),
                context.getString(R.string.worstsf),
                context.getString(R.string.fivestar),
                context.getString(R.string.dumaldendi)
            )

            LazyVerticalGrid(columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(10.dp),
                modifier = Modifier
                    .clip(RoundedCornerShape(25.dp, 25.dp, 0.dp, 0.dp))


            ) {
                items(list.size) { index ->
                    OutlinedButton(
                        onClick = {


                            val Media12: MediaPlayer = MediaPlayer.create(context, R.raw.cursed14)
                            val Media13: MediaPlayer = MediaPlayer.create(context, R.raw.cursed15)
                            val Media14: MediaPlayer = MediaPlayer.create(context, R.raw.cursed16)
                            val Media15: MediaPlayer = MediaPlayer.create(context, R.raw.cursed24)
                            val Media16: MediaPlayer = MediaPlayer.create(context, R.raw.cursed35)
                            val Media17: MediaPlayer = MediaPlayer.create(context, R.raw.cursed36)
                            val Media18: MediaPlayer = MediaPlayer.create(context, R.raw.cursed37)
                            val Media19: MediaPlayer = MediaPlayer.create(context, R.raw.cursed38)
                            val Media20: MediaPlayer = MediaPlayer.create(context, R.raw.cursed39)
                            val Media21: MediaPlayer = MediaPlayer.create(context, R.raw.cursed40)
                            val Media22: MediaPlayer = MediaPlayer.create(context, R.raw.cursed42)
                            val Media23: MediaPlayer = MediaPlayer.create(context, R.raw.cursed41)

                            when (index) {

                                0 -> {
                                    val Media2: MediaPlayer = MediaPlayer.create(context, R.raw.cursed2)
                                    Media2.start()
                                    Log.d("TAG","cehck")
                                }
                                1 -> {
                                    val Media3: MediaPlayer = MediaPlayer.create(context, R.raw.cursed3)
                                    Media3.start()

                                }

                                2 -> {

                                    val Media4: MediaPlayer = MediaPlayer.create(context, R.raw.cursed4)
                                    Media4.start()
                                }
                                3 -> {
                                    val Media5: MediaPlayer = MediaPlayer.create(context, R.raw.cursed5)
                                    Media5.start()
                                }
                                4 -> {
                                    val Media6: MediaPlayer = MediaPlayer.create(context, R.raw.cursed6)
                                    Media6.start()
                                }
                                5 -> {
                                    val Media7: MediaPlayer = MediaPlayer.create(context, R.raw.cursed7)
                                    Media7.start()
                                }
                                6 -> {
                                    val Media8: MediaPlayer = MediaPlayer.create(context, R.raw.cursed8)
                                    Media8.start()
                                }
                                7 -> {
                                    val Media9: MediaPlayer = MediaPlayer.create(context, R.raw.cursed9)
                                    Media9.start()
                                }
                                8 -> {
                                    val Media10: MediaPlayer = MediaPlayer.create(context, R.raw.cursed10)
                                    Media10.start()
                                }
                                9 -> {
                                    val Media11: MediaPlayer = MediaPlayer.create(context, R.raw.cursed11)
                                    Media11.start()
                                }


                                10 -> Media12.start()
                                11 -> Media13.start()
                                12 -> Media14.start()
                                13 -> Media15.start()
                                14 -> Media16.start()
                                15 -> Media17.start()
                                16 -> Media18.start()
                                17 -> Media19.start()
                                18 -> Media20.start()
                                19 -> Media21.start()
                                20 -> Media22.start()
                                21 -> Media23.start()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(Color(0xDA000000)),
                        shape = RoundedCornerShape(15.dp),
                        //0xDA000000
                        //Color(0x79D6C9C9
                        modifier = Modifier
                            .height(70.dp)
                            .border(1.dp, Color(0x79D6C9C9), RoundedCornerShape(15.dp)),
                    ) {
                        val fonts = FontFamily(Font(R.font.ubuntulight))
                        Text(
                            list[index].uppercase(),
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center,
                            fontFamily = fonts,
                            color = Color.White
                        )
                    }

                }
            }
        }
    }

}