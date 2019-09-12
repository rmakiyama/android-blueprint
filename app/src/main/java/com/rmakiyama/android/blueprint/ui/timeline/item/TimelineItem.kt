package com.rmakiyama.android.blueprint.ui.timeline.item

import com.rmakiyama.android.blueprint.R
import com.rmakiyama.android.blueprint.databinding.ItemTimelineBinding
import com.rmakiyama.android.blueprint.model.article.Article
import com.xwray.groupie.databinding.BindableItem

internal data class TimelineItem(
    private val article: Article
) : BindableItem<ItemTimelineBinding>(article.id.hashCode().toLong()) {

    override fun getLayout(): Int = R.layout.item_timeline

    override fun bind(viewBinding: ItemTimelineBinding, position: Int) {
        viewBinding.article = article
    }
}