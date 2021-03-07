package com.charlesawoodson.barparse.contents.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.charlesawoodson.barparse.contents.api.MusixMatchApi
import com.charlesawoodson.barparse.contents.databases.MusixMatchDatabase
import com.charlesawoodson.barparse.contents.responses.Artist
import com.charlesawoodson.barparse.contents.responses.Track
import com.charlesawoodson.barparse.contents.paging.TopArtistsPagingSource
import com.charlesawoodson.barparse.contents.paging.TopTracksPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MusixMatchRepository @Inject constructor(
    private val musixMatchApi: MusixMatchApi,
    private val musixMatchDatabase: MusixMatchDatabase
) {

    fun fetchPaginatedTopTracks(): Flow<PagingData<Track>> {
        return Pager(PagingConfig(pageSize = 20, enablePlaceholders = false)) {
            TopTracksPagingSource(musixMatchApi)
        }.flow
    }

    fun fetchPaginatedTopArtists(): Flow<PagingData<Artist>> {
        return Pager(PagingConfig(pageSize = 20, enablePlaceholders = false)) {
            TopArtistsPagingSource(musixMatchApi)
        }.flow
    }

    fun fetchPaginatedArtistAlbums(): Flow<PagingData<Artist>> {
        return Pager(PagingConfig(pageSize = 20, enablePlaceholders = false)) {
            ArtistAlbumsPagingSource(musixMatchApi)
        }.flow
    }

    suspend fun saveTrack(track: Track) {
        musixMatchDatabase.trackDao().saveTrack(track)
    }
}