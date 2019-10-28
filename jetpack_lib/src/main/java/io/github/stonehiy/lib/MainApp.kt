package io.github.stonehiy.lib

import android.app.Application
import io.github.prototypez.appjoint.core.ModuleSpec
import timber.log.Timber

@ModuleSpec
class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }

        Timber.i("MainApp onCreate")
    }

}