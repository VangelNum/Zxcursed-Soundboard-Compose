package com.zxcursedsoundboard.apk.feature_sounds_zxcursed.domain

import com.zxcursedsoundboard.apk.core.common.ResourceFirebase
import com.zxcursedsoundboard.apk.core.data.model.MediaItems
import kotlinx.coroutines.flow.Flow

interface ZxcursedSoundsRepository {
    fun getZxcursedSounds(): Flow<ResourceFirebase<List<MediaItems>>>
}