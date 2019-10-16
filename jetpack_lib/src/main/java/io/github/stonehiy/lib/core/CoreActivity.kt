package io.github.stonehiy.lib.core

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber
import java.security.AccessController.getContext

open class CoreActivity : AppCompatActivity(), IView {

    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressDialog = ProgressDialog(this)
    }

    override fun showLoading() {
        if (!progressDialog.isShowing) {
            progressDialog.show()
        }


    }

    override fun hideLoading() {
        if (progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }

    override fun showError(msg: String) {
    }

    override fun reLoginActivity() {
        Timber.d("reLoginActivity 401")

    }

}