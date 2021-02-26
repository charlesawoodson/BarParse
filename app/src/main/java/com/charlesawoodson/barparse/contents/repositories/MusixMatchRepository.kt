package com.charlesawoodson.barparse.contents.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.charlesawoodson.barparse.contents.api.MusixMatchApi
import com.charlesawoodson.barparse.contents.model.Track
import com.charlesawoodson.barparse.contents.paging.TopTracksPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MusixMatchRepository @Inject constructor(private val musixMatchApi: MusixMatchApi) {

    fun fetchPaginatedTopTracks(): Flow<PagingData<Track>> {
        return Pager(PagingConfig(pageSize = 20, enablePlaceholders = false)) {
            TopTracksPagingSource(musixMatchApi)
        }.flow
    }
}