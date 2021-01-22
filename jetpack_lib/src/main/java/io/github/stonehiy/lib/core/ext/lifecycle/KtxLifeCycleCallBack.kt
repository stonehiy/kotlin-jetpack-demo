package io.github.stonehiy.lib.core.ext.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import io.github.stonehiy.lib.core.ext.lifecycle.KtxActivityManger


/**
 * 作者　: hegaojian
 * 时间　: 20120/1/7
 * 描述　:
 */
class KtxLifeCycleCallBack : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        KtxActivityManger.pushActivity(activity)
       
    }
    override fun onActivityStarted(activity: Activity) {
       
    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivityPaused(activity: Activity) {
    }


    override fun onActivityDestroyed(activity: Activity) {
        KtxActivityManger.popActivity(activity)
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityStopped(activity: Activity) {
    }


}