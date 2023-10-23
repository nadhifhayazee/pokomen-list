package com.nadhifhayazee.testing.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadhifhayazee.data.Resource
import com.nadhifhayazee.data.repository.PokemonRepository
import com.nadhifhayazee.data.retrotfit.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel(){

    private val _pokemon = MutableStateFlow<Resource<List<Pokemon>>>(Resource.Loading())
    val pokemon get() = _pokemon.asStateFlow()

    fun getPokemon() {
        viewModelScope.launch {
            pokemonRepository.getPokemon(0,10).collectLatest {
                _pokemon.value = it
            }
        }
    }
}