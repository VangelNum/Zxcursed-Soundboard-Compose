package com.zxcursedsoundboard.feature_favourite.data

import com.zxcursedsoundboard.apk.core.common.Resource
import com.zxcursedsoundboard.feature_favourite.data.model.FavouriteEntity
import com.zxcursedsoundboard.feature_favourite.data.network.FavouriteDao
import com.zxcursedsoundboard.feature_favourite.domain.repository.FavouriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val dao: FavouriteDao
) : FavouriteRepository {
    override fun getAllSongs(): Flow<Resource<List<FavouriteEntity>>> = flow {
        try {
            emit(Resource.Loading())
            val response = dao.getAll()
            response.collect {
                emit(Resource.Success(it))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    override suspend fun addSong(song: FavouriteEntity) {
        dao.addSong(song)
    }

    override suspend fun deleteSong(songName: String) {
        dao.deleteSong(songName)
    }
}