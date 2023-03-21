package com.zxcursedsoundboard.apk.core.presentation

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private var mediaPlayer: MediaPlayer? = null

    private var _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private var _currentPosition = MutableStateFlow(-1)
    val currentPosition: StateFlow<Int> = _currentPosition.asStateFlow()

    private var _songName = MutableStateFlow(0)
    val songName = _songName.asStateFlow()

    private var _songImage = MutableStateFlow(0)
    val songImage = _songImage.asStateFlow()

    private var _duration = MutableStateFlow(0)
    val duration: StateFlow<Int> = _duration

    fun setMedia(index: Int, context: Context, songRes: Int, songName: Int, songImage: Int) {
        _songName.value = songName
        _songImage.value = songImage
        if (index == _currentPosition.value && mediaPlayer != null) {
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
                    setOnPreparedListener {
                        it.start()
                        _isPlaying.value = true
                        _currentPosition.value = index
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
        _currentPosition.value = position
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayer?.release()
    }
}