package com.nadhifhayazee.testing.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.nadhifhayazee.data.Resource
import com.nadhifhayazee.testing.commons.gone
import com.nadhifhayazee.testing.commons.visible
import com.nadhifhayazee.testing.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()
    private val pokemonAdapter = PokemonAdapter()

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        viewModel.getPokemon()

        observePokemon()


    }

    private fun setupView() {
        binding.apply {
            rvPokemon.adapter = pokemonAdapter
            rvPokemon.layoutManager = GridLayoutManager(this@MainActivity, 2)
        }
    }

    private fun observePokemon() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.pokemon.collectLatest {
                    when (it) {
                        is Resource.Loading -> {
                            binding.progressCircular.visible()
                        }

                        is Resource.Success -> {
                            binding.progressCircular.gone()
                            pokemonAdapter.submitList(it.data)
                        }

                        is Resource.Error -> {
                            binding.progressCircular.gone()
                            Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            }
        }
    }

}