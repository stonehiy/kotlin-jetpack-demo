package io.github.stonehiy.lib.core

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import io.github.stonehiy.lib.util.ToastUtil
import timber.log.Timber


/**
 * use [IView] child object
 */
@Deprecated(message = "Deprecated")
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
        ToastUtil.show(this, msg, Toast.LENGTH_SHORT)
    }

    override fun reLoginActivity() {
        Timber.d("reLoginActivity 401")

    }

}