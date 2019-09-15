package com.rmakiyama.android.blueprint.ui.timeline

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

internal class TimelineViewModel @Inject constructor() : ViewModel() {

    private val _queries = MutableLiveData<List<String>>()
    val queries: LiveData<List<String>> = _queries

    init {
        _queries.value = listOf("android", "kotlin", "flutter", "golang", "github")
    }
}
