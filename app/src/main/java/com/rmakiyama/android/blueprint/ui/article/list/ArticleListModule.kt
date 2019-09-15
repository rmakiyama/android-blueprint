package com.rmakiyama.android.blueprint.ui.article.list

import androidx.lifecycle.ViewModel
import com.rmakiyama.android.blueprint.data.repository.TimelineRepositoryImpl
import com.rmakiyama.android.blueprint.domain.repository.TimelineRepository
import com.rmakiyama.android.shared.di.FragmentScoped
import com.rmakiyama.android.shared.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ArticleListModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeArticleListFragment(): ArticleListFragment

    @Binds
    @IntoMap
    @ViewModelKey(ArticleListViewModel::class)
    abstract fun bindArticleListViewModel(viewModel: ArticleListViewModel): ViewModel

    @Binds
    internal abstract fun provideTimelineRepository(impl: TimelineRepositoryImpl): TimelineRepository
}
