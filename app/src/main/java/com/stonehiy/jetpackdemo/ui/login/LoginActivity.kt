package com.stonehiy.jetpackdemo.ui.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.stonehiy.jetpackdemo.MainActivity
import com.stonehiy.jetpackdemo.R
import com.stonehiy.jetpackdemo.base.CoreActivity
import com.stonehiy.jetpackdemo.databinding.ActivityLoginBinding
import io.github.stonehiy.lib.ext.parseState
import io.github.stonehiy.lib.util.ToastUtil


class LoginActivity : CoreActivity<LoginViewModel, ActivityLoginBinding>() {


    override fun layoutId(): Int = R.layout.activity_login

    override fun initView(savedInstanceState: Bundle?) {}


    override fun createObserver() {
        mDatabind.viewModel = mViewModel
        mViewModel.loginResult.observe(this, Observer {
            //parseState()
            //startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        })
        mViewModel.loginUser.observe(this, Observer {
            ToastUtil.show(this@LoginActivity, "登录成功", Toast.LENGTH_SHORT)
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        })


    }
}