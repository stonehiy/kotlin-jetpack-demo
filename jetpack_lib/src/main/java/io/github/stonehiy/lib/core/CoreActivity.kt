package io.github.stonehiy.lib.core

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.github.stonehiy.lib.util.ToastUtil

open class CoreActivity : AppCompatActivity(), IView {

    override fun showLoading() {
        ToastUtil.show(this, "showLoading", Toast.LENGTH_SHORT)

    }

    override fun hideLoading() {
//        ToastUtil.show(this, "hideLoading", Toast.LENGTH_SHORT)
    }

    override fun showError(msg: String) {
    }

    override fun reLoginActivity() {

    }

}