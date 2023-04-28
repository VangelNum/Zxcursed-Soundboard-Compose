package com.zxcursedsoundboard.apk.core.presentation.drawer_layout

import com.zxcursedsoundboard.apk.R

sealed class DrawerItems(val title: String, val imageRes: Int) {
    object Contacts : DrawerItems("Контакты", R.drawable.ic_baseline_message_24)
    object Share : DrawerItems("Поделиться", R.drawable.ic_baseline_share_24)
    object Settings : DrawerItems("Настройки", R.drawable.baseline_settings_24)
    object Estimate : DrawerItems("Оценить", R.drawable.ic_baseline_star_24)
    object Wallpaper: DrawerItems("Zxcursed Wallpaper", R.drawable.kaneki512)
    object DrumPad: DrawerItems("Zxcursed DrumPad", R.drawable.drumpad)
}