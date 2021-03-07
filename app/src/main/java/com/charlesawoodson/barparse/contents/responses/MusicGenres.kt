package com.charlesawoodson.barparse.contents.responses

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class MusicGenres(
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