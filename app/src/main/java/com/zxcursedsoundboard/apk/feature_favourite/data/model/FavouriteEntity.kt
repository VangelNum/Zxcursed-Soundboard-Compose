package com.zxcursedsoundboard.apk.feature_favourite.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_table")
data class FavouriteEntity(
    @PrimaryKey(autoGenerate = false)
    val songName: String,
    val songRes: Int
)