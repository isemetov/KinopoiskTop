package com.example.kinopoisktop.ui.list.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kinopoisktop.data.model.Film
import com.example.kinopoisktop.databinding.FilmListItemBinding

class FilmViewHolder(
    private val binding: FilmListItemBinding,
    private val filmItemClickListener: FilmsListAdapter.FilmItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

    fun bind(film: Film, position: Int) {
        binding.nameRu.text = (position + 1).toString() + ". " + film.nameRu
        binding.year.text = film.year
        binding.posterPreview.load(film.posterUrlPreview)

        //выгрузка жанров из списка Genres
        var genres: String = ""
        for (i in 0..film.genres.size-1) {
            if (i != film.genres.size-1)
                genres += film.genres[i].genre + ", "
            else genres += film.genres[i].genre
        }
        binding.genre.text = genres


            binding.root.setOnClickListener {
                filmItemClickListener.onClick(film.filmId.toInt())
            }
        }
    }
