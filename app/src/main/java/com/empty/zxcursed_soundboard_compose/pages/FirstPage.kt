package com.empty.zxcursed_soundboard_compose.pages

import android.app.DownloadManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.media.MediaPlayer
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.empty.zxcursed_soundboard_compose.tools.MyButton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch


var media0: MediaPlayer? = null
var media1: MediaPlayer? = null
var media2: MediaPlayer? = null
var media3: MediaPlayer? = null
var media4: MediaPlayer? = null
var media5: MediaPlayer? = null
var media6: MediaPlayer? = null
var media7: MediaPlayer? = null
var media8: MediaPlayer? = null
var media9: MediaPlayer? = null
var media10: MediaPlayer? = null
var media11: MediaPlayer? = null
var media12: MediaPlayer? = null
var media13: MediaPlayer? = null
var media14: MediaPlayer? = null
var media15: MediaPlayer? = null
var media16: MediaPlayer? = null
var media17: MediaPlayer? = null
var media18: MediaPlayer? = null
var media19: MediaPlayer? = null
var media20: MediaPlayer? = null
var media21: MediaPlayer? = null


@Destination(start = true)
@Composable
fun First(navigator: DestinationsNavigator) {
    Column(
        modifier = Modifier.background(Color.Black)
    ) {
        Person(navigator = navigator, Color.Green, Color.White, Color.White, Color.White)
        val context = LocalContext.current
        val fonts = FontFamily(Font(R.font.ubuntulight))
        Box() {
            Image(painter = painterResource(id = R.drawable.photo11),
                contentDescription = null,
                modifier = Modifier

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
            val listState = rememberLazyGridState()
            val coroutineScope = rememberCoroutineScope()
            LazyVerticalGrid(columns = GridCells.Fixed(2),
                state = listState,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(10.dp,10.dp,10.dp,100.dp),
                modifier = Modifier
                    .clip(RoundedCornerShape(25.dp, 25.dp, 0.dp, 0.dp))

            ) {
                items(list.size) { index ->
                    var expanded by remember { mutableStateOf(false) }
                    Box {
                        MaterialTheme(shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(
                            50.dp))) {
                            DropdownMenu(
                                expanded = expanded,
                                modifier = Modifier
                                    .background(Color.Black)
                                    .border(1.dp, Color.Cyan, CircleShape),
                                onDismissRequest = { expanded = false }
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.size(150.dp, 40.dp)
                                ) {
                                    Icon(

                                        painter = painterResource(R.drawable.ic_baseline_download_24),
                                        contentDescription = "print",
                                        modifier = Modifier
                                            .padding(5.dp, 5.dp, 0.dp, 0.dp)
                                            .size(18.dp)
                                    )

                                    Text(stringResource(R.string.download),
                                        fontSize = 18.sp,
                                        textAlign = TextAlign.Center,
                                        fontFamily = fonts,
                                        color = Color.White,
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .clickable(onClick = {
                                                Download(index, context)
                                                expanded = false
                                            })
                                    )

                                }
                            }
                        }

                        MyButton(
                            onLongClick = {
                                expanded = true
                            },
                            colors = ButtonDefaults.buttonColors(Color(0xDA000000)),
                            shape = RoundedCornerShape(15.dp),
                            modifier = Modifier
                                .height(80.dp)
                                .fillMaxWidth()
                                .border(1.dp, Color(0x79D6C9C9), RoundedCornerShape(15.dp)),
                            onClick = {
                                when (index) {
                                    0 -> {
                                        media0 = MediaPlayer.create(context, R.raw.cursed12)
                                        media0?.start()
                                        media0?.setOnCompletionListener {
                                            media0?.reset()
                                            media0?.release()
                                            media0 = null
                                        }
                                    }
                                    1 -> {
                                        media1 = MediaPlayer.create(context, R.raw.cursed43)
                                        media1?.start()
                                        media1?.setOnCompletionListener {
                                            media1?.reset()
                                            media1?.release()
                                            media1 = null
                                        }
                                    }
                                    2 -> {
                                        media2 = MediaPlayer.create(context, R.raw.cursed45)
                                        media2?.start()
                                        media2?.setOnCompletionListener {
                                            media2?.reset()
                                            media2?.release()
                                            media2 = null
                                        }
                                    }
                                    3 -> {
                                        media3 = MediaPlayer.create(context, R.raw.cursed47)
                                        media3?.start()
                                        media3?.setOnCompletionListener {
                                            media3?.reset()
                                            media3?.release()
                                            media3 = null
                                        }
                                    }
                                    4 -> {
                                        media4 = MediaPlayer.create(context, R.raw.cursed49)
                                        media4?.start()
                                        media4?.setOnCompletionListener {
                                            media4?.reset()
                                            media4?.release()
                                            media4 = null
                                        }
                                    }
                                    5 -> {
                                        media5 = MediaPlayer.create(context, R.raw.cursed51)
                                        media5?.start()
                                        media5?.setOnCompletionListener {
                                            media5?.reset()
                                            media5?.release()
                                            media5 = null
                                        }
                                    }
                                    6 -> {
                                        media6 = MediaPlayer.create(context, R.raw.cursed53)
                                        media6?.start()
                                        media6?.setOnCompletionListener {
                                            media6?.reset()
                                            media6?.release()
                                            media6 = null
                                        }
                                    }
                                    7 -> {
                                        media7 = MediaPlayer.create(context, R.raw.cursed55)
                                        media7?.start()
                                        media7?.setOnCompletionListener {
                                            media7?.reset()
                                            media7?.release()
                                            media7 = null
                                        }
                                    }
                                    8 -> {
                                        media8 = MediaPlayer.create(context, R.raw.cursed57)
                                        media8?.start()
                                        media8?.setOnCompletionListener {
                                            media8?.reset()
                                            media8?.release()
                                            media8 = null
                                        }
                                    }
                                    9 -> {
                                        media9 = MediaPlayer.create(context, R.raw.cursed13)
                                        media9?.start()
                                        media9?.setOnCompletionListener {
                                            media9?.reset()
                                            media9?.release()
                                            media9 = null
                                        }
                                    }
                                    10 -> {
                                        media10 = MediaPlayer.create(context, R.raw.cursed44)
                                        media10?.start()
                                        media10?.setOnCompletionListener {
                                            media10?.reset()
                                            media10?.release()
                                            media10 = null
                                        }
                                    }
                                    11 -> {
                                        media11 = MediaPlayer.create(context, R.raw.cursed46)
                                        media11?.start()
                                        media11?.setOnCompletionListener {
                                            media11?.reset()
                                            media11?.release()
                                            media11 = null
                                        }
                                    }
                                    12 -> {
                                        media12 = MediaPlayer.create(context, R.raw.cursed48)
                                        media12?.start()
                                        media12?.setOnCompletionListener {
                                            media12?.reset()
                                            media12?.release()
                                            media12 = null
                                        }
                                    }
                                    13 -> {
                                        media13 = MediaPlayer.create(context, R.raw.cursed50)
                                        media13?.start()
                                        media13?.setOnCompletionListener {
                                            media13?.reset()
                                            media13?.release()
                                            media13 = null
                                        }
                                    }
                                    14 -> {
                                        media14 = MediaPlayer.create(context, R.raw.cursed52)
                                        media14?.start()
                                        media14?.setOnCompletionListener {
                                            media14?.reset()
                                            media14?.release()
                                            media14 = null
                                        }
                                    }
                                    15 -> {
                                        media15 = MediaPlayer.create(context, R.raw.cursed54)
                                        media15?.start()
                                        media15?.setOnCompletionListener {
                                            media15?.reset()
                                            media15?.release()
                                            media15 = null
                                        }
                                    }
                                    16 -> {
                                        media16 = MediaPlayer.create(context, R.raw.cursed56)
                                        media16?.start()
                                        media16?.setOnCompletionListener {
                                            media16?.reset()
                                            media16?.release()
                                            media16 = null
                                        }
                                    }
                                    17 -> {
                                        media17 = MediaPlayer.create(context, R.raw.cursed58)
                                        media17?.start()
                                        media17?.setOnCompletionListener {
                                            media17?.reset()
                                            media17?.release()
                                            media17 = null
                                        }
                                    }


                                }
                            }
                        ) {
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


            Row(horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp, 0.dp, 10.dp, 10.dp)) {
                Box() {
                    Row(modifier = Modifier.border(2.dp,Color.White,
                        CircleShape)) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_volume_down_24_white),
                            contentDescription = null,
                            modifier = Modifier
                                .clickable {
                                    coroutineScope.launch { listState.animateScrollToItem(index = 0) }
                                }
                                .padding(5.dp)
                                .size(60.dp)
                                .clip(CircleShape)
                                .background(Color.Black)
                                .border(3.dp, Color.LightGray, CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_keyboard_double_arrow_up_24),
                            contentDescription = null,
                            modifier = Modifier
                                .clickable {
                                    coroutineScope.launch { listState.animateScrollToItem(index = 0) }
                                }
                                .padding(5.dp)
                                .size(60.dp)
                                .clip(CircleShape)
                                .background(Color.LightGray)
                                .border(3.dp, Color.Black, CircleShape),
                            contentScale = ContentScale.Crop

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

fun Download(index: Int, context: Context) {
    var url: String = ""
    when (index) {
        0 -> {
            url = "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/aGgQ40CCQXTW1g"
        }
        1 -> {
            url = "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/1LP83LPQtxwLTQ"
        }
        2 -> {
            url = "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/O8F3bGNXb_7u2g"
        }
        3 -> {
            url = "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/MDgulrxfgw500A"
        }
        4 -> {
            url = "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/y2bUa9KDmzTv9g"
        }
        5 -> {
            url = "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/-_R2gqsDyfkzgw"
        }
        6 -> {
            url = "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/H2WofFE01sC1jA"
        }
        7 -> {
            url = "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/TV7kMdMhDNiJtw"
        }
        8 -> {
            url = "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/io6eI1kXsraolg"
        }
        9 -> {
            url = "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/y_PpwZEhIHR6ZQ"
        }
        10 -> {
            url = "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/ZI9prUSVX2psNw"
        }
        11 -> {
            url =
                "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/zkk_IL4ALKTaWw" //мама
        }
        12 -> {
            url =
                "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/YvFtsPupk6g-hQ" //аккаунт
        }
        13 -> {
            url =
                "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/nG13rPaYIuZHKg" //роспись
        }
        14 -> {
            url =
                "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/AmRc3prTej4e_g" //крошка
        }
        15 -> {
            url =
                "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/JoWGJiVfyj6lKA" //что ты сказал
        }
        16 -> {
            url =
                "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/ciNmoO1ZSOGCFQ" //стесняюсь
        }
        17 -> {
            url =
                "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/TenBqnBGgedxyA" //шмотки
        }

    }

    val request = DownloadManager.Request(Uri.parse(url))
    request.setDescription("Downloading")
    request.setMimeType("audio/MP3")
    request.setTitle("File")
    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
        "audio.mp3")
    val manager = context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager?
    manager!!.enqueue(request)
}


