package com.rmakiyama.android.blueprint.data.repository

import com.rmakiyama.android.blueprint.data.Result
import com.rmakiyama.android.blueprint.data.api.QiitaApiService
import com.rmakiyama.android.blueprint.data.api.response.convert
import com.rmakiyama.android.blueprint.data.convert
import com.rmakiyama.android.blueprint.data.util.handleData
import com.rmakiyama.android.blueprint.domain.repository.TimelineRepository
import com.rmakiyama.android.blueprint.model.article.Article
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class TimelineRepositoryImpl @Inject constructor(
    private val api: QiitaApiService
) : TimelineRepository {

    override suspend fun getTimeline(page: Int, query: String): Result<List<Article>> {
        val response = api.getRecentArticle(page, query)
        return response.handleData().convert { res ->
            res.map { article -> article.convert() }
        }
    }
}
