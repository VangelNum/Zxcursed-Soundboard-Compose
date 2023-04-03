package com.zxcursedsoundboard.apk.feature_favourite.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_table")
data class FavouriteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val songName: Int,
    val songAuthor: Int,
    val songImageRes: Int,
    val songAudioRes: Int
)