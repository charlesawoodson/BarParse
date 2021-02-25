package com.charlesawoodson.barparse.contents.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.charlesawoodson.barparse.contents.api.MusixMatchApi
import com.charlesawoodson.barparse.contents.model.Track
import retrofit2.HttpException
import java.io.IOException

class TopTracksPagingSource(private val musixMatchApi: MusixMatchApi) :
    PagingSource<Int, Track>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Track> {
        return try {
            val page = params.key ?: DEFAULT_PAGE_INDEX
            val response = musixMatchApi.getTopTracks("us", page, params.loadSize)
            val trackList = response.body()?.message?.body?.trackList
            val topTracks = trackList?.map { it.track } ?: emptyList()
            LoadResult.Page(
                topTracks,
                prevKey = if (page == DEFAULT_PAGE_INDEX) null else (page - 1),
                nextKey = if (topTracks.isEmpty()) null else (page + 1)
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Track>): Int? {
        return null
    }

    override val keyReuseSupported: Boolean = true

    companion object {
        const val DEFAULT_PAGE_INDEX = 1
    }
}