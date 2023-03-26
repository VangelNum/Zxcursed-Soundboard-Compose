package com.zxcursedsoundboard.apk.core.data.model

sealed class DownloadStatus {
    object Loading : DownloadStatus()
    object Success : DownloadStatus()
    data class Error(val message: String) : DownloadStatus()
}