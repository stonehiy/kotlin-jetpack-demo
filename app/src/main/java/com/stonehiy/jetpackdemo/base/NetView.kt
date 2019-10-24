package com.stonehiy.jetpackdemo.base

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.stonehiy.jetpackdemo.ui.login.LoginActivity
import io.github.stonehiy.lib.core.IView
import io.github.stonehiy.lib.util.ToastUtil

class NetView constructor(private val context: Context, private val showLoading: Boolean = true) : IView {

    private val progressDialog: ProgressDialog

    init {
        progressDialog = ProgressDialog(context)
    }

    override fun showLoading() {
        if (!showLoading) {
            return
        }
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
        ToastUtil.show(context, msg, Toast.LENGTH_SHORT)
    }

    override fun reLoginActivity() {
        context.startActivity(Intent(context, LoginActivity::class.java))
    }
}