package com.charlesawoodson.barparse.contents.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.charlesawoodson.barparse.contents.bases.BaseViewModel
import com.charlesawoodson.barparse.contents.model.Track
import com.charlesawoodson.barparse.contents.repos.MusixMatchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val musicRepository: MusixMatchRepository
) : BaseViewModel() {

    /*init {
        musicRepository.getTopArtists("it", 1, 10)
            .subscribeOn(Schedulers.io())
            .subscribe(::handleResponse, ::handleError)
            .disposeOnClear()
    }*/


    fun fetchPaginatedTopTracks(): Flow<PagingData<Track>> {
        return musicRepository.fetchPaginatedTopTracks().cachedIn(viewModelScope)
    }


    /*val topTracks: LiveData<List<Track>> = musicRepository.getTopTracks("it", 1, 10)


    private fun handleResponse(response: TopArtistsResponse) {
        // topArtists.value = response
    }

    private fun handleError(error: Throwable) {

    }*/
}