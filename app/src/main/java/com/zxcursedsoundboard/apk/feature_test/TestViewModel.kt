package com.zxcursedsoundboard.apk.feature_test

import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.zxcursedsoundboard.apk.core.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


data class ItemsFirebase(
    val author: String = "",
    val name: String = "",
    val image: String = "",
    val audio: String = ""
)

@HiltViewModel
class TestViewModel @Inject constructor() : ViewModel() {
    val mp: MediaPlayer? = null

    private val _song = MutableStateFlow<Resource<List<ItemsFirebase>>>(Resource.Loading())
    val song = _song.asStateFlow()

    init {
        getAudio()
    }

    fun getAudio() {
        viewModelScope.launch {
            try {
                val db = Firebase.firestore
                val query = db.collection("audio").get().await()
                val items = query.documents.mapNotNull {
                    it.toObject(ItemsFirebase::class.java)
                }
                _song.value = Resource.Success(items)
            } catch (e: Exception) {
                _song.value = Resource.Error(e.message.toString())
            }
        }
    }
}