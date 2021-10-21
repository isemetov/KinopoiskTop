package com.example.kinopoisktop.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.kinopoisktop.data.api.RetrofitBuilder
import com.example.kinopoisktop.data.repository.FilmsRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import com.example.kinopoisktop.data.state.Result

class FilmDetailsViewModel : ViewModel() {

    private val filmsRepository = FilmsRepository(RetrofitBuilder.apiService)

    fun getFilm(id: Int) = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        try {
            emit(Result.Success(filmsRepository.getFilm(id)))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }

    }
}