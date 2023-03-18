package com.zxcursedsoundboard.apk

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zxcursedsoundboard.feature_favourite.presentation.FavouriteScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screens.MainScreen.route
) {

    val scaffoldState = rememberBackdropScaffoldState(initialValue = BackdropValue.Concealed)
    val scope = rememberCoroutineScope()

    val itemsScreens = listOf(
        "Курсед",
        "Песни курседа",
        "Улиточка",
        "Акума",
        "Флай",
        "Избранное"
    )



    BackdropScaffold(
        backLayerBackgroundColor = MaterialTheme.colors.background,
        scaffoldState = scaffoldState,
        appBar = {
            TopAppBar(
                title = { Text("Zxcursed Soundboard") },
                navigationIcon = {
                    if (scaffoldState.isConcealed) {
                        IconButton(
                            onClick = {
                                scope.launch { scaffoldState.reveal() }
                            }
                        ) {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    } else {
                        IconButton(
                            onClick = {
                                scope.launch { scaffoldState.conceal() }
                            }
                        ) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = "Close"
                            )
                        }
                    }
                },
                elevation = 0.dp,
                backgroundColor = Color.Transparent
            )
        },
        backLayerContent = {
            itemsScreens.forEachIndexed { index, route ->
                Column {
                    ListItem(text = { Text(text = route) }, modifier = Modifier.clickable {
                        when(index) {
                            0-> {
                                navController.navigate(Screens.MainScreen.route)
                            }
                            1-> {
                                navController.navigate(Screens.SoundScreen.route)
                            }
                            5-> {
                                navController.navigate(Screens.FavouriteScreen.route)
                            }
                        }
                    })
                }
            }
        },
        frontLayerContent = {
            NavHost(navController = navController, startDestination = startDestination) {
                composable(Screens.MainScreen.route) {
                    ZxcursedMainScreen()
                }
                composable(Screens.SoundScreen.route) {
                    ZxcursedSoundScreen()
                }
                composable(Screens.FavouriteScreen.route) {
                    FavouriteScreen()
                }
            }
        },
    )
}
