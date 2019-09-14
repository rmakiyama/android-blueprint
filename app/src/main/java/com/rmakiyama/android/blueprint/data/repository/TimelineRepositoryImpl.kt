package com.rmakiyama.android.blueprint.data.repository

import com.rmakiyama.android.blueprint.data.Result
import com.rmakiyama.android.blueprint.data.api.QiitaApiService
import com.rmakiyama.android.blueprint.data.api.response.convert
import com.rmakiyama.android.blueprint.domain.repository.TimelineRepository
import com.rmakiyama.android.blueprint.model.article.Article
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class TimelineRepositoryImpl @Inject constructor(
    private val api: QiitaApiService
) : TimelineRepository {

    override suspend fun getTimeline(page: Int): Result<List<Article>> {
        return handleData(page)
    }

    private suspend fun handleData(page: Int): Result<List<Article>> {
        val response = api.getRecentArticle(page)
        if (response.isSuccessful) {
            val body = response.body()?.map { body -> body.convert() }
            if (body != null) {
                return Result.Success(body)
            }
        }
        return Result.Error(
            IOException("Error: data ${response.code()} | ${response.message()}")
        )
    }
}
