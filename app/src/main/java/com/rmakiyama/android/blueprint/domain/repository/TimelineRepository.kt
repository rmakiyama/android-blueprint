package com.rmakiyama.android.blueprint.domain.repository

import com.rmakiyama.android.blueprint.data.Result
import com.rmakiyama.android.blueprint.model.article.Article

interface TimelineRepository {

    suspend fun getTimeline(page: Int): Result<List<Article>>
}
