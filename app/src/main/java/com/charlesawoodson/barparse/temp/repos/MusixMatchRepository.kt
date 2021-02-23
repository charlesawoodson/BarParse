package com.charlesawoodson.barparse.temp.repos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.charlesawoodson.barparse.temp.api.MusicResponse
import com.charlesawoodson.barparse.temp.api.MusixMatchApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MusixMatchRepository @Inject constructor(private val musixMatchApi: MusixMatchApi) {

    fun getTopArtists(country: String, page: Int, pageSize: Int): LiveData<MusicResponse> {

        val data = MutableLiveData<MusicResponse>()

        musixMatchApi.getTopArtists(country, page, pageSize)
            .enqueue(object : Callback<MusicResponse> {
                override fun onResponse(
                    call: Call<MusicResponse>,
                    response: Response<MusicResponse>
                ) {
                    data.value = response.body()
                }

                override fun onFailure(call: Call<MusicResponse>, t: Throwable) {
                    Log.d("onFailure", t.toString())
                }
            })

        return data
    }

    fun getTopTracks(country: String, page: Int, pageSize: Int): LiveData<MusicResponse> {
        val data = MutableLiveData<MusicResponse>()

        musixMatchApi.getTopTracks(country, page, pageSize)
            .enqueue(object : Callback<MusicResponse> {
                override fun onResponse(
                    call: Call<MusicResponse>,
                    response: Response<MusicResponse>
                ) {
                    data.value = response.body()
                }

                override fun onFailure(call: Call<MusicResponse>, t: Throwable) {
                    Log.d("onFailure", t.toString())
                }
            })

        return data
    }
}