package com.rmakiyama.android.blueprint.ui.timeline

import androidx.lifecycle.ViewModel
import timber.log.Timber
import javax.inject.Inject

class TimelineViewModel @Inject constructor() : ViewModel() {

    init {
        Timber.d("debug: yes")
    }
}
