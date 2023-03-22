package com.zxcursedsoundboard.apk.core.presentation

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private var mediaPlayer: MediaPlayer? = null

    private var _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private var _currentPositionIndex = MutableStateFlow(-1)
    val currentPositionIndex: StateFlow<Int> = _currentPositionIndex.asStateFlow()

    private var _songName = MutableStateFlow(0)
    val songName = _songName.asStateFlow()

    private var _songAuthor = MutableStateFlow(0)
    val songAuthor = _songAuthor.asStateFlow()

    private var _songImage = MutableStateFlow(0)
    val songImage = _songImage.asStateFlow()

    private var _duration = MutableStateFlow(0)
    val duration = _duration.asStateFlow()

    private var _currentTimeMedia = MutableStateFlow(0)
    val currentTimeMedia = _currentTimeMedia.asStateFlow()


    fun setMedia(
        index: Int,
        context: Context,
        songRes: Int,
        songName: Int,
        songAuthor: Int,
        songImage: Int
    ) {
        _songAuthor.value = songAuthor
        _songName.value = songName
        _songImage.value = songImage
        if (index == _currentPositionIndex.value && mediaPlayer != null) {
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.pause()
                _isPlaying.value = false
            } else {
                mediaPlayer?.start()
                _isPlaying.value = true
            }
        } else {
            mediaPlayer?.apply {
                stop()
                release()
            }
            mediaPlayer = MediaPlayer
                .create(context, songRes)
                .apply {
                    setOnCompletionListener {
                        _isPlaying.value = false
                    }
                    setOnPreparedListener { media ->
                        media.start()
                        _isPlaying.value = true
                        _currentPositionIndex.value = index
                        _duration.value = media.duration

                    }
                }
        }
        updateTime()
    }

    private fun updateTime() {
        viewModelScope.launch {
            while (isPlaying.value) {
                if (mediaPlayer!=null) {
                    _currentTimeMedia.value = mediaPlayer!!.currentPosition
                }
            }
        }
    }

    fun play() {
        mediaPlayer?.start()
        _isPlaying.value = true
    }

    fun pause() {
        mediaPlayer?.pause()
        _isPlaying.value = false
    }

    fun seekTo(position: Int) {
        mediaPlayer?.seekTo(position)
        _currentPositionIndex.value = position
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayer?.release()
    }
}