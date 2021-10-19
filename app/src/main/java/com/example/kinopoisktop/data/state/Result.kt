package com.example.kinopoisktop.data.state

sealed class Result<out T> {
    object Loading : Result<Nothing>()
    class Success<T>(val data: T) : Result<T>()
    class Error(val error: Throwable) : Result<Nothing>()
}