package com.charlesawoodson.barparse.contents.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.charlesawoodson.barparse.contents.bases.BaseViewModel
import com.charlesawoodson.barparse.contents.model.TopArtistsResponse
import com.charlesawoodson.barparse.contents.model.Track
import com.charlesawoodson.barparse.contents.repos.MusixMatchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val musicRepository: MusixMatchRepository
) : BaseViewModel() {

    val data = MutableLiveData<TopArtistsResponse>()

    init {
        musicRepository.getTopArtists("it", 1, 10)
            .subscribeOn(Schedulers.io())
            .subscribe(::handleResponse, ::handleError)
            .disposeOnClear()
    }

    val topTracks: LiveData<List<Track>> = musicRepository.getTopTracks("it", 1, 10)


    private fun handleResponse(response: TopArtistsResponse) {
        // topArtists.value = response
    }

    private fun handleError(error: Throwable) {

    }
}