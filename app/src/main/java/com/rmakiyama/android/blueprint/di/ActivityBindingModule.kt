package com.rmakiyama.android.blueprint.di

import com.rmakiyama.android.blueprint.ui.MainActivity
import com.rmakiyama.android.blueprint.ui.timeline.TimeLineModule
import com.rmakiyama.android.shared.di.ActivityScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("UNUSED")
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            TimeLineModule::class
        ]
    )
    internal abstract fun mainActivity(): MainActivity
}
