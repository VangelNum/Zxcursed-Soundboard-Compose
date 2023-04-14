package com.zxcursedsoundboard.apk.feature_favourite.data.network

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import com.zxcursedsoundboard.apk.feature_favourite.data.model.FavouriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteDao {

    @Query("SELECT * FROM favourite_table")
    fun getAll(): Flow<List<FavouriteEntity>>

    @Query("DELETE FROM favourite_table WHERE songName = :song")
    suspend fun deleteSong(song: String): Int

    @Insert(onConflict = IGNORE)
    suspend fun addSong(song: FavouriteEntity)
}