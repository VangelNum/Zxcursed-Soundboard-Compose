package com.zxcursedsoundboard.apk.core.domain.repository

import android.content.Context
import com.zxcursedsoundboard.apk.core.data.model.DownloadStatus
import kotlinx.coroutines.flow.Flow

interface FileRepository {
    suspend fun downloadRawFile(context: Context, rawResId: Int, fileName: String): Flow<DownloadStatus>
}