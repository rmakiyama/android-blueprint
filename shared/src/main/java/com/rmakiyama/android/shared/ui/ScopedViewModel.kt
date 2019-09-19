package com.rmakiyama.android.shared.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class ScopedViewModel : ViewModel() {

    fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(context, start) {
        try {
            block()
        } catch (e: IOException) {
            Timber.e(e, "IOException error")
        }
    }

    fun launchWithLoading(
        loading: MutableLiveData<Boolean>,
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(context, start) {
        try {
            loading.value = true
            block()
        } catch (e: IOException) {
            Timber.e(e, "IOException error")
        } finally {
            loading.value = false
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
