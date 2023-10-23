package com.nadhifhayazee.data.retrotfit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("pokemon")
    suspend fun getPokemon(
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?
    ): Response<PokemonResponse>
}