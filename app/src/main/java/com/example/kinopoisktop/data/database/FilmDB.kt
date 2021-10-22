package com.example.kinopoisktop.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kinopoisktop.data.model.Countries
import com.example.kinopoisktop.data.model.Genres
import com.google.gson.annotations.SerializedName

@Entity(tableName = "films")
data class FilmDB(
    @PrimaryKey
    val filmId: Int,
    @ColumnInfo(name = "nameRu")
    val nameRu: String,
//    @ColumnInfo(name = "nameEn")
//    val nameEn: String,
    @ColumnInfo(name = "year")
    val year: String,
//    @ColumnInfo(name = "filmLength")
//    val filmLength: String,
 //   @ColumnInfo(name = "countries")
//    val countries: List<Countries>,
//    @ColumnInfo(name = "genres")
//    val genres: List<Genres>,
//    @ColumnInfo(name = "rating")
//    val rating: String,
//    @ColumnInfo(name = "ratingVoteCount")
//    val ratingVoteCount: String,
//    @ColumnInfo(name = "posterUrl")
//    val posterUrl: String,
//    @ColumnInfo(name = "posterUrlPreview")
    val posterUrlPreview: String,
    @ColumnInfo(name = "slogan")
    val slogan: String?,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "ratingKinopoisk")
    val ratingKinopoisk: String?,
)
