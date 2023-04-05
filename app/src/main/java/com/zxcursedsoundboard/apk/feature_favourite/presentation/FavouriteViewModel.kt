package com.zxcursedsoundboard.apk.feature_favourite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zxcursedsoundboard.apk.core.common.Resource
import com.zxcursedsoundboard.apk.feature_favourite.data.model.FavouriteEntity
import com.zxcursedsoundboard.apk.feature_favourite.domain.repository.FavouriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val repository: FavouriteRepository
): ViewModel() {

    private val _favouriteState = MutableStateFlow<Resource<List<FavouriteEntity>>>(Resource.Loading())
    val favouriteState = _favouriteState.asStateFlow()

    init {
        getAllFavouriteSongs()
    }

    private fun getAllFavouriteSongs() {
        repository.getAllSongs().onEach { res->
            _favouriteState.value = res
        }.launchIn(viewModelScope)
    }

    fun addSong(song: FavouriteEntity) {
        viewModelScope.launch {
            repository.addSong(song)
        }
    }

    fun deleteSong(songName: String) {
        viewModelScope.launch {
            repository.deleteSong(songName)
        }
    }

}