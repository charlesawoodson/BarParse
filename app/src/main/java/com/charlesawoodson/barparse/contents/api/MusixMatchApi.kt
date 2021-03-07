package com.charlesawoodson.barparse.contents.api

import com.charlesawoodson.barparse.contents.responses.ArtistAlbumsResponse
import com.charlesawoodson.barparse.contents.responses.TopArtistsResponse
import com.charlesawoodson.barparse.contents.responses.TopTracksResponse
import com.charlesawoodson.barparse.contents.responses.TrackLyricsResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by charles.adams on 05/24/2020
 */

interface MusixMatchApi {

    @GET("chart.tracks.get")
    suspend fun getTopTracks(
        @Query("country") country: String,
        @Query("page") page: Int?,
        @Query("page_size") pageSize: Int
    ): Response<TopTracksResponse>

    @GET("chart.artists.get")
    suspend fun getTopArtists(
        @Query("country") country: String,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): Response<TopArtistsResponse>

    @GET("artist.albums.get")
    suspend fun getArtistAlbums(
        @Query("artist_id") artistId: String,
        @Query("artist_mbid") artistMbId: String,
        @Query("g_album_name") groupAlbumName: String = "",
        @Query("s_release_date") sortByDate: String = "desc",
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
    ) : Response<ArtistAlbumsResponse>

    @GET("track.lyrics.get")
    fun getTrackLyrics(
        @Query("track_id") trackId: String
    ): Observable<TrackLyricsResponse>

}