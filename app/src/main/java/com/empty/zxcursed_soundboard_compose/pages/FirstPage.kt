package com.empty.zxcursed_soundboard_compose.pages

import android.app.DownloadManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.empty.zxcursed_soundboard_compose.DrawerLayout
import com.empty.zxcursed_soundboard_compose.R
import com.empty.zxcursed_soundboard_compose.lifecycle.LifeOfApplication
import com.empty.zxcursed_soundboard_compose.tools.MyButton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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


@Destination(start = true)
@Composable
fun First(navigator: DestinationsNavigator) {

    var currentPage = 1

    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    val fonts = FontFamily(Font(R.font.ubuntulight))
    val listState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()
    var visible by remember { mutableStateOf(true) }

    var color1 by remember {
        mutableStateOf(Color.Green)
    }
    var color2 by remember {
        mutableStateOf(Color.White)
    }
    var color3 by remember {
        mutableStateOf(Color.White)
    }
    var color4 by remember {
        mutableStateOf(Color.White)
    }

    var list by remember {
        mutableStateOf(listOf(
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
        ))
    }

    var rememberDrawable by remember {
        mutableStateOf(R.drawable.photo13)
    }

    var thumbIconLiked: Boolean by remember {
        mutableStateOf(true)
    }
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
            DrawerLayout(imageLoader)
        }
    ) {
        it.calculateBottomPadding()

        Column(
            modifier = Modifier.background(Color.Black)
        ) {

            Row(
                modifier = Modifier
                    .padding(5.dp)
            ) {

                Spacer(modifier = Modifier.width(5.dp))

                Image(
                    painter = painterResource(id = R.drawable.photo8),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .weight(1f)
                        .aspectRatio(1f)
                        .border(1.dp, color1, CircleShape)
                        .clickable(onClick = {
                            rememberDrawable = R.drawable.photo13
                            color1 = Color.Green
                            color2 = Color.White
                            color3 = Color.White
                            color4 = Color.White
                            musicStop()
                            currentPage = 1
                            coroutineScope.launch {
                                visible = !visible
                                delay(500L)
                                visible = !visible
                                list = listOf(
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
                            }


                        }),
                    contentScale = ContentScale.Crop,

                    )
                Spacer(modifier = Modifier.width(5.dp))

                Image(
                    painter = rememberAsyncImagePainter(model = R.drawable.zxc2,  //second page
                        imageLoader = imageLoader),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .weight(1f)
                        .aspectRatio(1f)
                        .border(1.dp, color2, CircleShape)
                        .clickable(onClick = {
                            rememberDrawable = R.drawable.photo11
                            color1 = Color.White
                            color2 = Color.Green
                            color3 = Color.White
                            color4 = Color.White
                            currentPage = 2
                            musicStop()
                            coroutineScope.launch {
                                visible = !visible
                                delay(500L)
                                visible = !visible
                                list = listOf(
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
                            }

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
                        .border(1.dp, color3, CircleShape)
                        .clickable {
                            rememberDrawable = R.drawable.photo4
                            color1 = Color.White
                            color2 = Color.White
                            color3 = Color.Green
                            color4 = Color.White
                            currentPage = 3
                            musicStop()
                            coroutineScope.launch {
                                visible = !visible
                                delay(500L)
                                visible = !visible
                                list = listOf(
                                    context.getString(R.string.flymolodec),
                                    context.getString(R.string.zerodeathfly),
                                    context.getString(R.string.flyfood),
                                    context.getString(R.string.tronstoit),
                                    context.getString(R.string.unrealbuyback),
                                    context.getString(R.string.buybackfly),
                                    context.getString(R.string.apmfly),
                                    context.getString(R.string.onpausit),
                                    context.getString(R.string.makfluri),
                                    context.getString(R.string.stavka),
                                    context.getString(R.string.idealnayaigra),
                                    context.getString(R.string.beznegativa),
                                    context.getString(R.string.bestsupport),
                                    context.getString(R.string.mnogokv),
                                    context.getString(R.string.ludikikautsa),
                                    context.getString(R.string.superpoxui),
                                    context.getString(R.string.alwayswannaalttab)
                                )
                            }
                        },
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
                        .border(1.dp, color4, CircleShape)
                        .clickable {
                            rememberDrawable = R.drawable.photo18
                            color1 = Color.White
                            color2 = Color.White
                            color3 = Color.White
                            color4 = Color.Green
                            currentPage = 4
                            musicStop()
                            coroutineScope.launch {
                                visible = !visible
                                delay(500L)
                                visible = !visible
                                list = listOf(
                                    context.getString(R.string.CS),
                                    context.getString(R.string.kalim),
                                    context.getString(R.string.ulitka),
                                    context.getString(R.string.sorokonojka),
                                    context.getString(R.string.pokorit),
                                    context.getString(R.string.sinandshcool),
                                    context.getString(R.string.grandmother),
                                    context.getString(R.string.online),
                                    context.getString(R.string.simpledimple),
                                    context.getString(R.string.femlove),
                                    context.getString(R.string.in_my_mind)
                                )
                            }
                        },
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
                            coroutineScope.launch {
                                scaffoldState.drawerState.open()
                            }
                        },
                    contentScale = ContentScale.Crop,
                )
                Spacer(modifier = Modifier.width(5.dp))

            }
            Spacer(modifier = Modifier.height(5.dp))

            Box {
                Image(painter = painterResource(id = rememberDrawable),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(25.dp, 25.dp, 0.dp, 0.dp)).fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                androidx.compose.animation.AnimatedVisibility(visible = visible,
                    enter = slideInHorizontally(),
                    exit = fadeOut()) {
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
                                                    .clickable {
                                                        download(index, context, currentPage)
                                                        expanded = false
                                                    }
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
                                        .border(1.dp,
                                            Color(0x79D6C9C9),
                                            RoundedCornerShape(15.dp)),
                                    onClick = {
                                        if (thumbIconLiked) {
                                            when (index) {
                                                0 -> {
                                                    if (media0 == null) {
                                                        if (currentPage == 1) {
                                                            media0 = MediaPlayer.create(context,
                                                                R.raw.cursed12)
                                                        }
                                                        if (currentPage == 2) {
                                                            media0 = MediaPlayer.create(context,
                                                                R.raw.cursed2)
                                                        }
                                                        if (currentPage == 3) {
                                                            media0 = MediaPlayer.create(context,
                                                                R.raw.cursed17)
                                                        }
                                                        if (currentPage == 4) {
                                                            media0 = MediaPlayer.create(context,
                                                                R.raw.cursed61)
                                                        }

                                                    }
                                                    media0?.start()
                                                }
                                                1 -> {
                                                    if (media1 == null) {
                                                        if (currentPage == 1) {
                                                            media1 = MediaPlayer.create(context,
                                                                R.raw.cursed43)
                                                        }
                                                        if (currentPage == 2) {
                                                            media1 = MediaPlayer.create(context,
                                                                R.raw.cursed3)
                                                        }
                                                        if (currentPage == 3) {
                                                            media1 = MediaPlayer.create(context,
                                                                R.raw.cursed18)
                                                        }
                                                        if (currentPage == 4) {
                                                            media1 = MediaPlayer.create(context,
                                                                R.raw.cursed63)
                                                        }
                                                    }
                                                    media1?.start()
                                                }
                                                2 -> {
                                                    if (media2 == null) {
                                                        if (currentPage == 1) {
                                                            media2 = MediaPlayer.create(context,
                                                                R.raw.cursed45)
                                                        }
                                                        if (currentPage == 2) {
                                                            media2 = MediaPlayer.create(context,
                                                                R.raw.cursed4)
                                                        }
                                                        if (currentPage == 3) {
                                                            media2 = MediaPlayer.create(context,
                                                                R.raw.cursed19)
                                                        }
                                                        if (currentPage == 4) {
                                                            media2 = MediaPlayer.create(context,
                                                                R.raw.cursed65)
                                                        }
                                                    }
                                                    media2?.start()
                                                }
                                                3 -> {
                                                    if (media3 == null) {
                                                        if (currentPage == 1) {
                                                            media3 = MediaPlayer.create(context,
                                                                R.raw.cursed47)
                                                        }
                                                        if (currentPage == 2) {
                                                            media3 = MediaPlayer.create(context,
                                                                R.raw.cursed5)
                                                        }
                                                        if (currentPage == 3) {
                                                            media3 = MediaPlayer.create(context,
                                                                R.raw.cursed20)
                                                        }
                                                        if (currentPage == 4) {
                                                            media3 = MediaPlayer.create(context,
                                                                R.raw.cursed67)
                                                        }
                                                    }
                                                    media3?.start()
                                                }
                                                4 -> {
                                                    if (media4 == null) {
                                                        if (currentPage == 1) {
                                                            media4 = MediaPlayer.create(context,
                                                                R.raw.cursed49)
                                                        }
                                                        if (currentPage == 2) {
                                                            media4 = MediaPlayer.create(context,
                                                                R.raw.cursed6)
                                                        }
                                                        if (currentPage == 3) {
                                                            media4 = MediaPlayer.create(context,
                                                                R.raw.cursed21)
                                                        }
                                                        if (currentPage == 4) {
                                                            media4 = MediaPlayer.create(context,
                                                                R.raw.cursed64)
                                                        }
                                                    }
                                                    media4?.start()
                                                }
                                                5 -> {
                                                    if (media5 == null) {
                                                        if (currentPage == 1) {
                                                            media5 = MediaPlayer.create(context,
                                                                R.raw.cursed51)
                                                        }
                                                        if (currentPage == 2) {
                                                            media5 = MediaPlayer.create(context,
                                                                R.raw.cursed7)
                                                        }
                                                        if (currentPage == 3) {
                                                            media5 = MediaPlayer.create(context,
                                                                R.raw.cursed22)
                                                        }
                                                        if (currentPage == 4) {
                                                            media5 = MediaPlayer.create(context,
                                                                R.raw.cursed66)
                                                        }

                                                    }
                                                    media5?.start()
                                                }
                                                6 -> {
                                                    if (media6 == null) {
                                                        if (currentPage == 1) {
                                                            media6 = MediaPlayer.create(context,
                                                                R.raw.cursed53)
                                                        }
                                                        if (currentPage == 2) {
                                                            media6 = MediaPlayer.create(context,
                                                                R.raw.cursed8)
                                                        }
                                                        if (currentPage == 3) {
                                                            media6 = MediaPlayer.create(context,
                                                                R.raw.cursed23)
                                                        }
                                                        if (currentPage == 4) {
                                                            media6 = MediaPlayer.create(context,
                                                                R.raw.cursed68)
                                                        }
                                                    }
                                                    media6?.start()
                                                }
                                                7 -> {
                                                    if (media7 == null) {
                                                        if (currentPage == 1) {
                                                            media7 = MediaPlayer.create(context,
                                                                R.raw.cursed55)
                                                        }
                                                        if (currentPage == 2) {
                                                            media7 = MediaPlayer.create(context,
                                                                R.raw.cursed9)
                                                        }
                                                        if (currentPage == 3) {
                                                            media7 = MediaPlayer.create(context,
                                                                R.raw.cursed25)
                                                        }
                                                        if (currentPage == 4) {
                                                            media7 = MediaPlayer.create(context,
                                                                R.raw.cursed69)
                                                        }
                                                    }
                                                    media7?.start()
                                                }
                                                8 -> {
                                                    if (media8 == null) {
                                                        if (currentPage == 1) {
                                                            media8 = MediaPlayer.create(context,
                                                                R.raw.cursed57)
                                                        }
                                                        if (currentPage == 2) {
                                                            media8 = MediaPlayer.create(context,
                                                                R.raw.cursed10)
                                                        }
                                                        if (currentPage == 3) {
                                                            media8 = MediaPlayer.create(context,
                                                                R.raw.cursed26)
                                                        }
                                                        if (currentPage == 4) {
                                                            media8 = MediaPlayer.create(context,
                                                                R.raw.cursed62)
                                                        }
                                                    }
                                                    media8?.start()
                                                }
                                                9 -> {
                                                    if (media9 == null) {
                                                        if (currentPage == 1) {
                                                            media9 = MediaPlayer.create(context,
                                                                R.raw.cursed13)
                                                        }
                                                        if (currentPage == 2) {
                                                            media9 = MediaPlayer.create(context,
                                                                R.raw.cursed11)
                                                        }
                                                        if (currentPage == 3) {
                                                            media9 = MediaPlayer.create(context,
                                                                R.raw.cursed27)
                                                        }
                                                        if (currentPage == 4) {
                                                            media9 = MediaPlayer.create(context,
                                                                R.raw.cursed60)
                                                        }
                                                    }
                                                    media9?.start()
                                                }
                                                10 -> {
                                                    if (media10 == null) {
                                                        if (currentPage == 1) {
                                                            media10 = MediaPlayer.create(context,
                                                                R.raw.cursed44)
                                                        }
                                                        if (currentPage == 2) {
                                                            media10 = MediaPlayer.create(context,
                                                                R.raw.cursed14)
                                                        }
                                                        if (currentPage == 3) {
                                                            media10 = MediaPlayer.create(context,
                                                                R.raw.cursed28)
                                                        }
                                                        if (currentPage == 4) {
                                                            media10 = MediaPlayer.create(context,
                                                                R.raw.cursed59)
                                                        }
                                                    }
                                                    media10?.start()
                                                }
                                                11 -> {
                                                    if (media11 == null) {
                                                        if (currentPage == 1) {
                                                            media11 = MediaPlayer.create(context,
                                                                R.raw.cursed46)
                                                        }
                                                        if (currentPage == 2) {
                                                            media11 = MediaPlayer.create(context,
                                                                R.raw.cursed15)
                                                        }
                                                        if (currentPage == 3) {
                                                            media11 = MediaPlayer.create(context,
                                                                R.raw.cursed29)
                                                        }
                                                    }
                                                    media11?.start()
                                                }
                                                12 -> {
                                                    if (media12 == null) {
                                                        if (currentPage == 1) {
                                                            media12 = MediaPlayer.create(context,
                                                                R.raw.cursed48)
                                                        }
                                                        if (currentPage == 2) {
                                                            media12 = MediaPlayer.create(context,
                                                                R.raw.cursed16)
                                                        }
                                                        if (currentPage == 3) {
                                                            media12 = MediaPlayer.create(context,
                                                                R.raw.cursed30)
                                                        }
                                                    }
                                                    media12?.start()
                                                }
                                                13 -> {
                                                    if (media13 == null) {
                                                        if (currentPage == 1) {
                                                            media13 = MediaPlayer.create(context,
                                                                R.raw.cursed50)
                                                        }
                                                        if (currentPage == 2) {
                                                            media13 = MediaPlayer.create(context,
                                                                R.raw.cursed24)
                                                        }
                                                        if (currentPage == 3) {
                                                            media13 = MediaPlayer.create(context,
                                                                R.raw.cursed31)
                                                        }
                                                    }
                                                    media13?.start()
                                                }
                                                14 -> {
                                                    if (media14 == null) {
                                                        if (currentPage == 1) {
                                                            media14 = MediaPlayer.create(context,
                                                                R.raw.cursed52)
                                                        }
                                                        if (currentPage == 2) {
                                                            media14 = MediaPlayer.create(context,
                                                                R.raw.cursed35)
                                                        }
                                                        if (currentPage == 3) {
                                                            media14 = MediaPlayer.create(context,
                                                                R.raw.cursed32)
                                                        }
                                                    }
                                                    media14?.start()
                                                }
                                                15 -> {
                                                    if (media15 == null) {
                                                        if (currentPage == 1) {
                                                            media15 = MediaPlayer.create(context,
                                                                R.raw.cursed54)
                                                        }
                                                        if (currentPage == 2) {
                                                            media15 = MediaPlayer.create(context,
                                                                R.raw.cursed36)
                                                        }
                                                        if (currentPage == 3) {
                                                            media15 = MediaPlayer.create(context,
                                                                R.raw.cursed33)
                                                        }
                                                    }
                                                    media15?.start()
                                                }
                                                16 -> {
                                                    if (media16 == null) {
                                                        if (currentPage == 1) {
                                                            media16 = MediaPlayer.create(context,
                                                                R.raw.cursed56)
                                                        }
                                                        if (currentPage == 2) {
                                                            media16 = MediaPlayer.create(context,
                                                                R.raw.cursed37)
                                                        }
                                                        if (currentPage == 3) {
                                                            media16 = MediaPlayer.create(context,
                                                                R.raw.cursed34)
                                                        }
                                                    }
                                                    media16?.start()
                                                }
                                                17 -> {
                                                    if (media17 == null) {
                                                        if (currentPage == 1) {
                                                            media17 = MediaPlayer.create(context,
                                                                R.raw.cursed58)
                                                        }
                                                        if (currentPage == 2) {
                                                            media17 = MediaPlayer.create(context,
                                                                R.raw.cursed38)
                                                        }
                                                    }
                                                    media17?.start()
                                                }
                                                18 -> {
                                                    if (media18 == null) {
                                                        if (currentPage == 2) {
                                                            media18 = MediaPlayer.create(context,
                                                                R.raw.cursed39)
                                                        }
                                                    }
                                                    media18?.start()
                                                }
                                                19 -> {
                                                    if (media19 == null) {
                                                        if (currentPage == 2) {
                                                            media19 = MediaPlayer.create(context,
                                                                R.raw.cursed40)
                                                        }
                                                    }
                                                    media19?.start()
                                                }
                                                20 -> {
                                                    if (media20 == null) {
                                                        if (currentPage == 2) {
                                                            media20 = MediaPlayer.create(context,
                                                                R.raw.cursed42)
                                                        }
                                                    }
                                                    media20?.start()
                                                }
                                                21 -> {
                                                    if (media21 == null) {
                                                        if (currentPage == 2) {
                                                            media21 = MediaPlayer.create(context,
                                                                R.raw.cursed41)
                                                        }
                                                    }
                                                    media21?.start()
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
                }

                Row(horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp, 0.dp, 10.dp, 10.dp)) {

                    Box {

                        Row(modifier = Modifier.border(1.dp, Color.White,
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
                                    .border(1.dp, Color.LightGray, CircleShape),
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
                                    .border(1.dp, Color.LightGray, CircleShape),
                                contentScale = ContentScale.Crop

                            )


                        }

                    }

                }
            }

            LifeOfApplication(context = context) // цикл жизни

        }


    }


}


fun musicStop() {
    if (media0?.isPlaying == true || media0 != null) {
        media0?.stop()
        media0?.reset()
        media0?.release()
        media0 = null
    }

    if (media1?.isPlaying == true || media1 != null) {
        media1?.stop()
        media1?.reset()
        media1?.release()
        media1 = null
    }
    if (media2?.isPlaying == true || media2 != null) {
        media2?.stop()
        media2?.reset()
        media2?.release()
        media2 = null
    }
    if (media3?.isPlaying == true || media3 != null) {
        media3?.stop()
        media3?.reset()
        media3?.release()
        media3 = null
    }
    if (media4?.isPlaying == true || media4 != null) {
        media4?.stop()
        media4?.reset()
        media4?.release()
        media4 = null
    }
    if (media5?.isPlaying == true || media5 != null) {
        media5?.stop()
        media5?.reset()
        media5?.release()
        media5 = null
    }
    if (media6?.isPlaying == true || media6 != null) {
        media6?.stop()
        media6?.reset()
        media6?.release()
        media6 = null
    }
    if (media7?.isPlaying == true || media7 != null) {
        media7?.stop()
        media7?.reset()
        media7?.release()
        media7 = null
    }
    if (media8?.isPlaying == true || media8 != null) {
        media8?.stop()
        media8?.reset()
        media8?.release()
        media8 = null
    }
    if (media9?.isPlaying == true || media9 != null) {
        media9?.stop()
        media9?.reset()
        media9?.release()
        media9 = null
    }
    if (media10?.isPlaying == true || media10 != null) {
        media10?.stop()
        media10?.reset()
        media10?.release()
        media10 = null
    }
    if (media11?.isPlaying == true || media11 != null) {
        media11?.stop()
        media11?.reset()
        media11?.release()
        media11 = null
    }
    if (media12?.isPlaying == true || media12 != null) {
        media12?.stop()
        media12?.reset()
        media12?.release()
        media12 = null
    }
    if (media13?.isPlaying == true || media13 != null) {
        media13?.stop()
        media13?.reset()
        media13?.release()
        media13 = null
    }
    if (media14?.isPlaying == true || media14 != null) {
        media14?.stop()
        media14?.reset()
        media14?.release()
        media14 = null
    }
    if (media15?.isPlaying == true || media15 != null) {
        media15?.stop()
        media15?.reset()
        media15?.release()
        media15 = null
    }
    if (media16?.isPlaying == true || media16 != null) {
        media16?.stop()
        media16?.reset()
        media16?.release()
        media16 = null
    }
    if (media17?.isPlaying == true || media17 != null) {
        media17?.stop()
        media17?.reset()
        media17?.release()
        media17 = null
    }
    if (media18?.isPlaying == true || media18 != null) {
        media18?.stop()
        media18?.reset()
        media18?.release()
        media18 = null
    }
    if (media19?.isPlaying == true || media19 != null) {
        media19?.stop()
        media19?.reset()
        media19?.release()
        media19 = null
    }
    if (media20?.isPlaying == true || media20 != null) {
        media20?.stop()
        media20?.reset()
        media20?.release()
        media20 = null
    }
    if (media21?.isPlaying == true || media21 != null) {
        media21?.stop()
        media21?.reset()
        media21?.release()
        media21 = null
    }
}

private fun download(index: Int, context: Context, currentPage: Int) {
    var url = ""
    if (currentPage == 1) {
        when (index) {
            0 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/aGgQ40CCQXTW1g"
            }
            1 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/1LP83LPQtxwLTQ"
            }
            2 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/O8F3bGNXb_7u2g"
            }
            3 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/MDgulrxfgw500A"
            }
            4 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/y2bUa9KDmzTv9g"
            }
            5 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/-_R2gqsDyfkzgw"
            }
            6 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/H2WofFE01sC1jA"
            }
            7 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/TV7kMdMhDNiJtw"
            }
            8 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/io6eI1kXsraolg"
            }
            9 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/y_PpwZEhIHR6ZQ"
            }
            10 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/ZI9prUSVX2psNw"
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
