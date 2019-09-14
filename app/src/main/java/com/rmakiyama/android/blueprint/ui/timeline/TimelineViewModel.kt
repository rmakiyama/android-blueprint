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

    private var page = 1

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _loading

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    init {
        getArticle()
    }

    fun getArticle() {
        viewModelScope.launch {
            try {
                _loading.value = true
                when (val result = getTimeline(page)) {
                    is Result.Success -> updateArticleList(articles.orEmpty(), result.data)
                    is Result.Error -> Timber.e(result.exception)
                }
            } catch (e: Exception) {
                Timber.e(e)
            } finally {
                _loading.value = false
            }
        }
    }

    private fun updateArticleList(
        oldItems: List<Article>,
        newItems: List<Article>
    ) {
        val items = oldItems.plus(newItems)
        _articles.value = items
        page++
    }

    private fun <T> LiveData<List<T>>.orEmpty(): List<T> {
        return value.orEmpty()
    }
}
