package com.rmakiyama.android.blueprint.data.api.response

import com.rmakiyama.android.blueprint.model.article.Article
import java.text.SimpleDateFormat
import java.util.*

fun ArticleResponse.convert(): Article {
    return Article(
        id = id,
        title = title,
        body = body,
        likeCount = likeCount,
        createdAt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.JAPAN).parse(createdAt),
        url = url
    )
}