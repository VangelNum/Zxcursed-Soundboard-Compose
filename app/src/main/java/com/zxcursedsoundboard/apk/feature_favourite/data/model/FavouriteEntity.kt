package com.zxcursedsoundboard.apk.feature_favourite.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_table")
data class  FavouriteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val songName: String,
    val songAuthor: String,
    val songImageRes: String,
    val songAudioRes: String
)