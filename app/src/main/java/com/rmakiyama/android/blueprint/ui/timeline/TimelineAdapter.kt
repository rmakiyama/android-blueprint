package com.rmakiyama.android.blueprint.ui.timeline

import com.rmakiyama.android.blueprint.model.article.Article
import com.rmakiyama.android.blueprint.ui.timeline.item.TimelineItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

internal class TimelineAdapter : GroupAdapter<ViewHolder>() {

    fun update(articles: List<Article>) {
        update(articles.map { article -> TimelineItem(article = article) })
    }
}