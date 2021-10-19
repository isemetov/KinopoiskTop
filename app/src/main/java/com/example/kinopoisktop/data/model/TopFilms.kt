package com.example.kinopoisktop.data.model

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET

data class TopFilms(
    @SerializedName("pagesCount")
    val pagesCount: Int,
    @SerializedName("films")
    val films: List<Film>
)
