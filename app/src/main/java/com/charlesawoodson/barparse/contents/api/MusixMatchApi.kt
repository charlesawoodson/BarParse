package com.charlesawoodson.barparse.contents.api

import com.charlesawoodson.barparse.contents.model.TopArtistsResponse
import com.charlesawoodson.barparse.contents.model.TopTracksResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by charles.adams on 05/24/2020
 */

interface MusixMatchApi {

    // Provides you the list of top artists from a given country
    @GET("chart.artists.get")
    fun getTopArtists(
        @Query("country") country: String,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): Observable<TopArtistsResponse>

    @GET("chart.tracks.get")
    fun getTopTracks(
        @Query("country") country: String,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): Call<TopTracksResponse>

}