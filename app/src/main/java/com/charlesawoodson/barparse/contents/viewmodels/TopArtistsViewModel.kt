package com.charlesawoodson.barparse.contents.viewmodels

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.charlesawoodson.barparse.contents.bases.BaseViewModel
import com.charlesawoodson.barparse.contents.repositories.MusixMatchRepository
import com.charlesawoodson.barparse.contents.responses.Artist
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TopArtistsViewModel @Inject constructor(
    private val musicRepository: MusixMatchRepository
) : BaseViewModel() {

    fun fetchPaginatedTopArtists(): Flow<PagingData<Artist>> {
        return musicRepository.fetchPaginatedTopArtists().cachedIn(viewModelScope)
    }
}
