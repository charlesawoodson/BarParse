package com.charlesawoodson.barparse.contents.responses

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class MessageHeader(
    @Json(name = "status_code") val statusCode: Int = 0,
    @Json(name = "execution_time") val executionTime: Long = 0
) : Parcelable