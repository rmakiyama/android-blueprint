package com.rmakiyama.android.blueprint.ui.article.list

import com.rmakiyama.android.blueprint.model.article.Article
import com.rmakiyama.android.blueprint.ui.article.list.item.ArticleItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

internal class ArticleListAdapter : GroupAdapter<ViewHolder>() {

    fun update(articles: List<Article>) {
        update(articles.map { article -> ArticleItem(article = article) })
    }
}