package com.zxcursedsoundboard.apk.core.presentation

sealed class Screens(val route: String, val nameScreen: String) {
    object MainScreen : Screens("main_route", "Курсед")
    object SoundScreen : Screens("sound_route", "Песни курседа")
    object FavouriteScreen : Screens("favourite_route", "Избранное")
}