package com.charlesawoodson.barparse.contents.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.charlesawoodson.barparse.contents.bases.BaseViewModel
import com.charlesawoodson.barparse.contents.repositories.MusixMatchRepository
import com.charlesawoodson.barparse.contents.responses.GetTracksResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumTracksViewModel @Inject constructor(
    private val musixMatchRepository: MusixMatchRepository
) : BaseViewModel() {

    private var _albumTracksResponse = MutableLiveData<GetTracksResponse>()

    val albumTracksResponse: LiveData<GetTracksResponse>
        get() = _albumTracksResponse

    fun fetchAlbumTracks(albumId: String, trackCount: Int) {
        musixMatchRepository.fetchAlbumTracks(albumId, trackCount)
            .subscribeOn(Schedulers.io())
            .subscribe(::handleResponse, ::handleError)
            .disposeOnClear()
    }

    private fun handleResponse(albumTracksResponse: GetTracksResponse) {
        viewModelScope.launch {
            _albumTracksResponse.value = albumTracksResponse
        }
    }

    private fun handleError(error: Throwable) {
        Log.d("error", error.toString())
    }
}
