package com.charlesawoodson.barparse.contents.repos

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.charlesawoodson.barparse.contents.api.MusixMatchApi
import com.charlesawoodson.barparse.contents.model.Track
import com.charlesawoodson.barparse.contents.paging.TopTracksPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MusixMatchRepository @Inject constructor(private val musixMatchApi: MusixMatchApi) {

    /*fun getTopArtists(country: String, page: Int, pageSize: Int): Observable<TopArtistsResponse> {
        return musixMatchApi.getTopArtists(country, page, pageSize)
    }

    fun getTopTracks(country: String, page: Int, pageSize: Int): LiveData<List<Track>> {
        val data = MutableLiveData<List<Track>>()

        *//*musixMatchApi.getTopTracks(country, page, pageSize)
            .enqueue(object : Callback<TopTracksResponse> {
                override fun onResponse(
                    call: Call<TopTracksResponse>, response: Response<TopTracksResponse>
                ) {

                    data.value = response.body()?.message?.body?.trackList?.map {
                        it.track
                    }
                }

                override fun onFailure(call: Call<TopTracksResponse>, t: Throwable) {
                    Log.d("onFailure", t.toString())
                }
            })*//*

        return data
    }*/

    fun fetchPaginatedTopTracks(): Flow<PagingData<Track>> {
        return Pager(PagingConfig(pageSize = 20, enablePlaceholders = false)) {
            TopTracksPagingSource(musixMatchApi)
        }.flow
    }
}