package se.linerotech.module206.project1.common

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryData(
    val country: String,
    val language: String,
    val flagUrl: String,
    val lat: Double,
    val long: Double,
    val population: Int,
    val area: Double
) : Parcelable
