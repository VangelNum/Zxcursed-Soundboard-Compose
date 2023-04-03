package com.zxcursedsoundboard.apk.feature_favourite.data.network

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zxcursedsoundboard.apk.feature_favourite.data.model.FavouriteEntity

@Database(entities = [FavouriteEntity::class], version = 9)
abstract class FavouriteDatabase: RoomDatabase() {
    abstract fun getDao(): FavouriteDao
}