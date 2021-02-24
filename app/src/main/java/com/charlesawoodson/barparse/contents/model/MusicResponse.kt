package com.charlesawoodson.barparse.contents.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * Created by charles.adams on 05/25/2020
 */

@Parcelize
@JsonClass(generateAdapter = true)
data class MusicResponse(
    @Json(name = "message") val message: MusicMessage
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class MusicHeader(
    @Json(name = "status_code") val statusCode: Int = 0,
    @Json(name = "execution_time") val executionTime: Long = 0
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class MusicMessage(
    @Json(name = "header") val header: MusicHeader,
    @Json(name = "body") val body: MusicBody
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class MusicBody(
    @Json(name = "artist_list") val artistList: List<MusicListData> = emptyList(),
    @Json(name = "track_list") val trackList: List<MusicListData> = emptyList()
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class MusicListData(
    @Json(name = "artist") val artist: Artist?,
    @Json(name = "track") val track: Track?
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Artist(
    @Json(name = "artist_id") val artistId: String = "",
    @Json(name = "artist_name") val artistName: String = ""
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Track(
    @Json(name = "track_id") val trackId: String = "",
    @Json(name = "track_name") val trackName: String = ""
) : Parcelable