package com.jobrapp.androidinterview.dagger

import com.jobrapp.androidinterview.MainActivity
import com.jobrapp.androidinterview.factories.MainViewModelFactory
import com.jobrapp.androidinterview.data.UserRepository
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class MainActivityModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        internal fun providesMainViewModelFactory(gitRepoRepository: UserRepository)
                : MainViewModelFactory {
            return MainViewModelFactory(gitRepoRepository)
        }
    }

    @ContributesAndroidInjector()
    internal abstract fun mainActivity(): MainActivity

}