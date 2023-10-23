package com.nadhifhayazee.data.repository

import com.nadhifhayazee.data.Resource
import com.nadhifhayazee.data.retrotfit.ApiClient
import com.nadhifhayazee.data.retrotfit.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val apiClient: ApiClient
) {

    suspend fun getPokemon(
        offset: Int,
        limit: Int,
    ): Flow<Resource<List<Pokemon>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = apiClient.getPokemon(offset, limit)
                if (response.isSuccessful) {
                    val result = response.body()?.results
                    emit(Resource.Success(result))
                } else {
                    emit(Resource.Error(Exception(), "Failed get pokemon!"))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e, e.message))
            }
        }.flowOn(Dispatchers.IO)
    }
}