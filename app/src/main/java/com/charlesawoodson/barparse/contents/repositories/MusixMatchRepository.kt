package com.charlesawoodson.barparse.contents.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.charlesawoodson.barparse.contents.api.MusixMatchApi
import com.charlesawoodson.barparse.contents.databases.MusixMatchDatabase
import com.charlesawoodson.barparse.contents.model.Track
import com.charlesawoodson.barparse.contents.paging.TopTracksPagingSource
import com.charlesawoodson.barparse.contents.repositories.mediators.TopTracksRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MusixMatchRepository @Inject constructor(
    private val musixMatchApi: MusixMatchApi,
    private val musixMatchDatabase: MusixMatchDatabase
) {

    fun fetchTracks(): Flow<PagingData<Track>> {
        return Pager(
            PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                prefetchDistance = 5
            ),
            remoteMediator = TopTracksRemoteMediator(musixMatchApi, musixMatchDatabase),
            pagingSourceFactory = { musixMatchDatabase.trackDao().getTracks() }
        ).flow
    }

    fun fetchPaginatedTopTracks(): Flow<PagingData<Track>> {
        return Pager(PagingConfig(pageSize = 20, enablePlaceholders = false)) {
            TopTracksPagingSource(musixMatchApi)
        }.flow
    }
}