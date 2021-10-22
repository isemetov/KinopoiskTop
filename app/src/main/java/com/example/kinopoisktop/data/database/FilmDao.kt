package com.example.kinopoisktop.data.database

import androidx.room.*

@Dao
interface FilmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(film: FilmDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFilms(films: List<FilmDB>)

    @Update
    suspend fun updateFilm(film: FilmDB)

    @Delete
    suspend fun deleteFilm(film: FilmDB)

    @Query("SELECT * FROM films")
    suspend fun getAllFilms(): List<FilmDB>

    @Query("SELECT * FROM films WHERE (filmId=1)")
    suspend fun getFilm(): FilmDB
}