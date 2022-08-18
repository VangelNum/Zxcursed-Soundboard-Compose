package com.empty.zxcursed_soundboard_compose.pages

import android.app.DownloadManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
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
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.empty.zxcursed_soundboard_compose.Person
import com.empty.zxcursed_soundboard_compose.R
import com.empty.zxcursed_soundboard_compose.tools.MyButton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch


private lateinit var media0: MediaPlayer
private lateinit var media1: MediaPlayer
private lateinit var media2: MediaPlayer
private lateinit var media3: MediaPlayer
private lateinit var media4: MediaPlayer
private lateinit var media5: MediaPlayer
private lateinit var media6: MediaPlayer
private lateinit var media7: MediaPlayer
private lateinit var media8: MediaPlayer
private lateinit var media9: MediaPlayer
private lateinit var media10: MediaPlayer
private lateinit var media11: MediaPlayer
private lateinit var media12: MediaPlayer
private lateinit var media13: MediaPlayer
private lateinit var media14: MediaPlayer
private lateinit var media15: MediaPlayer
private lateinit var media16: MediaPlayer
private lateinit var media17: MediaPlayer


@Destination(start = true)
@Composable
fun First(navigator: DestinationsNavigator) {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val fonts = FontFamily(Font(R.font.ubuntulight))
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    Scaffold(
        scaffoldState = scaffoldState,
        drawerBackgroundColor = Color.White,
        drawerContent = {
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
        },
        content = {
            it.calculateBottomPadding()

            Column(
                modifier = Modifier.background(Color.Black)
            ) {
                Person(navigator = navigator,
                    Color.Green,
                    Color.White,
                    Color.White,
                    Color.White,
                    scope,
                    scaffoldState)


                media0 = MediaPlayer.create(context, R.raw.cursed12)
                media1 = MediaPlayer.create(context, R.raw.cursed43)
                media2 = MediaPlayer.create(context, R.raw.cursed45)
                media3 = MediaPlayer.create(context, R.raw.cursed47)
                media4 = MediaPlayer.create(context, R.raw.cursed49)
                media5 = MediaPlayer.create(context, R.raw.cursed51)
                media6 = MediaPlayer.create(context, R.raw.cursed53)
                media7 = MediaPlayer.create(context, R.raw.cursed55)
                media8 = MediaPlayer.create(context, R.raw.cursed57)
                media9 = MediaPlayer.create(context, R.raw.cursed13)
                media10 = MediaPlayer.create(context, R.raw.cursed44)
                media11 = MediaPlayer.create(context, R.raw.cursed46)
                media12 = MediaPlayer.create(context, R.raw.cursed48)
                media13 = MediaPlayer.create(context, R.raw.cursed50)
                media14 = MediaPlayer.create(context, R.raw.cursed52)
                media15 = MediaPlayer.create(context, R.raw.cursed54)
                media16 = MediaPlayer.create(context, R.raw.cursed56)
                media17 = MediaPlayer.create(context, R.raw.cursed58)

                Box {
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

                    var thumbIconLiked by remember {
                        mutableStateOf(true)
                    }

                    LazyVerticalGrid(columns = GridCells.Fixed(2),
                        state = listState,
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        contentPadding = PaddingValues(10.dp, 10.dp, 10.dp, 100.dp),
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
                                                        download(index, context)
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
                                        if (thumbIconLiked) {
                                            when (index) {
                                                0 -> {
                                                    media0.start()
                                                }
                                                1 -> {
                                                    media1.start()
                                                }
                                                2 -> {
                                                    media2.start()
                                                }
                                                3 -> {
                                                    media3.start()
                                                }
                                                4 -> {
                                                    media4.start()
                                                }
                                                5 -> {
                                                    media5.start()
                                                }
                                                6 -> {
                                                    media6.start()
                                                }
                                                7 -> {
                                                    media7.start()
                                                }
                                                8 -> {
                                                    media8.start()
                                                }
                                                9 -> {
                                                    media9.start()
                                                }
                                                10 -> {
                                                    media10.start()
                                                }
                                                11 -> {
                                                    media11.start()
                                                }
                                                12 -> {
                                                    media12.start()
                                                }
                                                13 -> {
                                                    media13.start()
                                                }
                                                14 -> {
                                                    media14.start()
                                                }
                                                15 -> {
                                                    media15.start()
                                                }
                                                16 -> {
                                                    media16.start()
                                                }
                                                17 -> {
                                                    media17.start()
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
                        Box {

                            Row(modifier = Modifier.border(2.dp, Color.White,
                                CircleShape)) {

                                Image(
                                    painter = painterResource(
                                        id = if (thumbIconLiked) {
                                            R.drawable.ic_baseline_volume_down_24_white
                                        } else {
                                            R.drawable.ic_baseline_volume_off_24_white
                                        }
                                    ),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .clickable {
                                            thumbIconLiked = !thumbIconLiked
                                            if (!thumbIconLiked) {
                                                musicStop()
                                            }
                                        }
                                        .padding(5.dp)
                                        .size(60.dp)
                                        .clip(CircleShape)
                                        .background(Color.Black)
                                        .border(3.dp, Color.LightGray, CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.ic_baseline_keyboard_double_arrow_up_24_white),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .clickable {
                                            coroutineScope.launch {
                                                listState.animateScrollToItem(index = 0)
                                            }
                                        }
                                        .padding(5.dp)
                                        .size(60.dp)
                                        .clip(CircleShape)
                                        .background(Color.Black)
                                        .border(3.dp, Color.LightGray, CircleShape),
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
                    when (event) {
                        Lifecycle.Event.ON_CREATE -> {
                            //something to do
                            Log.d("Tag", "CREATE")
                        }
                        Lifecycle.Event.ON_RESUME -> {
                            //something to do
                            Log.d("Tag", "RESUME")
                        }
                        Lifecycle.Event.ON_DESTROY -> {
                            //MusicStop()
                            Log.d("Tag", "DESTROY")
                        }
                        Lifecycle.Event.ON_PAUSE -> {
                            musicStop()
                            Log.d("Tag", "PAUSE")
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
    )


}

fun musicStop() {

    media0.stop()
    media0.reset()
    media0.release()

    if (media1.isPlaying) {
        media1.stop()
        media1.reset()
        media1.release()
    }
    if (media2.isPlaying) {
        media2.stop()
        media2.reset()
        media2.release()
    }
    if (media3.isPlaying) {
        media3.stop()
        media3.reset()
        media3.release()
    }
    if (media4.isPlaying) {
        media4.stop()
        media4.reset()
        media4.release()
    }
    if (media5.isPlaying) {
        media5.stop()
        media5.reset()
        media5.release()
    }
    if (media6.isPlaying) {
        media6.stop()
        media6.reset()
        media6.release()
    }
    if (media7.isPlaying) {
        media7.stop()
        media7.reset()
        media7.release()
    }
    if (media8.isPlaying) {
        media8.stop()
        media8.reset()
        media8.release()
    }
    if (media9.isPlaying) {
        media9.stop()
        media9.reset()
        media9.release()
    }
    if (media10.isPlaying) {
        media10.stop()
        media10.reset()
        media10.release()
    }
    if (media11.isPlaying) {
        media11.stop()
        media11.reset()
        media11.release()
    }
    if (media12.isPlaying) {
        media12.stop()
        media12.reset()
        media12.release()
    }
    if (media13.isPlaying) {
        media13.stop()
        media13.reset()
        media13.release()
    }
    if (media14.isPlaying) {
        media14.stop()
        media14.reset()
        media14.release()
    }
    if (media15.isPlaying) {
        media15.stop()
        media15.reset()
        media15.release()
    }
    if (media16.isPlaying) {
        media16.stop()
        media16.reset()
        media16.release()
    }
    if (media17.isPlaying) {
        media17.stop()
        media17.reset()
        media17.release()
    }
}


fun download(index: Int, context: Context) {
    var url = ""
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


