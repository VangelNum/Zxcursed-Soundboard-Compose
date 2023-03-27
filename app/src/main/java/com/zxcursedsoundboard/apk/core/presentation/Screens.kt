package com.zxcursedsoundboard.apk.core.presentation

import com.zxcursedsoundboard.apk.R

sealed class Screens(
    val route: String,
    val nameScreen: Int,
    val image: Int,
) {
    object ZxcursedMainScreen : Screens("main_route", R.string.cursed, R.drawable.madmyazel)
    object ZxcursedSoundScreen : Screens("sound_route", R.string.sounds_cursed, R.drawable.denegnet)

    object FavouriteScreen : Screens("favourite_route", R.string.favourite, R.drawable.spasibo)
    object NavigationScreen : Screens("navigation_route", R.string.navigation, R.drawable.photo11)
    object WatchMediaScreen : Screens("media_watch_route", R.string.watch_audio, R.drawable.photo11)

    object TestScreen: Screens("test_route",R.string.app_name,R.drawable.photo11)
}