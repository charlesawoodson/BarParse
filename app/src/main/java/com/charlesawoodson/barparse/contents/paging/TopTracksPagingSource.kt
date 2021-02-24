package com.charlesawoodson.barparse.contents.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.charlesawoodson.barparse.contents.api.MusixMatchApi
import com.charlesawoodson.barparse.contents.model.Track

class TopTracksPagingSource(private val musixMatchApi: MusixMatchApi) :
    PagingSource<String, Track>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Track> {
        TODO()
    }

    override fun getRefreshKey(state: PagingState<String, Track>): String? {
        TODO("Not yet implemented")
    }
}