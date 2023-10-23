package com.nadhifhayazee.testing.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nadhifhayazee.data.retrotfit.Pokemon
import com.nadhifhayazee.testing.commons.loadImage
import com.nadhifhayazee.testing.databinding.PokemonItemLayoutBinding

class PokemonAdapter : ListAdapter<Pokemon, PokemonAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder (private val binding: PokemonItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Pokemon?) {
            binding.apply {
                ivPokemon.loadImage("https://img.pokemondb.net/artwork/large/${item?.name}.jpg")
            }
        }


    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PokemonItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}
