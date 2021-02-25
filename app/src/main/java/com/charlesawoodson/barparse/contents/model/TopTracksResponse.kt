package com.charlesawoodson.barparse.contents.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class TopTracksResponse(
    @Json(name = "message") val message: TrackListMessage
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class TrackListMessage(
    @Json(name = "header") val header: MessageHeader,
    @Json(name = "body") val body: TrackListBody
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class TrackListBody(
    @Json(name = "track_list") val trackList: List<TrackList> = emptyList()
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class TrackList(
    @Json(name = "track") val track: Track
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Track(
    @Json(name = "track_id") val id: String = "",
    @Json(name = "track_name") val name: String = "",
    @Json(name = "track_rating") val rating: String = "",
    @Json(name = "commontrack_id") val commonId: String = "",
    @Json(name = "instrumental") val instrumental: String = "",
    @Json(name = "explicit") val explicit: Int = 0,
    @Json(name = "has_lyrics") val hasLyrics: String = "",
    @Json(name = "has_subtitles") val hasSubtitles: String = "",
    @Json(name = "has_richsync") val hasRichSync: String = "",
    @Json(name = "num_favourite") val numFavorite: String = "",
    @Json(name = "album_id") val albumId: String = "",
    @Json(name = "album_name") val albumName: String = "",
    @Json(name = "artist_id") val artistId: String = "",
    @Json(name = "artist_name") val artistName: String = "",
    @Json(name = "track_share_url") val shareUrl: String = "",
    @Json(name = "track_edit_url") val editUrl: String = "",
    @Json(name = "restricted") val restricted: String = "",
    @Json(name = "updated_time") val updatedTime: String = "",
    @Json(name = "primary_genres") val primaryGenres: PrimaryGenres
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class PrimaryGenres(
    @Json(name = "music_genre_list") val musicGenreList: List<MusicGenreList> = emptyList()
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class MusicGenreList(
    @Json(name = "music_genre") val musicGenre: MusicGenre
) : Parcelable


@Parcelize
@JsonClass(generateAdapter = true)
data class MusicGenre(
    @Json(name = "music_genre_id") val id: String = "",
    @Json(name = "music_genre_parent_id") val parentId: String = "",
    @Json(name = "music_genre_name") val name: String = "",
    @Json(name = "music_genre_name_extended") val extendedName: String = "",
    @Json(name = "music_genre_vanity") val vanity: String = ""
) : Parcelable

