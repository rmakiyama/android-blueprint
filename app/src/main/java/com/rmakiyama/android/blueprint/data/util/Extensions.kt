package com.rmakiyama.android.blueprint.data.util

import com.rmakiyama.android.blueprint.data.Result
import retrofit2.Response

fun <T : Any> Response<T>.handleData(): Result<T> {
    if (isSuccessful) {
        val body = body()
        if (body != null) {
            return Result.Success(body)
        }
    }
    return Result.Error(
        java.io.IOException("Error: data ${code()} | ${message()}")
    )
}