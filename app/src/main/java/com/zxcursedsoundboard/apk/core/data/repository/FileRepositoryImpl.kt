package com.zxcursedsoundboard.apk.core.data.repository

import android.Manifest
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.zxcursedsoundboard.apk.R
import com.zxcursedsoundboard.apk.core.data.model.DownloadStatus
import com.zxcursedsoundboard.apk.core.domain.repository.FileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class FileRepositoryImpl @Inject constructor() : FileRepository {
    override suspend fun downloadRawFile(
        context: Context,
        rawResId: Int,
        fileName: String
    ): Flow<DownloadStatus> = flow {
        val hasWritePermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        if (!hasWritePermission) {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                1
            )
            emit(DownloadStatus.Error(context.getString(R.string.grand_permission)))
        }

        val storageDir =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        if (!storageDir.exists()) {
            if (!storageDir.mkdirs()) {
                emit(DownloadStatus.Error("Failed to create directory"))
            }
        }

        val file = File(storageDir, "$fileName.mp3")
        val inputStream = context.resources.openRawResource(rawResId)

        try {
            val outputStream = withContext(Dispatchers.IO) {
                FileOutputStream(file)
            }
            inputStream.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }
            createDownloadCompleteNotification(context, fileName, file)
            emit(DownloadStatus.Success)
        } catch (e: Exception) {
            emit(DownloadStatus.Error(e.message.toString()))
        } finally {
            withContext(Dispatchers.IO) {
                inputStream.close()
            }
        }
    }
}

private fun createDownloadCompleteNotification(context: Context, fileName: String, file: File) {
    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val channelId = getNotificationChannelId(context)
    val fileUri = FileProvider.getUriForFile(
        context,
        "${context.applicationContext.packageName}.fileprovider",
        file
    )
    val notificationIntent = Intent(Intent.ACTION_VIEW).apply {
        setDataAndType(fileUri, "audio/*")
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    val pendingIntent = PendingIntent.getActivity(
        context,
        System.currentTimeMillis().toInt(),
        notificationIntent,
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )
    val notificationBuilder = NotificationCompat.Builder(context, channelId).apply {
        setContentTitle(context.getString(R.string.download_complete_notification_title))
        setContentText(fileName)
        setSmallIcon(android.R.drawable.stat_sys_download_done)
        setContentIntent(pendingIntent)
        priority = NotificationCompat.PRIORITY_HIGH
        setAutoCancel(true)
    }
    notificationManager.notify(System.currentTimeMillis().toInt(), notificationBuilder.build())
}

private fun getNotificationChannelId(context: Context): String {
    val channelId = "my_channel"
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel =
            NotificationChannel(channelId, "My Channel", NotificationManager.IMPORTANCE_HIGH)
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
    return channelId
}
