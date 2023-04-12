package com.zxcursedsoundboard.apk.feature_contacts.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel

class ContactViewModel : ViewModel() {

    fun goToVkZxcursed(context: Context) {
        val uri: Uri = Uri.parse("https://vk.com/zxcursed")
        val browser = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(browser)
    }

    fun goToTelegramZxcursed(context: Context) {
        val uri: Uri = Uri.parse("https://t.me/zxcursed")
        val browser = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(browser)
    }

    fun goToYoutubeZxcursed(context: Context) {
        val uri: Uri = Uri.parse("https://www.youtube.com/zxcursed")
        val browser = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(browser)
    }

    fun goToMyVk(context: Context) {
        val uri: Uri = Uri.parse("https://vk.com/vangelnum")
        val browser = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(browser)

    }

    fun emailSend(context: Context) {
        val mailto = "mailto:vangelnum@gmail.com" +
                "?cc=" +
                "&subject=" + Uri.encode("Zxcursed SoundBoard")
        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse(mailto)
        context.startActivity(emailIntent)
    }

}