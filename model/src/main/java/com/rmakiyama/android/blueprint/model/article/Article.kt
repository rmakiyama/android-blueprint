package com.rmakiyama.android.blueprint.model.article

import java.text.SimpleDateFormat
import java.util.*

typealias ArticleId = String
typealias ArticleTitle = String
typealias ArticleBody = String
typealias LikeCount = Int
typealias CreatedAt = Date
typealias Url = String

data class Article(
    val id: ArticleId,
    val title: ArticleTitle,
    val body: ArticleBody,
    val likeCount: LikeCount,
    val createdAt: CreatedAt,
    val url: Url
) {

    val createdAtText: String
        get() {
            val format = SimpleDateFormat("yyyy/MM/dd k:mm", Locale.JAPAN)
            return format.format(createdAt)
        }
}