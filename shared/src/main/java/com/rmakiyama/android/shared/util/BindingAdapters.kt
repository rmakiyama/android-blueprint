package com.rmakiyama.android.shared.util

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("visibleUnless")
fun View.bindVisibleUnless(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}
