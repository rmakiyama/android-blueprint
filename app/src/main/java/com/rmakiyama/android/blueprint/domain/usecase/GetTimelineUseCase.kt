package com.rmakiyama.android.blueprint.domain.usecase

import com.rmakiyama.android.blueprint.data.Result
import com.rmakiyama.android.blueprint.domain.repository.TimelineRepository
import com.rmakiyama.android.blueprint.model.article.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class GetTimelineUseCase @Inject constructor(
    private val timelineRepository: TimelineRepository
) {

    suspend operator fun invoke(): Result<List<Article>> {
        return withContext(Dispatchers.IO) { timelineRepository.getTimeline() }
    }
}
