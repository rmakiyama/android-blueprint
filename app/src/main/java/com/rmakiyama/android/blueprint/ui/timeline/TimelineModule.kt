package com.rmakiyama.android.blueprint.ui.timeline

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
internal abstract class TimelineModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeTimeLineFragment(): TimelineFragment

    @Binds
    @IntoMap
    @ViewModelKey(TimelineViewModel::class)
    abstract fun bindScheduleViewModel(viewModel: TimelineViewModel): ViewModel

    @Binds
    internal abstract fun provideTimelineRepository(impl: TimelineRepositoryImpl): TimelineRepository
}
