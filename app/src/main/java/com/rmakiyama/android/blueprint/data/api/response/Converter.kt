package com.rmakiyama.android.blueprint.data.api.response

import com.rmakiyama.android.blueprint.model.article.Article

fun ArticleResponse.convert(): Article {
    return Article(
        id = id,
        title = title,
        body = body,
        likeCount = likeCount,
        createdAt = createdAt,
        url = url
    )
}