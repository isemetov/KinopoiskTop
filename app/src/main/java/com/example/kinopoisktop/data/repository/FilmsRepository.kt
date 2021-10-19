package com.example.kinopoisktop.data.repository

import com.example.kinopoisktop.TOKEN
import com.example.kinopoisktop.data.api.ApiService

class FilmsRepository(private val apiService: ApiService) {

    suspend fun getFilm(id: Int) = apiService.getFilm(id, TOKEN)
    suspend fun getTopFilms(page: Int) = apiService.getTopFilms(page, TOKEN)

}