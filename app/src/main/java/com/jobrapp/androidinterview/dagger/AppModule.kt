package com.jobrapp.androidinterview.dagger

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule{

    @Provides
    fun providesContext(application: FoxApplication): Context {
        return application.applicationContext
    }
}