package com.zxcursedsoundboard.apk.feature_sounds_zxcursed.data

import com.google.firebase.firestore.FirebaseFirestore
import com.zxcursedsoundboard.apk.core.common.ResourceFirebase
import com.zxcursedsoundboard.apk.core.data.model.MediaItems
import com.zxcursedsoundboard.apk.feature_sounds_zxcursed.domain.ZxcursedSoundsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ZxcursedSoundsRepositoryImpl @Inject constructor(
    private val bdFirebase: FirebaseFirestore
) : ZxcursedSoundsRepository {
    override fun getZxcursedSounds(): Flow<ResourceFirebase<List<MediaItems>>> = flow {
        emit(ResourceFirebase.Loading())
        try {
            val items = bdFirebase.collection("audioSound").get().await().toObjects(MediaItems::class.java)
            if (items.isNotEmpty()) {
                emit(ResourceFirebase.Success(items))
            } else {
                emit(ResourceFirebase.Error("Error loading data"))
            }
        } catch (e: Exception) {
            emit(ResourceFirebase.Error(e.message.toString()))
        }
    }
}