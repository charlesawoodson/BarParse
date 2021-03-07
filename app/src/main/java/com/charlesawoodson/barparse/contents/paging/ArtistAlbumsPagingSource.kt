package com.charlesawoodson.barparse.contents.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.charlesawoodson.barparse.contents.api.MusixMatchApi
import com.charlesawoodson.barparse.contents.responses.Album
import com.charlesawoodson.barparse.contents.responses.Artist
import retrofit2.HttpException
import java.io.IOException

class ArtistAlbumsPagingSource(
    private val musixMatchApi: MusixMatchApi,
    private val artistId: String
) : PagingSource<Int, Album>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Album> {
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = musixMatchApi.getArtistAlbums(
                artistId = artistId,
                artistMbId = "",
                groupAlbumName = "",
                sortByDate = "desc",
                page = page,
                pageSize = DEFAULT_PAGE_SIZE
            )
            val albumList = response.body()?.message?.body?.albumList
            val artistAlbums = albumList?.map { it.album } ?: emptyList()
            LoadResult.Page(
                artistAlbums,
                prevKey = if (page == DEFAULT_PAGE_INDEX) null else (page - 1),
                nextKey = if (artistAlbums.isEmpty()) null else (page + 1)
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Album>): Int? {
        return null
    }

    override val keyReuseSupported: Boolean = true

    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 15
    }
}