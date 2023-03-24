package com.zxcursedsoundboard.apk.core.presentation

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zxcursedsoundboard.apk.R
import com.zxcursedsoundboard.apk.core.data.MediaItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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

    private var _looping = MutableStateFlow(false)
    val looping = _looping.asStateFlow()

    val mediaItemsMain = listOf(
        MediaItem(R.raw.cursed2, R.string.pivo, R.string.zxcursed, R.drawable.pivo),
        MediaItem(R.raw.cursed3, R.string.molchat, R.string.zxcursed, R.drawable.molchat),
        MediaItem(R.raw.cursed4, R.string.traxat, R.string.zxcursed, R.drawable.traxat),
        MediaItem(R.raw.cursed5, R.string.pikaper, R.string.zxcursed, R.drawable.pikaper),
        MediaItem(R.raw.cursed6, R.string.sydalut, R.string.zxcursed, R.drawable.sydalut),
        MediaItem(R.raw.cursed7, R.string.madmyazel, R.string.zxcursed, R.drawable.madmyazel),
        MediaItem(R.raw.cursed8, R.string.chtoetoblyat, R.string.zxcursed, R.drawable.chtoetoblyat),
        MediaItem(R.raw.cursed9, R.string.spasibo, R.string.zxcursed, R.drawable.spasibo),
        MediaItem(R.raw.cursed10, R.string.denegnet, R.string.zxcursed, R.drawable.denegnet),
        MediaItem(R.raw.cursed11, R.string.minuspivo, R.string.zxcursed, R.drawable.minuspivo),
    )

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
            togglePlayback()
        } else {
            mediaPlayer?.apply {
                stop()
                release()
            }
            mediaPlayer = MediaPlayer
                .create(context, songRes)
                .apply {
                    setOnCompletionListener { media ->
                        if (_looping.value) {
                            media.start()
                        } else {
                            playNextMedia(context)
                            _isPlaying.value = false
                        }
                    }
                    setOnPreparedListener { media ->
                        media.start()
                        _isPlaying.value = true
                        _currentPositionIndex.value = index
                        _duration.value = media.duration
                    }
                }
        }
        updateCurrentTimePosition()
    }


    private fun togglePlayback() {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.pause()
            _isPlaying.value = false
        } else {
            mediaPlayer?.start()
            _isPlaying.value = true
        }
    }


    //work only in dispatcher.main
    private fun updateCurrentTimePosition() {
        viewModelScope.launch(Dispatchers.Main) {
            while (isPlaying.value) {
                val currentPosition = mediaPlayer?.currentPosition ?: 0
                _currentTimeMedia.value = currentPosition
                delay(16L) //just to not block UI
            }
        }
    }

    fun setLopping() {
        _looping.value = !_looping.value
    }

    fun playNextMedia(context: Context) {
        val nextIndex = (_currentPositionIndex.value + 1) % mediaItemsMain.size
        setMedia(
            nextIndex,
            context,
            mediaItemsMain[nextIndex].audioResId,
            mediaItemsMain[nextIndex].songNameRes,
            mediaItemsMain[nextIndex].songAuthor,
            mediaItemsMain[nextIndex].imageRes
        )
    }

    fun playPreviousMedia(context: Context) {
        var newIndex = currentPositionIndex.value - 1
        if (newIndex < 0) {
            newIndex = mediaItemsMain.size - 1
        }
        setMedia(
            newIndex,
            context,
            mediaItemsMain[newIndex].audioResId,
            mediaItemsMain[newIndex].songNameRes,
            mediaItemsMain[newIndex].songAuthor,
            mediaItemsMain[newIndex].imageRes
        )
    }

    fun setCurrentTime(position: Int) {
        mediaPlayer?.seekTo(position)
        _currentTimeMedia.value = position
    }

    fun play() {
        togglePlayback()
        updateCurrentTimePosition()
    }

    fun pause() {
        togglePlayback()
        updateCurrentTimePosition()
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayer?.release()
    }
}