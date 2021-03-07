package com.charlesawoodson.barparse.contents.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.charlesawoodson.barparse.contents.api.MusixMatchApi
import com.charlesawoodson.barparse.contents.responses.Artist
import retrofit2.HttpException
import java.io.IOException

class ArtistAlbumsPagingSource(private val musixMatchApi: MusixMatchApi) :
    PagingSource<Int, Artist>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Artist> {
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = musixMatchApi.getArtistAlbums(DEFAULT_COUNTRY, page, DEFAULT_PAGE_SIZE)
            val artistList = response.body()?.message?.body?.artistList
            val topArtists = artistList?.map { it.artist } ?: emptyList()
            LoadResult.Page(
                topArtists,
                prevKey = if (page == DEFAULT_PAGE_INDEX) null else (page - 1),
                nextKey = if (topArtists.isEmpty()) null else (page + 1)
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Artist>): Int? {
        return null
    }

    override val keyReuseSupported: Boolean = true

    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 15
        const val DEFAULT_COUNTRY = "us" // todo: remove duplication
    }
}