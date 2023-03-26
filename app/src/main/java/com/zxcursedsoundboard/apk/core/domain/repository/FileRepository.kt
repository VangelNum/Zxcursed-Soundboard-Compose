package com.zxcursedsoundboard.apk.core.domain.repository

import android.content.Context

interface FileRepository {
    suspend fun downloadRawFile(context: Context, rawResId: Int, fileName: String): Boolean
}