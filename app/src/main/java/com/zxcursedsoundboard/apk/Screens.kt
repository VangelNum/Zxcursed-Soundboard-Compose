package com.zxcursedsoundboard.apk

sealed class Screens(val route: String) {
    object MainScreen : Screens("main_route")
    object SoundScreen : Screens("sound_route")
    object FavouriteScreen : Screens("favourite_route")
}