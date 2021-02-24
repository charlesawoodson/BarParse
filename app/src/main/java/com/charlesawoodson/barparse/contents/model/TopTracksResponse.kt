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
    @Json(name = "track_id") val trackId: String = "",
    @Json(name = "track_name") val trackName: String = ""
) : Parcelable