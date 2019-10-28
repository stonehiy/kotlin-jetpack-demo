package com.stonehiy.jetpackdemo

import android.app.Application
import io.github.prototypez.appjoint.core.AppSpec
import io.github.stonehiy.lib.BuildConfig
import io.github.stonehiy.lib.CrashReportingTree
import timber.log.Timber

@AppSpec
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
        Timber.i("App onCreate")
    }
}