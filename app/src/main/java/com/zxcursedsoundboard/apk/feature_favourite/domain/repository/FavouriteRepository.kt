package com.zxcursedsoundboard.apk.feature_favourite.domain.repository

import com.zxcursedsoundboard.apk.core.common.Resource
import com.zxcursedsoundboard.apk.feature_favourite.data.model.FavouriteEntity
import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {
    fun getAllSongs(): Flow<Resource<List<FavouriteEntity>>>
    suspend fun addSong(song: FavouriteEntity)
    suspend fun deleteSong(songName: Int)
}