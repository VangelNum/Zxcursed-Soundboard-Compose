package com.zxcursedsoundboard.apk

import android.media.MediaPlayer
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zxcursedsoundboard.feature_favourite.data.model.FavouriteEntity
import com.zxcursedsoundboard.feature_favourite.presentation.FavouriteViewModel


private var media0: MediaPlayer? = null
private var media1: MediaPlayer? = null

@Composable
fun ZxcursedMainScreen(favouriteViewModel: FavouriteViewModel = hiltViewModel()) {

    val favouriteState = favouriteViewModel.favouriteState.collectAsState()

    val context = LocalContext.current

    media0 = MediaPlayer.create(
        context,
        R.raw.cursed2
    )
    media1 = MediaPlayer.create(
        context,
        R.raw.cursed2
    )

    val items = remember {
        mutableStateListOf(
            R.string.pivo,
            R.string.molchat,
            R.string.traxat,
            R.string.pikaper,
            R.string.sydalut,
            R.string.madmyazel,
            R.string.chtoetoblyat,
            R.string.spasibo,
            R.string.denegnet,
            R.string.minuspivo,
            R.string.anekdot,
            R.string.dirkavsire,
            R.string.buyback,
            R.string.kvakvakva,
            R.string._0_10,
            R.string.mamont,
            R.string.gerichabi,
            R.string.jenarojala,
            R.string.onrad,
            R.string.worstsf,
            R.string.fivestar,
            R.string.dumaldendi,
            R.string.boje,
            R.string.osujdau,
            R.string.ofau,
            R.string.zerozriteley,
            R.string.zavaliebalo,
            R.string.razban
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.photo11),
            contentDescription = "zxcursed",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items) { songName ->
                OutlinedButton(
                    shape = RoundedCornerShape(25.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .alpha(0.9f),
                    onClick = {
                        media0?.start()
                    },
                    border = BorderStroke(1.dp, Color.Gray),
                ) {
                    IconButton(onClick = {
                        if (favouriteState.value.data?.toString()?.contains(context.getString(songName)) == true) {
                            favouriteViewModel.deleteSong(context.getString(songName))
                        } else {
                            favouriteViewModel.addSong(
                                FavouriteEntity(
                                    songName = context.getString(songName), songRes = R.raw.cursed12
                                )
                            )
                        }
                    }) {
                        Icon(
                            tint = if (favouriteState.value.data?.toString()?.contains(context.getString(songName)) == true) Color.Red else Color.White,
                            imageVector = Icons.Outlined.Favorite,
                            contentDescription = "favourite"
                        )
                    }
                    Text(
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .weight(1f)
                            .offset((-24).dp),
                        text = stringResource(id = songName).uppercase(),
                        style = MaterialTheme.typography.h4
                    )
                }
            }
        }
    }
}