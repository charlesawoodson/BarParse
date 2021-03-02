package com.charlesawoodson.barparse.contents.viewmodels

import androidx.hilt.Assisted
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.charlesawoodson.barparse.contents.bases.BaseViewModel
import com.charlesawoodson.barparse.contents.extensions.Mvi
import com.charlesawoodson.barparse.contents.model.Track
import com.charlesawoodson.barparse.contents.repositories.MusixMatchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TopTracksViewModel @Inject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val musicRepository: MusixMatchRepository
) : BaseViewModel() {

    fun fetchPaginatedTopTracks(): Flow<PagingData<Track>> {
        return musicRepository.fetchPaginatedTopTracks().cachedIn(viewModelScope)
    }
}
