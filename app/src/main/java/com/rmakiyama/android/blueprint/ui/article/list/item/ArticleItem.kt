package com.rmakiyama.android.blueprint.ui.article.list.item

import com.rmakiyama.android.blueprint.R
import com.rmakiyama.android.blueprint.databinding.ItemArticleBinding
import com.rmakiyama.android.blueprint.model.article.Article
import com.xwray.groupie.databinding.BindableItem

internal data class ArticleItem(
    val article: Article
) : BindableItem<ItemArticleBinding>(article.id.hashCode().toLong()) {

    override fun getLayout(): Int = R.layout.item_article

    override fun bind(viewBinding: ItemArticleBinding, position: Int) {
        viewBinding.article = article
    }
}