package com.example.kinopoisktop.ui.list

import androidx.lifecycle.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.kinopoisktop.data.api.RetrofitBuilder
import com.example.kinopoisktop.data.model.TopFilms
import com.example.kinopoisktop.data.repository.FilmsRepository
import com.example.kinopoisktop.data.state.Result
import kotlinx.coroutines.*
import java.lang.Exception
import java.lang.reflect.TypeVariable

class FilmsViewModel: ViewModel() {

    private val filmRepository = FilmsRepository(RetrofitBuilder.apiService)
    fun getFilm(id: Int) = liveData(Dispatchers.IO) {
        emit(filmRepository.getFilm(id))
    }


    fun getTopFilms(page: Int) = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        try {
            val resultSuccess = Result.Success(filmRepository.getTopFilms(page))
            emit(resultSuccess)
        } catch (error: Exception) {
            val resultError = Result.Error(error)
            emit(resultError)
        }

    }



}