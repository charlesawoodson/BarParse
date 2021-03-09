package com.charlesawoodson.barparse.contents.viewmodels

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.charlesawoodson.barparse.contents.bases.BaseViewModel
import com.charlesawoodson.barparse.contents.repositories.MusixMatchRepository
import com.charlesawoodson.barparse.contents.responses.Album
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ArtistAlbumsViewModel @Inject constructor(
    private val musicRepository: MusixMatchRepository
) : BaseViewModel() {

    fun fetchPaginatedArtistAlbums(artistId: String): Flow<PagingData<Album>> {
        return musicRepository.fetchPaginatedArtistAlbums(artistId).cachedIn(viewModelScope)
    }
}
