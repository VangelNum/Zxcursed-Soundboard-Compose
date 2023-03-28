package com.zxcursedsoundboard.apk.core.domain.repository

import android.content.Context

interface FileRepository {
    fun downloadRawFile(context: Context, rawResId: Int, fileName: String): Boolean
}