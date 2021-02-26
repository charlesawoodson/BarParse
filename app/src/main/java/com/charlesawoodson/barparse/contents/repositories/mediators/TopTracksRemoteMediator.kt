package com.charlesawoodson.barparse.contents.repositories.mediators

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.charlesawoodson.barparse.contents.api.MusixMatchApi
import com.charlesawoodson.barparse.contents.databases.MusixMatchDatabase
import com.charlesawoodson.barparse.contents.model.Track
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import kotlin.math.max

@OptIn(ExperimentalPagingApi::class)
class TopTracksRemoteMediator @Inject constructor(
    private val musixMatchApi: MusixMatchApi,
    private val musixMatchDatabase: MusixMatchDatabase
) : RemoteMediator<Int, Track>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Track>): MediatorResult {
        val page = max(DEFAULT_PAGE_INDEX, state.pages.size)
        return try {
            val response = musixMatchApi.getTopTracks(DEFAULT_COUNTRY, page, DEFAULT_PAGE_SIZE)

            val trackList = response.body()?.message?.body?.trackList
            val topTracks = trackList?.map { it.track } ?: emptyList()

            if (topTracks.isNotEmpty()) {
                musixMatchDatabase.trackDao().saveTracks(topTracks)
            }

            MediatorResult.Success(endOfPaginationReached = topTracks.isEmpty())
        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }
    }

    companion object {
        var DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 15
        const val DEFAULT_COUNTRY = "us" // todo: remove duplication
    }
}