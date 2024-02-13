package se.linerotech.module206.project1.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Name(
    @Json(name = "common")
    val common: String = "",
    @Json(name = "nativeName")
    val nativeName: Any?,
    @Json(name = "official")
    val official: String = ""
)