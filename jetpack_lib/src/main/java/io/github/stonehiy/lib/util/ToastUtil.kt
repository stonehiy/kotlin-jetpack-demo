package io.github.stonehiy.lib.util

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.annotation.IntDef
import io.github.stonehiy.lib.MainApp

class ToastUtil {
    companion object {

        @JvmStatic
        fun show(context: Context, text: String, duration: Int) {
            Toast.makeText(context, text, duration).show()
        }
    }

}