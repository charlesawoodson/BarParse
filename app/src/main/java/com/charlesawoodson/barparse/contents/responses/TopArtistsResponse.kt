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
data class TopArtistsResponse(
    @Json(name = "message") val message: ArtistListMessage
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class ArtistListMessage(
    @Json(name = "header") val header: MessageHeader,
    @Json(name = "body") val body: ArtistListBody
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class ArtistListBody(
    @Json(name = "artist_list") val artistList: List<ArtistList> = emptyList(),
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class ArtistList(
    @Json(name = "artist") val artist: Artist,
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Artist(
    @Json(name = "artist_id") val artistId: String = "",
    @Json(name = "artist_name") val artistName: String = "",
    @Json(name = "artist_country") val artistCountry: String = "",
    @Json(name = "artist_rating") val artistRating: Int = 0,
    @Json(name = "artist_twitter_url") val artistTwitterUrl: String = "",
    @Json(name = "restricted") val restricted: Int = 0,
    @Json(name = "updated_time") val updatedTime: String = "",
    @Json(name = "begin_date") val beginDate: String = "",
    @Json(name = "end_date") val endDate: String = ""
) : Parcelable