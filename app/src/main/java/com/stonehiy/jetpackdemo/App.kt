package com.stonehiy.jetpackdemo

import android.app.Application
import io.github.prototypez.appjoint.core.AppSpec
import timber.log.Timber

@AppSpec
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.i("App onCreate")
    }
}