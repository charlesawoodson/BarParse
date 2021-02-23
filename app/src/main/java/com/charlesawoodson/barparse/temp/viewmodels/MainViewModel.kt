package com.charlesawoodson.barparse.temp.viewmodels

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.charlesawoodson.barparse.temp.api.MusicResponse
import com.charlesawoodson.barparse.temp.repos.MusixMatchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val musicRepository: MusixMatchRepository
) : ViewModel(), LifecycleObserver {
    val topArtists: LiveData<MusicResponse> = musicRepository.getTopArtists("it", 1, 10)
    val topTracks: LiveData<MusicResponse> = musicRepository.getTopTracks("it", 1, 10)
}