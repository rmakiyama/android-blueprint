package com.rmakiyama.android.shared.ui

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PagingScrollListener(
    private val layoutManager: LinearLayoutManager,
    private val threshold: Int = VISIBLE_THRESHOLD
) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (dy < 0 || isLoading()) return

        val visibleItemCount = recyclerView.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

        if (totalItemCount - visibleItemCount <= firstVisibleItem + threshold) {

            onLoadMore()
        }
    }

    abstract fun onLoadMore()

    abstract fun isLoading(): Boolean

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }
}