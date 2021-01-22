package io.github.stonehiy.lib

import android.app.Application
import timber.log.Timber

@Deprecated("")
//@ModuleSpec
class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.i("MainApp onCreate")
    }

}