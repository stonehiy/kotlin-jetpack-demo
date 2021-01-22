package io.github.stonehiy.lib

import android.app.Application
import io.github.prototypez.appjoint.core.ModuleSpec
import timber.log.Timber

@ModuleSpec
class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.i("MainApp onCreate")
    }

}