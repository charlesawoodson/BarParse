package com.charlesawoodson.barparse.contents.viewmodels

import android.util.Log
import androidx.hilt.Assisted
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.charlesawoodson.barparse.contents.api.MusixMatchApi
import com.charlesawoodson.barparse.contents.bases.BaseViewModel
import com.charlesawoodson.barparse.contents.extensions.Mvi
import com.charlesawoodson.barparse.contents.model.Track
import com.charlesawoodson.barparse.contents.model.TrackLyricsResponse
import com.charlesawoodson.barparse.contents.repositories.MusixMatchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LyricsViewModel @Inject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val musixMatchApi: MusixMatchApi
) : BaseViewModel() {

    private var _lyricsResponse = MutableLiveData<TrackLyricsResponse>()

    val lyricsLiveData: LiveData<TrackLyricsResponse>
        get() = _lyricsResponse

    fun fetchTrackLyrics(trackId: String) {
        musixMatchApi.getTrackLyrics(trackId)
            .subscribeOn(Schedulers.io())
            .subscribe(::handleResponse, ::handleError)
            .disposeOnClear()
    }

    private fun handleResponse(lyricsResponse: TrackLyricsResponse) {
        viewModelScope.launch {
            _lyricsResponse.value = lyricsResponse
        }
    }

    private fun handleError(error: Throwable) {
        Log.d("error", error.toString())
    }
}
