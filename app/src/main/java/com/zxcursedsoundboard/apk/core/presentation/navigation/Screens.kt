package com.zxcursedsoundboard.apk.core.presentation.navigation

import com.zxcursedsoundboard.apk.R

sealed class Screens(
    val route: String,
    val nameScreen: Int,
    val image: Int,
) {
    object ZxcursedMainScreen : Screens("main_route", R.string.cursed, R.drawable.nenado)
    object ZxcursedSoundScreen : Screens("sound_route", R.string.sounds_cursed, R.drawable.manabreak)
    object FavouriteScreen : Screens("favourite_route", R.string.favourite, R.drawable.stack)
    object SnailScreen : Screens("snail_route", R.string.snail, R.drawable.photo8)
    object AlwaysWannaFlyScreen : Screens("fly_route", R.string.fly, R.drawable.photo12)
    object NavigationScreen : Screens("navigation_route", R.string.navigation, R.drawable.photo11)
    object WatchMediaScreen : Screens("media_watch_route", R.string.watch_audio, R.drawable.photo11)
    object ContactScreen: Screens("contact_route",R.string.contacts,R.drawable.photo11)
    object SettingsScreen: Screens("settings_route",R.string.settings,R.drawable.photo11)
}