package io.github.stonehiy.lib.util

import android.content.Context
import android.widget.Toast
import io.github.stonehiy.lib.core.appContext
import timber.log.Timber

/**
 * 请使用ToastExt.kt
 */
@Deprecated("")
class ToastUtil private constructor() {
    companion object {

        @JvmStatic
        fun show(context: Context, text: String?, duration: Int) {
            if (text.isNullOrEmpty()) {
                return
            }
            Toast.makeText(context, text, duration).show()
        }

        @JvmStatic
        fun show(text: String?) {
            if (null == appContext) {
                Timber.w("ToastUtil appContext is null")
                return
            }
            show(appContext, text, Toast.LENGTH_SHORT)
        }

        @JvmStatic
        fun show(context: Context, text: String?) {
            show(context, text, Toast.LENGTH_SHORT)
        }
    }

}