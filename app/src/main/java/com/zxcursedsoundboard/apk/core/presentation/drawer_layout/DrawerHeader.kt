package com.zxcursedsoundboard.apk.core.presentation.drawer_layout

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.google.android.play.core.review.ReviewManagerFactory
import com.zxcursedsoundboard.apk.R
import com.zxcursedsoundboard.apk.core.presentation.navigation.Screens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DrawerHeader() {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxWidth()
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = R.drawable.zxcursed,  //second page
                imageLoader = imageLoader
            ),
            modifier = Modifier
                .size(100.dp)
                .padding(16.dp)
                .clip(CircleShape),
            contentDescription = null
        )
        Text(
            text = "Zxcursed SoundBoard",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
        )
    }
}

@Composable
fun DrawerBody(navController: NavController, drawerState: DrawerState) {

    val items = listOf(
        DrawerItems.Contacts,
        DrawerItems.Share,
        DrawerItems.Estimate,
        DrawerItems.Settings,
    )
    val itemsAnotherApplications = listOf(
        DrawerItems.DrumPad,
        DrawerItems.Wallpaper
    )
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val openDialogWallpaper = remember { mutableStateOf(false) }
    val openDialogDrumPad = remember { mutableStateOf(false) }

    AlertDialogZxcursedWallpaper(openDialogWallpaper, context)
    AlertDialogZxcursedDrumPad(openDialogDrumPad, context)

    LazyColumn {
        itemsIndexed(items) { index, item ->
            ListItem(modifier = Modifier.clickable {
                onEvent(item, navController, context, scope, drawerState)
            }, headlineContent = {
                Text(text = item.title)
            }, leadingContent = {
                Icon(
                    painter = painterResource(id = item.imageRes),
                    contentDescription = item.title,
                    modifier = Modifier.size(24.dp)
                )
            })
        }
        item {
            Divider()
            Text(
                text = stringResource(id = R.string.another_applications),
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 8.dp
                )
            )
        }
        itemsIndexed(itemsAnotherApplications) { index, item ->
            ListItem(modifier = Modifier.clickable {
                if (index == 0) {
                    openDialogDrumPad.value = true
                } else {
                    openDialogWallpaper.value = true
                }
            }, headlineContent = {
                Text(text = item.title)
            }, leadingContent = {
                Image(
                    painter = painterResource(id = item.imageRes),
                    contentDescription = item.title,
                    modifier = Modifier.size(24.dp),
                )
            })
        }


    }
}


@Composable
fun AlertDialogZxcursedDrumPad(openDialog: MutableState<Boolean>, context: Context) {
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = { Text(text = stringResource(id = R.string.confirm_action)) },
            text = { Text(stringResource(id = R.string.are_you_sure_drumpad)) },
            confirmButton = {
                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data =
                            Uri.parse("https://play.google.com/store/apps/details?id=com.vangelnum.drumpad")
                        context.startActivity(intent)
                        openDialog.value = false
                    }
                ) {
                    Text(stringResource(id = R.string.yes))
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        openDialog.value = false
                    }) {
                    Text(stringResource(id = R.string.no))
                }
            }
        )
    }
}


@Composable
fun AlertDialogZxcursedWallpaper(openDialog: MutableState<Boolean>, context: Context) {
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = { Text(text = stringResource(id = R.string.confirm_action)) },
            text = { Text(stringResource(id = R.string.are_you_sure_wallpaper)) },
            confirmButton = {
                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data =
                            Uri.parse("https://play.google.com/store/apps/details?id=com.zxcursed.wallpaper")
                        context.startActivity(intent)
                        openDialog.value = false
                    }
                ) {
                    Text(stringResource(id = R.string.yes))
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        openDialog.value = false
                    }) {
                    Text(stringResource(id = R.string.no))
                }
            }
        )
    }
}


fun onEvent(
    title: DrawerItems,
    navController: NavController,
    context: Context,
    scope: CoroutineScope,
    drawerState: DrawerState,
) {
    when (title) {
        is DrawerItems.Estimate -> {
            val manager = ReviewManagerFactory.create(context)
            val request = manager.requestReviewFlow()
            request.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // We got the ReviewInfo object
                    val reviewInfo = request.result
                    val flow = manager.launchReviewFlow(context as Activity, reviewInfo)
                    flow.addOnCompleteListener { _ ->
                        // The flow has finished. The API does not indicate whether the user
                        // reviewed or not, or even whether the review dialog was shown. Thus, no
                        // matter the result, we continue our app flow.
                    }
                } else {
                    Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show()
                }
            }
        }

        is DrawerItems.Share -> {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TITLE, "Спасибо за то, что делишься приложением! ❤")
                putExtra(
                    Intent.EXTRA_TEXT,
                    "https://play.google.com/store/apps/details?id=com.zxcursedsoundboard.apk"
                )
                type = "text/plain"
            }
            context.startActivity(Intent.createChooser(sendIntent, "Share..."))
        }

        is DrawerItems.DrumPad -> {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data =
                Uri.parse("https://play.google.com/store/apps/details?id=com.vangelnum.drumpad")
            context.startActivity(intent)
        }

        is DrawerItems.Contacts -> {
            scope.launch {
                drawerState.close()
            }
            navController.navigate(Screens.ContactScreen.route)
        }

        is DrawerItems.Wallpaper -> {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data =
                Uri.parse("https://play.google.com/store/apps/details?id=com.zxcursed.wallpaper")
            context.startActivity(intent)
        }

        DrawerItems.Settings -> {
            scope.launch {
                drawerState.close()
            }
            navController.navigate(Screens.SettingsScreen.route)
        }
    }
}