package com.charlesawoodson.barparse.contents.responses

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

/**
 * Created by charles.adams on 05/25/2020
 */

@Parcelize
@JsonClass(generateAdapter = true)
data class ArtistAlbumsResponse(
    @Json(name = "message") val message: ArtistAlbumsMessage
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class ArtistAlbumsMessage(
    @Json(name = "header") val header: MessageHeader,
    @Json(name = "body") val body: ArtistAlbumsBody
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class ArtistAlbumsBody(
    @Json(name = "album_list") val albumList: List<AlbumList> = emptyList(),
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class AlbumList(
    @Json(name = "album") val album: Album,
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Album(
    @Json(name = "album_id") val albumId: String = "",
    @Json(name = "album_mbid") val albumMbId: String = "",
    @Json(name = "album_name") val albumName: String = "",
    @Json(name = "album_rating") val artistRating: Int = 0,
    @Json(name = "album_track_count") val albumTrackCount: Int = 0,
    @Json(name = "album_release_date") val albumReleaseDate: String = "",
    @Json(name = "album_release_type") val albumReleaseType: String = "", // todo: create Single/Album Enum
    @Json(name = "explicit") val explicit: Int = 0,
    @Json(name = "artist_id") val artistId: String = "",
    @Json(name = "artist_name") val artistName: String = "",
    @Json(name = "primary_genres") val primaryGenres: MusicGenres? = null,
    @Json(name = "secondary_genres") val secondaryGenres: MusicGenres? = null,
    @Json(name = "album_pline") val albumPLine: String = "",
    @Json(name = "album_copyright") val albumCopyright: String = "",
    @Json(name = "album_label") val albumLabel: String = "",
    @Json(name = "updated_time") val updatedTime: String = "",
    @Json(name = "album_coverart_100x100") val albumCoverArt: String = "",
) : Parcelable