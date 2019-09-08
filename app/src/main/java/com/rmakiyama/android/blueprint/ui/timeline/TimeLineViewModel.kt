package com.rmakiyama.android.blueprint.ui.timeline

import androidx.lifecycle.ViewModel
import timber.log.Timber
import javax.inject.Inject

class TimeLineViewModel @Inject constructor() : ViewModel() {

    init {
        Timber.d("debug: yes")
    }
}
