package com.rmakiyama.android.blueprint.ui.article.detail

import androidx.lifecycle.ViewModel
import com.rmakiyama.android.shared.di.FragmentScoped
import com.rmakiyama.android.shared.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ArticleDetailModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeArticleDetailFragment(): ArticleDetailFragment

    @Binds
    @IntoMap
    @ViewModelKey(ArticleDetailViewModel::class)
    abstract fun bindArticleDetailViewModel(viewModel: ArticleDetailViewModel): ViewModel
}
