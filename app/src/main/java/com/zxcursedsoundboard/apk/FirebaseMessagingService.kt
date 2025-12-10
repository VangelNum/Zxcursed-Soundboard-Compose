package com.zxcursedsoundboard.apk

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        if (message.notification != null) {
            Log.d("tag", "${message.notification!!.title}")
        } else {
            Log.d("tag", "its null")
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

}