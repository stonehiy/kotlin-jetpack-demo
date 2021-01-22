package io.github.stonehiy.lib.core

import android.app.Application
import android.arch.lifecycle.ProcessLifecycleOwner
import android.content.ContentProvider
import android.content.ContentValues
import android.content.IntentFilter
import android.database.Cursor
import android.net.ConnectivityManager
import android.net.Uri
import io.github.stonehiy.lib.BuildConfig
import io.github.stonehiy.lib.CrashReportingTree
import io.github.stonehiy.lib.ext.lifecycle.KtxAppLifeObserver
import io.github.stonehiy.lib.net.manager.NetworkStateReceive
import io.github.stonehiy.lib.ext.lifecycle.KtxLifeCycleCallBack
import timber.log.Timber


/**
 * 代替在BaseApplication初始化 如 sdk 等等
 */

val appContext: Application by lazy { Ktx.app }

class Ktx : ContentProvider() {

    companion object {
        lateinit var app: Application
        private var mNetworkStateReceive: NetworkStateReceive? = null
        var watchActivityLife = true
        var watchAppLife = true
    }

    override fun onCreate(): Boolean {
        val application = context!!.applicationContext as Application
        install(application)
        return true
    }

    private fun install(application: Application) {
        app = application
        initTimber()
        //Timber.d("Ktx install")
        mNetworkStateReceive = NetworkStateReceive()
        app.registerReceiver(
                mNetworkStateReceive,
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )

        if (watchActivityLife) application.registerActivityLifecycleCallbacks(KtxLifeCycleCallBack())
        if (watchAppLife) ProcessLifecycleOwner.get().lifecycle.addObserver(KtxAppLifeObserver)
    }


    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }


    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun query(
            uri: Uri,
            projection: Array<String>?,
            selection: String?,
            selectionArgs: Array<String>?,
            sortOrder: String?
    ): Cursor? = null


    override fun update(
            uri: Uri,
            values: ContentValues?,
            selection: String?,
            selectionArgs: Array<String>?
    ): Int = 0

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int = 0

    override fun getType(uri: Uri): String? = null
}