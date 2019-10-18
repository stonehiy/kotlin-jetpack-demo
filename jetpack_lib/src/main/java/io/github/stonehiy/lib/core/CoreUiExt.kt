package io.github.stonehiy.lib.core

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment


 fun IView.relogin() {
    when (this) {
        is Activity -> {

            startActivity(Intent())
        }
        is Fragment -> {
            startActivity(Intent())
        }
    }

}