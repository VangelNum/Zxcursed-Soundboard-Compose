package com.zxcursedsoundboard.apk.pages

import android.app.DownloadManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.*
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
import androidx.compose.ui.draw.alpha
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
import com.zxcursedsoundboard.apk.DrawerLayout
import com.zxcursedsoundboard.apk.R

import com.zxcursedsoundboard.apk.lifecycle.LifeOfApplication
import com.zxcursedsoundboard.apk.tools.MyButton

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
private var media22: MediaPlayer? = null
private var media23: MediaPlayer? = null
private var media24: MediaPlayer? = null
private var media25: MediaPlayer? = null
private var media26: MediaPlayer? = null
private var media27: MediaPlayer? = null


@Destination(start = true)
@Composable
fun First(navigator: DestinationsNavigator) {

    var currentPage by remember {
        mutableStateOf(1)
    }

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
            DrawerLayout(imageLoader, navigator, context)
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
                            currentPage = 1
                            rememberDrawable = R.drawable.photo13
                            color1 = Color.Green
                            color2 = Color.White
                            color3 = Color.White
                            color4 = Color.White
                            musicStop()
                            coroutineScope.launch {
                                listState.animateScrollToItem(index = 0)
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
                                listState.animateScrollToItem(index = 0)
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
                                    context.getString(R.string.dumaldendi),
                                    context.getString(R.string.boje),
                                    context.getString(R.string.osujdau),
                                    context.getString(R.string.ofau),
                                    context.getString(R.string.zerozriteley),
                                    context.getString(R.string.zavaliebalo),
                                    context.getString(R.string.razban)
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
                            musicStop()
                            coroutineScope.launch {
                                currentPage = 3
                                listState.animateScrollToItem(index = 0)
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
                                listState.animateScrollToItem(index = 0)
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
                                    context.getString(R.string.in_my_mind),
                                    context.getString(R.string.bankai),
                                    context.getString(R.string.killua),
                                    context.getString(R.string.neverenough),
                                    context.getString(R.string.quinque),
                                    context.getString(R.string.fxckoff),
                                )
                            }
                        },
                    contentScale = ContentScale.Crop,
                )
                Spacer(modifier = Modifier.width(5.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_dehaze_24),
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
                        .clip(RoundedCornerShape(25.dp, 25.dp, 0.dp, 0.dp))
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                androidx.compose.animation.AnimatedVisibility(visible = visible,
                    enter = slideInHorizontally(),
                    exit = shrinkHorizontally()) {
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
                                    border = BorderStroke(1.dp, Color.Gray),
                                    modifier = Modifier
                                        .height(80.dp)
                                        .fillMaxWidth()
                                        .alpha(0.9f),
                                    shape = RoundedCornerShape(25),
                                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(
                                        0xFF0A0A0A)),
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
                                                        if (currentPage == 4) {
                                                            media11 = MediaPlayer.create(context,
                                                                R.raw.zxcursed70)
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
                                                        if (currentPage == 4) {
                                                            media12 = MediaPlayer.create(context,
                                                                R.raw.zxcursed72)
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
                                                        if (currentPage == 4) {
                                                            media13 = MediaPlayer.create(context,
                                                                R.raw.zxcursed73)
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
                                                        if (currentPage == 4) {
                                                            media14 = MediaPlayer.create(context,
                                                                R.raw.zxcursed74)
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
                                                        if (currentPage == 4) {
                                                            media15 = MediaPlayer.create(context,
                                                                R.raw.zxcursed71)
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
                                                22 -> {  // да какого хуя
                                                    if (media22 == null) {
                                                        if (currentPage == 2) {
                                                            media22 = MediaPlayer.create(context,
                                                                R.raw.cursed76)
                                                        }
                                                    }
                                                    media22?.start()
                                                }
                                                23 -> { //осуждаю
                                                    if (media23 == null) {
                                                        if (currentPage == 2) {
                                                            media23 = MediaPlayer.create(context,
                                                                R.raw.cursed77)
                                                        }
                                                    }
                                                    media23?.start()
                                                }
                                                24 -> { //офаю
                                                    if (media24 == null) {
                                                        if (currentPage == 2) {
                                                            media24 = MediaPlayer.create(context,
                                                                R.raw.cursed78)
                                                        }
                                                    }
                                                    media24?.start()
                                                }
                                                25 -> { //офаю
                                                    if (media25 == null) {
                                                        if (currentPage == 2) {
                                                            media25 = MediaPlayer.create(context,
                                                                R.raw.cursed79)
                                                        }
                                                    }
                                                    media25?.start()
                                                }
                                                26 -> { //0 зрителей
                                                    if (media26 == null) {
                                                        if (currentPage == 2) {
                                                            media26 = MediaPlayer.create(context,
                                                                R.raw.cursed80)
                                                        }
                                                    }
                                                    media26?.start()
                                                }
                                                27 -> { //завали ебало
                                                    if (media27 == null) {
                                                        if (currentPage == 2) {
                                                            media27 = MediaPlayer.create(context,
                                                                R.raw.cursed81)
                                                        }
                                                    }
                                                    media27?.start()
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
    if (media22?.isPlaying == true || media22 != null) {
        media22?.stop()
        media22?.reset()
        media22?.release()
        media22 = null
    }
    if (media23?.isPlaying == true || media23 != null) {
        media23?.stop()
        media23?.reset()
        media23?.release()
        media23 = null
    }
    if (media24?.isPlaying == true || media24 != null) {
        media24?.stop()
        media24?.reset()
        media24?.release()
        media24 = null
    }
    if (media25?.isPlaying == true || media25 != null) {
        media25?.stop()
        media25?.reset()
        media25?.release()
        media25 = null
    }
    if (media26?.isPlaying == true || media26 != null) {
        media26?.stop()
        media26?.reset()
        media26?.release()
        media26 = null
    }
    if (media27?.isPlaying == true || media27 != null) {
        media27?.stop()
        media27?.reset()
        media27?.release()
        media27 = null
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
    if (currentPage == 2) {
        when (index) {
            0 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/1lYhaW-rrnroeA"
            }
            1 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/WC5Ax2n_7IhXHw"
            }
            2 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/9lhUu6VHkw-XtA"
            }
            3 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/gy-O4dXFpXhpCw"
            }
            4 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/990tsnu-jYPCDw"
            }
            5 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/jb1zwCOQdviGSQ"
            }
            6 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/jlKJdK_UNs9U4w"
            }
            7 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/N3hz-J4A1zoQCQ"
            }
            8 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/FjHszVTHslnftg"
            }
            9 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/cXIrWJPxNDQ2tg"
            }
            10 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/sHICBg02SBdSMw"
            }
            11 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/UhWg8nuwNb0b6Q"
            }
            12 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/one1yK07x_ODhw"
            }
            13 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/tDs8oVb3iENTww"
            }
            14 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/8MU6ax_xAsopjQ"
            }
            15 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/y-IhIFdtDqhUCQ"
            }
            16 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/7miNN3B8nQWLzw"
            }
            17 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/NAP0uU6dNOBIrw"
            }
            18 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/zDKJmh2xZXmNRA"
            }
            19 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/YQMfEUTY4g406g"
            }
            20 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/PRvpvob4obuVkQ"
            }
            21 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/VX4T-qOTVuAzUw"
            }
            22 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/HZnIyc8OLry4UQ"
            }
            23 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/EqlMzTWmjM3hOg"
            }
            24 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/AjMzxJ9eC207GQ"
            }
            25 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/7axeoTxc69xnPQ"
            }
            26 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/EhhILYDkGhD1cQ"
            }
            27 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/idl9f1ngQT5iQA"
            }
        }
    }
    if (currentPage == 3) {
        when (index) {
            0 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/tegFWnGJ7vPT2w"
            }
            1 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/vXDPdrKzMT9ilw"
            }
            2 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/vKVGgzj0JCG2gg"
            }
            3 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/go7QLV-k7S3VkA"
            }
            4 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/NqMTIQCl8bjG7A"
            }
            5 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/60sO7feCjtM-EA"
            }
            6 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/SzYQoNOFdpoLkg"
            }
            7 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/RcVz3JRHmzt_ew"
            }
            8 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/MBnzwLtgLt1Y8w"
            }
            9 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/dmiEP6OGaDQV2w"
            }
            10 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/V1HyBjpEhzf_vg"
            }
            11 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/6-tfOZPQuCX60Q"
            }
            12 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/oBjCCP32lo4chg"
            }
            13 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/QtLm1WJX7g1whA"
            }
            14 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/j4AeNwo01-G9gQ"
            }
            15 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/eaLnatA_boVIJw"
            }
            16 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/juj3v705e2iZ-Q"
            }
        }
    }
    if (currentPage == 4) {
        when (index) {
            0 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/B4vX2zYKr2Wffw"
            }
            1 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/uKCn2C4y6gp3ug"
            }
            2 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/RBp-yWs-IDKWLQ"
            }
            3 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/_wJ1xDvN-hNJwA"
            }
            4 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/k6FR6bBGlaFwIQ"
            }
            5 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/X1Kl4wgZ2GKW5A"
            }
            6 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/B3ktHaLusbKFhw"
            }
            7 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/P4WrRT7lC-0_xA"
            }
            8 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/4uzESrHti_rCUw"
            }
            9 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/60sFXr5P-NrzYQ"
            }
            10 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/s6CJG8Ixs_QEUg"
            }
            11 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/fTwiF1ab46R_QQ"
            }
            12 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/HLaFF0QXKSjoRw"
            }
            13 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/YM2HyGZ7-qDP_g"
            }
            14 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/diz5x-Jk0TKFNA"
            }
            15 -> {
                url =
                    "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/2OAHqikayl3sIA"
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
