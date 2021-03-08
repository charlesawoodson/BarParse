package com.charlesawoodson.barparse.contents.responses

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class GetTracksResponse(
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
@Entity(tableName = "tracks")
data class Track(
    @PrimaryKey
    @Json(name = "track_id") val id: String,
    @Json(name = "track_name") val name: String,
    @Json(name = "track_rating") val rating: String,
    @Json(name = "commontrack_id") val commonId: String,
    @Json(name = "instrumental") val instrumental: String,
    @Json(name = "explicit") val explicit: Int,
    @Json(name = "has_lyrics") val hasLyrics: Int,
    @Json(name = "has_subtitles") val hasSubtitles: Int,
    @Json(name = "has_richsync") val hasRichSync: Int,
    @Json(name = "num_favourite") val numFavorite: Int,
    @Json(name = "album_id") val albumId: String,
    @Json(name = "album_name") val albumName: String,
    @Json(name = "artist_id") val artistId: String,
    @Json(name = "artist_name") val artistName: String,
    @Json(name = "track_share_url") val shareUrl: String,
    @Json(name = "track_edit_url") val editUrl: String,
    @Json(name = "restricted") val restricted: Int,
    @Json(name = "updated_time") val updatedTime: String,
    @Json(name = "primary_genres") val musicGenres: MusicGenres
) : Parcelable

