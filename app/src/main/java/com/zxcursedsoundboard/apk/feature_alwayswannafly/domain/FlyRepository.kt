package com.zxcursedsoundboard.apk.feature_alwayswannafly.domain

import com.zxcursedsoundboard.apk.core.common.ResourceFirebase
import com.zxcursedsoundboard.apk.core.data.model.MediaItems
import kotlinx.coroutines.flow.Flow

interface FlyRepository {
    fun getFlySounds() : Flow<ResourceFirebase<List<MediaItems>>>
}