package com.example.kinopoisktop.ui.list

import androidx.lifecycle.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.kinopoisktop.data.api.RetrofitBuilder
import com.example.kinopoisktop.data.database.AppDatabase
import com.example.kinopoisktop.data.database.FilmDB
import com.example.kinopoisktop.data.model.Film
import com.example.kinopoisktop.data.repository.FilmsRepository
import com.example.kinopoisktop.data.state.Result
import kotlinx.coroutines.*
import java.lang.Exception

class FilmsViewModel: ViewModel() {

    private val filmRepository = FilmsRepository(RetrofitBuilder.apiService)
    private val appDatabase = AppDatabase.getDatabase()

    private val _localFilms = MutableLiveData<List<FilmDB>>()
    val localFilms: LiveData<List<FilmDB>>
        get() = _localFilms


    fun getFilmsFromDatabase() =
        viewModelScope.launch {
            try {
                _localFilms.postValue(appDatabase.filmDao().getAllFilms())
            } catch (exception: Exception) {
                throw exception
            }
        }

    fun setAllFilmsToDatabase(films: List<FilmDB>) =
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                appDatabase.filmDao().insertAllFilms(films)
                getFilmsFromDatabase()
            }
        }

    fun getTopFilms(page: Int) = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        try {
            val topFilms = filmRepository.getTopFilms(page)
            val resultSuccess = Result.Success(topFilms.films)
            emit(resultSuccess)
        } catch (error: Exception) {
            val resultError = Result.Error(error)
            emit(resultError)
        }

    }

    fun getTopFilms() = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        try {
            //загрузка всех страниц фильмов в один список
            var films: List<Film>
            var topFilms = filmRepository.getTopFilms(1)
            films = topFilms.films
            val cntPages = topFilms.pagesCount
            for (i in 2..cntPages)
                films += filmRepository.getTopFilms(i).films
            emit(Result.Success(data = films))
        } catch (error: Exception) {
            val resultError = Result.Error(error)
            emit(resultError)

        }


    }
}