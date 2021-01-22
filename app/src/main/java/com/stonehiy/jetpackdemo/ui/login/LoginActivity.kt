package com.stonehiy.jetpackdemo.ui.login

import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.stonehiy.jetpackdemo.MainActivity
import com.stonehiy.jetpackdemo.R
import com.stonehiy.jetpackdemo.base.NetView
import com.stonehiy.jetpackdemo.databinding.ActivityLoginBinding
import com.stonehiy.jetpackdemo.entity.User
import io.github.stonehiy.lib.core.CoreObserver
import io.github.stonehiy.lib.core.IResult
import io.github.stonehiy.lib.util.ToastUtil
import io.github.stonehiy.lib.util.viewModelProvider


class LoginActivity : AppCompatActivity() {

    private lateinit var mModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mModel = viewModelProvider(ViewModelProvider.AndroidViewModelFactory.getInstance(application))
        var binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = mModel



        mModel.mLogin.observe(this, object : CoreObserver<User>(NetView(this)) {
            override fun onSuccess(r: IResult<User>) {
//                tvDemo2.text = r.toString()
                ToastUtil.show(this@LoginActivity, "登录成功", Toast.LENGTH_SHORT)
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))

            }
        })


    }
}
