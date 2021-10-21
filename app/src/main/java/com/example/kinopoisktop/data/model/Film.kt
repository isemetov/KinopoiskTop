package com.example.kinopoisktop.data.model

import com.google.gson.annotations.SerializedName

data class Film(
    @SerializedName("filmId")
    val filmId: String,
    @SerializedName("nameRu")
    val nameRu: String,
    @SerializedName("nameEn")
    val nameEn: String,
    @SerializedName("year")
    val year: String,
    @SerializedName("filmLength")
    val filmLength: String,
//    @SerializedName("countries")
//    val countries: List<Countries>,
//    @SerializedName("genres")
//    val genres: List<Genres>,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("ratingVoteCount")
    val ratingVoteCount: String,
    @SerializedName("posterUrl")
    val posterUrl: String,
    @SerializedName("posterUrlPreview")
    val posterUrlPreview: String,
    @SerializedName("slogan")
    val slogan: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("ratingKinopoisk")
    val ratingKinopoisk: String,




)
