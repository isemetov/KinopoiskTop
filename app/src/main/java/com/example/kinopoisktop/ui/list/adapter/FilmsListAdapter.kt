package com.example.kinopoisktop.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kinopoisktop.data.model.Film
import com.example.kinopoisktop.databinding.FilmListItemBinding

class FilmsListAdapter(
    private val films: List<Film>, private val filmItemClickListener: FilmItemClickListener
    ) : RecyclerView.Adapter<FilmViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FilmListItemBinding.inflate(layoutInflater, parent, false)
        return FilmViewHolder(binding, filmItemClickListener)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val film = films[position]
        holder.bind(film, position)
    }

    override fun getItemCount(): Int = films.size

    fun interface FilmItemClickListener {
        fun onClick(id: Int)
    }
}