package com.rmakiyama.android.blueprint.ui.timeline

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmakiyama.android.blueprint.data.Result
import com.rmakiyama.android.blueprint.domain.usecase.GetTimelineUseCase
import com.rmakiyama.android.blueprint.model.article.Article
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

internal class TimelineViewModel @Inject constructor(
    private val getTimeline: GetTimelineUseCase
) : ViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    fun getArticle() {
        viewModelScope.launch {
            when (val result = getTimeline()) {
                is Result.Success -> _articles.value = result.data
                is Result.Error -> Timber.e(result.exception)
            }

        }
    }
}
