package com.charlesawoodson.barparse.contents.repos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.charlesawoodson.barparse.contents.api.MusixMatchApi
import com.charlesawoodson.barparse.contents.model.TopArtistsResponse
import com.charlesawoodson.barparse.contents.model.TopTracksResponse
import com.charlesawoodson.barparse.contents.model.Track
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MusixMatchRepository @Inject constructor(
    private val musixMatchApi: MusixMatchApi
) {

    fun getTopArtists(country: String, page: Int, pageSize: Int): Observable<TopArtistsResponse> {
        return musixMatchApi.getTopArtists(country, page, pageSize)
    }

    fun getTopTracks(country: String, page: Int, pageSize: Int): LiveData<List<Track>> {
        val data = MutableLiveData<List<Track>>()

        musixMatchApi.getTopTracks(country, page, pageSize)
            .enqueue(object : Callback<TopTracksResponse> {
                override fun onResponse(
                    call: Call<TopTracksResponse>,
                    response: Response<TopTracksResponse>
                ) {

                    data.value = response.body()?.message?.body?.trackList?.map {
                        it.track
                    }
                }

                override fun onFailure(call: Call<TopTracksResponse>, t: Throwable) {
                    Log.d("onFailure", t.toString())
                }
            })

        return data
    }
}