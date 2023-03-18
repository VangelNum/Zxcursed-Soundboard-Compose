package com.zxcursedsoundboard.feature_favourite.data.network

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zxcursedsoundboard.feature_favourite.data.model.FavouriteEntity

@Database(entities = [FavouriteEntity::class], version = 6)
abstract class FavouriteDatabase: RoomDatabase() {
    abstract fun getDao(): FavouriteDao
}