package com.zxcursedsoundboard.apk.core.presentation.drawer_layout

import androidx.annotation.StringRes
import com.zxcursedsoundboard.apk.R

sealed class DrawerItems(@StringRes val title: Int, val imageRes: Int) {
    object Contacts : DrawerItems(R.string.contacts, R.drawable.ic_baseline_message_24)
    object Share : DrawerItems(R.string.share, R.drawable.ic_baseline_share_24)
    object Settings : DrawerItems(R.string.settings, R.drawable.baseline_settings_24)
    object Estimate : DrawerItems(R.string.rate, R.drawable.ic_baseline_star_24)
    object Additionally: DrawerItems(R.string.additionally,R.drawable.baseline_read_more_24)
}

sealed class DrawerItemsAnotherApplications(val title: String, val imageRes: Int) {
    object Wallpaper: DrawerItemsAnotherApplications("Zxcursed Wallpaper", R.drawable.kaneki512)
    object DrumPad: DrawerItemsAnotherApplications("Zxcursed DrumPad", R.drawable.drumpad)
}