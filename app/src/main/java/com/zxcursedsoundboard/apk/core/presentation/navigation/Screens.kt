package com.zxcursedsoundboard.apk.core.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.zxcursedsoundboard.apk.R
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
sealed interface Screens {
    val route: String
        get() = this::class.simpleName!!

    @get:StringRes
    @Transient
    val nameScreen: Int get() = 0

    @get:DrawableRes
    @Transient
    val image: Int get() = 0

    @Serializable
    data object ZxcursedMainScreen : Screens {
        override val nameScreen: Int = R.string.cursed
        override val image: Int = R.drawable.nenado
    }

    @Serializable
    data object ZxcursedSoundScreen : Screens {
        override val nameScreen: Int = R.string.sounds_cursed
        override val image: Int = R.drawable.manabreak
    }

    @Serializable
    data object FavouriteScreen : Screens {
        override val nameScreen: Int = R.string.favourite
        override val image: Int = R.drawable.stack
    }

    @Serializable
    data object SnailScreen : Screens {
        override val nameScreen: Int = R.string.snail
        override val image: Int = R.drawable.photo8
    }

    @Serializable
    data object AlwaysWannaFlyScreen : Screens {
        override val nameScreen: Int = R.string.fly
        override val image: Int = R.drawable.photo12
    }

    @Serializable
    data object NavigationScreen : Screens {
        override val nameScreen: Int = R.string.navigation
        override val image: Int = R.drawable.photo11
    }

    @Serializable
    data object WatchMediaScreen : Screens {
        override val nameScreen: Int = R.string.watch_audio
        override val image: Int = R.drawable.photo11
    }

    @Serializable
    data object ContactScreen : Screens {
        override val nameScreen: Int = R.string.contacts
        override val image: Int = R.drawable.photo11
    }

    @Serializable
    data object SettingsScreen : Screens {
        override val nameScreen: Int = R.string.settings
        override val image: Int = R.drawable.photo11
    }

    @Serializable
    data object AdditionallyScreen : Screens {
        override val nameScreen: Int = R.string.additionally
        override val image: Int = R.drawable.photo11
    }

    @Serializable
    data object CatScreen : Screens {
        override val nameScreen: Int = R.string.cat
        override val image: Int = R.drawable.photo11
    }

    @Serializable
    data object ZxcursedBack : Screens {
        override val nameScreen: Int = R.string.zxcursed_in_back
        override val image: Int = R.drawable.photo11
    }
}
