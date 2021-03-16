package com.charlesawoodson.barparse.contents.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.charlesawoodson.barparse.contents.bases.BaseViewModel
import com.charlesawoodson.barparse.contents.repositories.MusixMatchRepository
import com.charlesawoodson.barparse.contents.responses.TrackLyricsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LyricsViewModel @Inject constructor(
    private val musixMatchRepository: MusixMatchRepository
) : BaseViewModel() {

    private var _lyricsResponse = MutableLiveData<String>()

    val lyricsLiveData: LiveData<String>
        get() = _lyricsResponse

    fun fetchTrackLyrics(trackId: String) {
        musixMatchRepository.fetchTrackLyrics(trackId)
            .subscribeOn(Schedulers.io())
            .subscribe(::handleResponse, ::handleError)
            .disposeOnClear()
    }

    private fun handleResponse(lyricsResponse: TrackLyricsResponse) {
        viewModelScope.launch {
            _lyricsResponse.value = lyricsResponse.message.body.lyrics.lyricsBody
        }
    }

    private fun handleError(error: Throwable) {
        Log.d("error", error.toString())
    }
}
