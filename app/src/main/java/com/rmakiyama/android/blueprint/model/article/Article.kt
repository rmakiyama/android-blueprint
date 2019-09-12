package com.rmakiyama.android.blueprint.model.article

typealias ArticleId = String
typealias ArticleTitle = String
typealias ArticleBody = String
typealias LikeCount = Int
typealias CreatedAt = String
typealias Url = String

data class Article(
    val id: ArticleId,
    val title: ArticleTitle,
    val body: ArticleBody,
    val likeCount: LikeCount,
    val createdAt: CreatedAt,
    val url: Url
)

