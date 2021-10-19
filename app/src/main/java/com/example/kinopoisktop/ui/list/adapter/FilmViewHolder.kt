package com.example.kinopoisktop.ui.list.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kinopoisktop.data.model.Film
import com.example.kinopoisktop.databinding.FilmListItemBinding

class FilmViewHolder(private val binding: FilmListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(film: Film) {
        binding.nameRu.text =film.nameRu
        binding.year.text = film.year
        binding.posterPreview.load(film.posterUrlPreview)
    }
}