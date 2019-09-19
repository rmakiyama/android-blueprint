package com.rmakiyama.android.blueprint.ui.article.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rmakiyama.android.blueprint.data.Result
import com.rmakiyama.android.blueprint.domain.usecase.GetTimelineUseCase
import com.rmakiyama.android.blueprint.model.article.Article
import com.rmakiyama.android.shared.ui.ScopedViewModel
import com.rmakiyama.android.shared.util.orEmpty
import javax.inject.Inject

internal class ArticleListViewModel @Inject constructor(
    private val getTimeline: GetTimelineUseCase
) : ScopedViewModel() {

    private var page = 1

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _loading

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    lateinit var query: String

    fun getArticles() {
        launchWithLoading(_loading) {
            when (val result = getTimeline(page, query)) {
                is Result.Success -> updateArticleList(articles.orEmpty(), result.data)
                is Result.Error -> throw result.exception
            }
        }
    }

    private fun updateArticleList(
        oldItems: List<Article>,
        newItems: List<Article>
    ) {
        val items = oldItems.plus(newItems).distinctBy { it.id }
        _articles.value = items
        page++
    }
}
