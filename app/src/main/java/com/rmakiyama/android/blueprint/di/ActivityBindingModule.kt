package com.rmakiyama.android.blueprint.di

import com.rmakiyama.android.blueprint.ui.MainActivity
import com.rmakiyama.android.blueprint.ui.article.detail.ArticleDetailModule
import com.rmakiyama.android.blueprint.ui.article.list.ArticleListModule
import com.rmakiyama.android.blueprint.ui.timeline.TimelineModule
import com.rmakiyama.android.shared.di.ActivityScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("UNUSED")
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            TimelineModule::class,
            ArticleListModule::class,
            ArticleDetailModule::class
        ]
    )
    internal abstract fun mainActivity(): MainActivity
}
