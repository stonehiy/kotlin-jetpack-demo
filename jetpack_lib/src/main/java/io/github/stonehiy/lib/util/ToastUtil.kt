package io.github.stonehiy.lib.util

import android.content.Context
import android.widget.Toast


class ToastUtil {
    companion object {

        @JvmStatic
        fun show(context: Context, text: String, duration: Int) {
            Toast.makeText(context, text, duration).show()
        }
    }

}