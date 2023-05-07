package com.zxcursedsoundboard.apk.feature_snail.domain

import com.zxcursedsoundboard.apk.core.common.ResourceFirebase
import com.zxcursedsoundboard.apk.core.data.model.MediaItems
import kotlinx.coroutines.flow.Flow

interface SnailRepository {
    fun getSnailSounds(): Flow<ResourceFirebase<List<MediaItems>>>
}