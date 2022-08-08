package com.empty.zxcursed_soundboard_compose.ui.theme

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.empty.zxcursed_soundboard_compose.R

@Composable
fun First(context: Context) {

    val Media2: MediaPlayer = MediaPlayer.create(context, R.raw.cursed12)
    val Media3: MediaPlayer = MediaPlayer.create(context, R.raw.cursed43)
    val Media4: MediaPlayer = MediaPlayer.create(context, R.raw.cursed45)
    val Media5: MediaPlayer = MediaPlayer.create(context, R.raw.cursed47)
    val Media6: MediaPlayer = MediaPlayer.create(context, R.raw.cursed49)
    val Media7: MediaPlayer = MediaPlayer.create(context, R.raw.cursed51)
    val Media8: MediaPlayer = MediaPlayer.create(context, R.raw.cursed53)
    val Media9: MediaPlayer = MediaPlayer.create(context, R.raw.cursed55)
    val Media10: MediaPlayer = MediaPlayer.create(context, R.raw.cursed57)
    val Media11: MediaPlayer = MediaPlayer.create(context, R.raw.cursed13)
    val Media12: MediaPlayer = MediaPlayer.create(context, R.raw.cursed44)
    val Media13: MediaPlayer = MediaPlayer.create(context, R.raw.cursed46)
    val Media14: MediaPlayer = MediaPlayer.create(context, R.raw.cursed48)
    val Media15: MediaPlayer = MediaPlayer.create(context, R.raw.cursed50)
    val Media16: MediaPlayer = MediaPlayer.create(context, R.raw.cursed52)
    val Media17: MediaPlayer = MediaPlayer.create(context, R.raw.cursed54)
    val Media18: MediaPlayer = MediaPlayer.create(context, R.raw.cursed56)
    val Media19: MediaPlayer = MediaPlayer.create(context, R.raw.cursed58)

        Box() {
            Image(painter = painterResource(id = R.drawable.photo11),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(25.dp, 25.dp, 0.dp, 0.dp)),
                contentScale = ContentScale.Crop
            )
            val list = listOf(
                context.getString(R.string.uchimenyabistro),
                context.getString(R.string.anekdotbryda),
                context.getString(R.string.nezachto),
                context.getString(R.string.menyaopyatybili),
                context.getString(R.string.nykakxochesh),
                context.getString(R.string.troiroditelynexleb),
                context.getString(R.string.chtomnezaetobudet),
                context.getString(R.string.yasnoponyanto),
                context.getString(R.string.yachitatneumeu),
                context.getString(R.string.nyplis),
                context.getString(R.string.discord),
                context.getString(R.string.mama),
                context.getString(R.string.akkaunt),
                context.getString(R.string.rospis),
                context.getString(R.string.kroshka),
                context.getString(R.string.chtotiskazal),
                context.getString(R.string.yastesnyays),
                context.getString(R.string.shmotki)
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
                        when (index) {
                            0 -> Media2.start()
                            1 -> Media3.start()
                            2 -> Media4.start()
                            3 -> Media5.start()
                            4 -> Media6.start()
                            5 -> Media7.start()
                            6 -> Media8.start()
                            7 -> Media9.start()
                            8 -> Media10.start()
                            9 -> Media11.start()
                            10 -> Media12.start()
                            11 -> Media13.start()
                            12 -> Media14.start()
                            13 -> Media15.start()
                            14 -> Media16.start()
                            15 -> Media17.start()
                            16 -> Media18.start()
                            17 -> Media19.start()
                        }
                                  },
                        colors = ButtonDefaults.buttonColors(Color(0xDA000000)),
                        shape = RoundedCornerShape(15.dp),
                        modifier = Modifier.height(70.dp).border(1.dp,Color(0x79D6C9C9), RoundedCornerShape(15.dp)),
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

private fun FirstPageClicker(inx: Int, context: Context, media: MediaPlayer) {
    media.start()
    //media = MediaPlayer.create(context, R.raw.cursed6)
    //Media6.start()
   /*
    val mSoundPool = SoundPool.Builder()
        .setMaxStreams(5)
        .build()
    var soundId2 = 1
    soundId2 = mSoundPool.load(context, R.raw.cursed2, 1)
    mSoundPool.play(soundId2, 1F, 1F, 1, 0, 1F)

    */
}