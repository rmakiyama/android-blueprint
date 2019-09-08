package com.rmakiyama.android.blueprint.data.repository

import com.rmakiyama.android.blueprint.domain.repository.TimelineRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class TimelineRepositoryImpl @Inject constructor() : TimelineRepository {

    override fun getTimeline(): List<String> {
        return listOf("hofe", "piyo")
    }
}
