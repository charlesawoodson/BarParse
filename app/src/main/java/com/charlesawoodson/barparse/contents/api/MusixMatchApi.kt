package com.charlesawoodson.barparse.contents.api

import com.charlesawoodson.barparse.contents.responses.ArtistAlbumsResponse
import com.charlesawoodson.barparse.contents.responses.TopArtistsResponse
import com.charlesawoodson.barparse.contents.responses.GetTracksResponse
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
    ): Response<GetTracksResponse>

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
    ): Response<ArtistAlbumsResponse>

    @GET("album.tracks.get")
    fun getAlbumTracks(
        @Query("album_id") albumId: String,
        @Query("page") page: Int?,
        @Query("page_size") pageSize: Int
    ): Observable<GetTracksResponse> // todo: change to GetTracksResponse

    @GET("track.lyrics.get")
    fun getTrackLyrics(
        @Query("track_id") trackId: String
    ): Observable<TrackLyricsResponse>

    @GET("track.search")
    fun trackSearch(
        @Query("q_track") qTrack: String = "",
        @Query("q_artist") qArtist: String = "",
        @Query("q_lyrics") qLyrics: String = "",
        @Query("q_track_artist") qTrackArtist: String = "",
        @Query("q_writer") qWriter: String = "",
        @Query("q") query: String = "",
        @Query("f_artist_id") fArtistId: String = "",
        @Query("f_music_genre_id") fMusicGenreId: String = "",
        @Query("f_lyrics_language") fLyricsLanguage: String = "",
        @Query("f_has_lyrics") fHasLyrics: Int = 0,
        @Query("f_track_release_group_first_release_date_min") fTrackReleaseGroupDateMin: String = "", // YYYMMDD
        @Query("f_track_release_group_first_release_date_max") fTrackReleaseGroupDateMax: String = "", // YYYMMDD
        @Query("s_artist_rating") sArtistRating: String = "", // acs|desc
        @Query("s_track_rating") sTrackRating: String = "", // acs|desc
        @Query("quorum_factor") quorumFactor: Float? = null, // 0.1F-0.9F
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 15
    )

}