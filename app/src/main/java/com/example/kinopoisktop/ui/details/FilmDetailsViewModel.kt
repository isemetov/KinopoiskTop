package com.example.kinopoisktop.ui.details

import androidx.lifecycle.*
import com.example.kinopoisktop.data.api.RetrofitBuilder
import com.example.kinopoisktop.data.database.AppDatabase
import com.example.kinopoisktop.data.database.FilmDB
import com.example.kinopoisktop.data.model.Film
import com.example.kinopoisktop.data.repository.FilmsRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import com.example.kinopoisktop.data.state.Result
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FilmDetailsViewModel : ViewModel() {

    private val filmsRepository = FilmsRepository(RetrofitBuilder.apiService)

    private val appDatabase = AppDatabase.getDatabase()

    private val _localFilms = MutableLiveData<FilmDB>()
    val localFilms: LiveData<FilmDB>
        get() = _localFilms


    fun getFilmFromDatabase() =
        viewModelScope.launch {
            try {
                _localFilms.postValue(appDatabase.filmDao().getFilm())
            } catch (exception: Exception) {
                throw exception
            }
        }

    fun setFilmToDatabase(film: FilmDB) =
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                appDatabase.filmDao().insertFilm(film)
                getFilmFromDatabase()
            }
        }

    fun getFilm(id: Int) = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        try {
            emit(Result.Success(filmsRepository.getFilm(id)))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }

    }
}