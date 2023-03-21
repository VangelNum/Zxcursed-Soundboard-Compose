package com.zxcursedsoundboard.apk.core.presentation

import com.zxcursedsoundboard.apk.R

sealed class Screens(
    val route: String,
    val nameScreen: Int,
    val image: Int,
    val numberOfSound: Int
) {
    object ZxcursedMainScreen : Screens("main_route", R.string.cursed, R.drawable.madmyazel, 30)
    object ZxcursedSoundScreen : Screens("sound_route", R.string.sounds_cursed, R.drawable.denegnet, 40)

    object FavouriteScreen : Screens("favourite_route", R.string.favourite, R.drawable.spasibo, 0)
    object NavigationScreen : Screens("navigation_route", R.string.navigation, R.drawable.photo11, 0)
}