package com.charlesawoodson.barparse.contents.responses

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class TrackLyricsResponse(
    @Json(name = "message") val message: TrackLyricsMessage
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class TrackLyricsMessage(
    @Json(name = "header") val header: MessageHeader,
    @Json(name = "body") val body: TrackLyricsBody
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class TrackLyricsBody(
    @Json(name = "lyrics") val lyrics: TrackLyrics
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class TrackLyrics(
    @Json(name = "lyrics_id") val lyrics: String = "",
    @Json(name = "restricted") val restricted: Int = 0,
    @Json(name = "instrumental") val instrumental: Int = 0,
    @Json(name = "lyrics_body") val lyricsBody: String = "",
    @Json(name = "lyrics_language") val lyricsLanguage: String = "",
    @Json(name = "script_tracking_url") val scriptTrackingUrl: String = "",
    @Json(name = "pixel_tracking_url") val pixelTrackingUrl: String = "",
    @Json(name = "lyrics_copyright") val lyricsCopyright: String = "",
    @Json(name = "backlink_url") val backLinkUrl: String = "",
    @Json(name = "updated_time") val updatedTime: String = ""
) : Parcelable

