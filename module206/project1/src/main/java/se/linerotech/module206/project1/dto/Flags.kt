package se.linerotech.module206.project1.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Flags(
    @Json(name = "alt")
    val alt: String = "",
    @Json(name = "png")
    val png: String = "",
    @Json(name = "svg")
    val svg: String = ""
)