package io.github.stonehiy.lib

import timber.log.Timber

class CrashReportingTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
    }
}