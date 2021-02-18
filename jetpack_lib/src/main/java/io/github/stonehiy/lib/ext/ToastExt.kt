package io.github.stonehiy.lib.ext

import android.content.Context
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast


/**
 * desc: toast 相关
 */
var toast: Toast? = null
fun Context.toast(text: CharSequence?, duration: Int = Toast.LENGTH_SHORT): Toast? {
    if (null == toast) {
        toast = Toast.makeText(this, text, duration).apply {
            view.findViewById<TextView>(android.R.id.message).apply {
                gravity = Gravity.CENTER
            }
        }
    } else {
        toast?.setText(text)
        toast?.duration = duration
    }
    if (!text.isNullOrEmpty()) {
        toast?.show()
    }
    return toast

}

fun Context.toast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT): Toast? {
    if (null == toast) {
        toast = Toast.makeText(this, resId, duration).apply {
            view.findViewById<TextView>(android.R.id.message).apply {
                gravity = Gravity.CENTER
            }
        }
    } else {
        toast?.setText(resId)
        toast?.duration = duration
    }
    toast?.show()
    return toast
}

fun Fragment.toast(textResource: Int) = requireActivity().toast(textResource)

fun Fragment.toast(text: CharSequence) = requireActivity().toast(text)