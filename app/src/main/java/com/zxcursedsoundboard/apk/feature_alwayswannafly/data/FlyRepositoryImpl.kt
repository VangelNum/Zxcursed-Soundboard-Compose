package com.zxcursedsoundboard.apk.feature_alwayswannafly.data

import com.google.firebase.firestore.FirebaseFirestore
import com.zxcursedsoundboard.apk.core.common.ResourceFirebase
import com.zxcursedsoundboard.apk.core.data.model.MediaItems
import com.zxcursedsoundboard.apk.feature_alwayswannafly.domain.FlyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FlyRepositoryImpl @Inject constructor(
    private val bdFirebase: FirebaseFirestore
) : FlyRepository {
    override fun getFlySounds(): Flow<ResourceFirebase<List<MediaItems>>> = flow {
        emit(ResourceFirebase.Loading())
        try {
            val items = bdFirebase.collection("audioFly").get().await().toObjects(MediaItems::class.java)
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