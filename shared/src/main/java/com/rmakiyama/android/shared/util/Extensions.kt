package com.rmakiyama.android.shared.util

import androidx.lifecycle.LiveData

fun <T> LiveData<List<T>>.orEmpty(): List<T> {
    return value.orEmpty()
}

fun LiveData<Boolean>.orFalse(): Boolean {
    return value ?: false
}