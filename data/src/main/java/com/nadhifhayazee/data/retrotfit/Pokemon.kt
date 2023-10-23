package com.nadhifhayazee.data.retrotfit

data class PokemonResponse(
    val results: List<Pokemon>?,
    val count: Int?
)

data class Pokemon(
    val name: String?,
    val url: String?,
)
