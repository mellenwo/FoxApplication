package com.jobrapp.androidinterview.dagger

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class FoxApplication : DaggerApplication(){

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

}