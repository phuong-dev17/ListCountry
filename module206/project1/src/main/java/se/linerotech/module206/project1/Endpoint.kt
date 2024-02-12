package se.linerotech.module206.project1

import retrofit2.Call
import retrofit2.http.GET

interface Endpoint {

    @GET("all")
    fun all(): Call<CountryDTO>
}