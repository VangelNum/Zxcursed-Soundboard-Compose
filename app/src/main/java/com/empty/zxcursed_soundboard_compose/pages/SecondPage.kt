package com.empty.zxcursed_soundboard_compose.pages

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
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.empty.zxcursed_soundboard_compose.Person
import com.empty.zxcursed_soundboard_compose.R
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

private var media0: MediaPlayer? = null
private var media1: MediaPlayer? = null
private var media2: MediaPlayer? = null
private var media3: MediaPlayer? = null
private var media4: MediaPlayer? = null
private var media5: MediaPlayer? = null
private var media6: MediaPlayer? = null
private var media7: MediaPlayer? = null
private var media8: MediaPlayer? = null
private var media9: MediaPlayer? = null
private var media10: MediaPlayer? = null
private var media11: MediaPlayer? = null
private var media12: MediaPlayer? = null
private var media13: MediaPlayer? = null
private var media14: MediaPlayer? = null
private var media15: MediaPlayer? = null
private var media16: MediaPlayer? = null
private var media17: MediaPlayer? = null
private var media18: MediaPlayer? = null
private var media19: MediaPlayer? = null
private var media20: MediaPlayer? = null
private var media21: MediaPlayer? = null

@Destination
@Composable
fun Second(navigator: DestinationsNavigator) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current


    Column(
        modifier = Modifier.background(Color.Black)
    ) {
        Person(navigator = navigator,
            Color.White,
            Color.Green,
            Color.White,
            Color.White,
            scope,
            scaffoldState)



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
                            when (index) {
                                0 -> {
                                    media0 = MediaPlayer.create(context, R.raw.cursed2)
                                    media0?.start()
                                    media0?.setOnCompletionListener {
                                        media0?.reset()
                                        media0?.release()
                                        media0 = null
                                    }
                                }
                                1 -> {
                                    media1 = MediaPlayer.create(context, R.raw.cursed3)
                                    media1?.start()
                                    media1?.setOnCompletionListener {
                                        media1?.reset()
                                        media1?.release()
                                        media1 = null
                                    }
                                }
                                2 -> {
                                    media2 = MediaPlayer.create(context, R.raw.cursed4)
                                    media2?.start()
                                    media2?.setOnCompletionListener {
                                        media2?.reset()
                                        media2?.release()
                                        media2 = null
                                    }
                                }
                                3 -> {
                                    media3 = MediaPlayer.create(context, R.raw.cursed5)
                                    media3?.start()
                                    media3?.setOnCompletionListener {
                                        media3?.reset()
                                        media3?.release()
                                        media3 = null
                                    }
                                }
                                4 -> {
                                    media4 = MediaPlayer.create(context, R.raw.cursed6)
                                    media4?.start()
                                    media4?.setOnCompletionListener {
                                        media4?.reset()
                                        media4?.release()
                                        media4 = null
                                    }
                                }
                                5 -> {
                                    media5 = MediaPlayer.create(context, R.raw.cursed7)
                                    media5?.start()
                                    media5?.setOnCompletionListener {
                                        media5?.reset()
                                        media5?.release()
                                        media5 = null
                                    }
                                }
                                6 -> {
                                    media6 = MediaPlayer.create(context, R.raw.cursed8)
                                    media6?.start()
                                    media6?.setOnCompletionListener {
                                        media6?.reset()
                                        media6?.release()
                                        media6 = null
                                    }
                                }
                                7 -> {
                                    media7 = MediaPlayer.create(context, R.raw.cursed9)
                                    media7?.start()
                                    media7?.setOnCompletionListener {
                                        media7?.reset()
                                        media7?.release()
                                        media7 = null
                                    }
                                }
                                8 -> {
                                    media8 = MediaPlayer.create(context, R.raw.cursed10)
                                    media8?.start()
                                    media8?.setOnCompletionListener {
                                        media8?.reset()
                                        media8?.release()
                                        media8 = null
                                    }
                                }
                                9 -> {
                                    media9 = MediaPlayer.create(context, R.raw.cursed11)
                                    media9?.start()
                                    media9?.setOnCompletionListener {
                                        media9?.reset()
                                        media9?.release()
                                        media9 = null
                                    }
                                }
                                10 -> {
                                    media10 = MediaPlayer.create(context, R.raw.cursed14)
                                    media10?.start()
                                    media10?.setOnCompletionListener {
                                        media10?.reset()
                                        media10?.release()
                                        media10 = null
                                    }
                                }
                                11 -> {
                                    media11 = MediaPlayer.create(context, R.raw.cursed15)
                                    media11?.start()
                                    media11?.setOnCompletionListener {
                                        media11?.reset()
                                        media11?.release()
                                        media11 = null
                                    }
                                }
                                12 -> {
                                    media12 = MediaPlayer.create(context, R.raw.cursed16)
                                    media12?.start()
                                    media12?.setOnCompletionListener {
                                        media12?.reset()
                                        media12?.release()
                                        media12 = null
                                    }
                                }
                                13 -> {
                                    media13 = MediaPlayer.create(context, R.raw.cursed24)
                                    media13?.start()
                                    media13?.setOnCompletionListener {
                                        media13?.reset()
                                        media13?.release()
                                        media13 = null
                                    }
                                }
                                14 -> {
                                    media14 = MediaPlayer.create(context, R.raw.cursed35)
                                    media14?.start()
                                    media14?.setOnCompletionListener {
                                        media14?.reset()
                                        media14?.release()
                                        media14 = null
                                    }
                                }
                                15 -> {
                                    media15 = MediaPlayer.create(context, R.raw.cursed36)
                                    media15?.start()
                                    media15?.setOnCompletionListener {
                                        media15?.reset()
                                        media15?.release()
                                        media15 = null
                                    }
                                }
                                16 -> {
                                    media16 = MediaPlayer.create(context, R.raw.cursed37)
                                    media16?.start()
                                    media16?.setOnCompletionListener {
                                        media16?.reset()
                                        media16?.release()
                                        media16 = null
                                    }
                                }
                                17 -> {
                                    media17 = MediaPlayer.create(context, R.raw.cursed38)
                                    media17?.start()
                                    media17?.setOnCompletionListener {
                                        media17?.reset()
                                        media17?.release()
                                        media17 = null
                                    }
                                }
                                18 -> {
                                    media18 = MediaPlayer.create(context, R.raw.cursed39)
                                    media18?.start()
                                    media18?.setOnCompletionListener {
                                        media18?.reset()
                                        media18?.release()
                                        media18 = null
                                    }
                                }
                                19 -> {
                                    media19 = MediaPlayer.create(context, R.raw.cursed40)
                                    media19?.start()
                                    media19?.setOnCompletionListener {
                                        media19?.reset()
                                        media19?.release()
                                        media19 = null
                                    }
                                }
                                20 -> {
                                    media20 = MediaPlayer.create(context, R.raw.cursed42)
                                    media20?.start()
                                    media20?.setOnCompletionListener {
                                        media20?.reset()
                                        media20?.release()
                                        media20 = null
                                    }
                                }
                                21 -> {
                                    media21 = MediaPlayer.create(context, R.raw.cursed41)
                                    media21?.start()
                                    media21?.setOnCompletionListener {
                                        media21?.reset()
                                        media21?.release()
                                        media21 = null
                                    }
                                }


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
            fun musicStop() {
                if (media0?.isPlaying == true && media0 != null) {
                    media0?.stop()
                    media0?.reset()
                    media0?.release()
                    media0 = null

                }
                if (media1?.isPlaying == true && media1 != null) {
                    media1?.stop()
                    media1?.reset()
                    media1?.release()
                    media1 = null
                }
                if (media2?.isPlaying == true && media2 != null) {
                    media2?.stop()
                    media2?.reset()
                    media2?.release()
                    media2 = null
                }
                if (media3?.isPlaying == true && media3 != null) {
                    media3?.stop()
                    media3?.reset()
                    media3?.release()
                    media3 = null
                }
                if (media4?.isPlaying == true && media4 != null) {
                    media4?.stop()
                    media4?.reset()
                    media4?.release()
                    media4 = null
                }
                if (media5?.isPlaying == true && media5 != null) {
                    media5?.stop()
                    media5?.reset()
                    media5?.release()
                    media5 = null
                }
                if (media6?.isPlaying == true && media6 != null) {
                    media6?.stop()
                    media6?.reset()
                    media6?.release()
                    media6 = null
                }
                if (media7?.isPlaying == true && media7 != null) {
                    media7?.stop()
                    media7?.reset()
                    media7?.release()
                    media7 = null
                }
                if (media8?.isPlaying == true && media8 != null) {
                    media8?.stop()
                    media8?.reset()
                    media8?.release()
                    media8 = null
                }
                if (media9?.isPlaying == true && media9 != null) {
                    media9?.stop()
                    media9?.reset()
                    media9?.release()
                    media9 = null
                }
                if (media10?.isPlaying == true && media10 != null) {
                    media10?.stop()
                    media10?.reset()
                    media10?.release()
                    media10 = null
                }
                if (media11?.isPlaying == true && media11 != null) {
                    media11?.stop()
                    media11?.reset()
                    media11?.release()
                    media11 = null
                }
                if (media12?.isPlaying == true && media12 != null) {
                    media12?.stop()
                    media12?.reset()
                    media12?.release()
                    media12 = null
                }
                if (media13?.isPlaying == true && media13 != null) {
                    media13?.stop()
                    media13?.reset()
                    media13?.release()
                    media13 = null
                }
                if (media14?.isPlaying == true && media14 != null) {
                    media14?.stop()
                    media14?.reset()
                    media14?.release()
                    media14 = null
                }
                if (media15?.isPlaying == true && media15 != null) {
                    media15?.stop()
                    media15?.reset()
                    media15?.release()
                    media15 = null
                }
                if (media16?.isPlaying == true && media16 != null) {
                    media16?.stop()
                    media16?.reset()
                    media16?.release()
                    media16 = null
                }
                if (media17?.isPlaying == true && media17 != null) {
                    media17?.stop()
                    media17?.reset()
                    media17?.release()
                    media17 = null
                }
                if (media18?.isPlaying == true && media18 != null) {
                    media18?.stop()
                    media18?.reset()
                    media18?.release()
                    media18 = null
                }
                if (media19?.isPlaying == true && media19 != null) {
                    media19?.stop()
                    media19?.reset()
                    media19?.release()
                    media19 = null
                }
                if (media20?.isPlaying == true && media20 != null) {
                    media20?.stop()
                    media20?.reset()
                    media20?.release()
                    media20 = null
                }
                if (media21?.isPlaying == true && media21 != null) {
                    media21?.stop()
                    media21?.reset()
                    media21?.release()
                    media21 = null
                }
            }
            when (event) {
                Lifecycle.Event.ON_CREATE -> {
                    //something to do
                    Log.d("Tag", "ONCREATE")
                }
                Lifecycle.Event.ON_RESUME -> {
                    //something to do
                    Log.d("Tag", "ONRESUME")
                }
                Lifecycle.Event.ON_DESTROY -> {
                    //MusicStop()
                    Log.d("Tag", "ONDESTROY")
                }
                Lifecycle.Event.ON_PAUSE -> {
                    musicStop()
                    Log.d("Tag", "ONPAUSE")
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


}

