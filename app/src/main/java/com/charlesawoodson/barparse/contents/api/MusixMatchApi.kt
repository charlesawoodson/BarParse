package com.charlesawoodson.barparse.contents.api

import com.charlesawoodson.barparse.contents.model.TopArtistsResponse
import com.charlesawoodson.barparse.contents.model.TopTracksResponse
import com.charlesawoodson.barparse.contents.model.TrackLyricsResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by charles.adams on 05/24/2020
 */

interface MusixMatchApi {

    // Provides you the list of top artists from a given country
    @GET("chart.artists.get")
    suspend fun getTopArtists(
        @Query("country") country: String,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): Response<TopArtistsResponse>

    @GET("chart.tracks.get")
    suspend fun getTopTracks(
        @Query("country") country: String,
        @Query("page") page: Int?,
        @Query("page_size") pageSize: Int
    ): Response<TopTracksResponse>

    @GET("track.lyrics.get")
    fun getTrackLyrics(
        @Query("track_id") trackId: String
    ): Observable<TrackLyricsResponse>

}