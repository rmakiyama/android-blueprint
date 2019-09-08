package com.rmakiyama.android.blueprint.ui.timeline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmakiyama.android.blueprint.domain.usecase.GetTimelineUseCase
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

internal class TimelineViewModel @Inject constructor(
    private val getTimeline: GetTimelineUseCase
) : ViewModel() {

    // TODO: test
    fun init() {
        viewModelScope.launch {
            getTimeline().forEach { Timber.d("debug: $it") }
        }
    }
}
