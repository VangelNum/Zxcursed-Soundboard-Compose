package com.zxcursedsoundboard.apk.feature_main.domain

import com.zxcursedsoundboard.apk.core.common.ResourceFirebase
import com.zxcursedsoundboard.apk.core.data.model.MediaItems
import kotlinx.coroutines.flow.Flow

interface ZxcursedMainRepository {
    fun getZxcursedMain(): Flow<ResourceFirebase<List<MediaItems>>>
}