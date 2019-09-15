package com.rmakiyama.android.blueprint.data

sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}

fun <T : Any, R : Any> Result<T>.convert(converter: (T) -> R): Result<R> {
    return when (this) {
        is Result.Success -> Result.Success(converter(data))
        is Result.Error -> Result.Error(exception)
        is Result.Loading -> Result.Loading
    }
}