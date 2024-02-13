package se.linerotech.module206.project1.network

import retrofit2.http.GET
import se.linerotech.module206.project1.dto.CountryDTOItem

interface Endpoint {
    @GET("/v3.1/all")
    suspend fun all(): List<CountryDTOItem>
}
