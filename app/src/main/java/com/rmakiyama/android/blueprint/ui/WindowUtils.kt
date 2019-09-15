package com.rmakiyama.android.blueprint.ui

import android.view.View
import android.view.ViewGroup

fun setupFullScreen(root: ViewGroup) {
    root.systemUiVisibility =
        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
}