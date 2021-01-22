package com.stonehiy.jetpackdemo.ui.login

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.qhebusbar.basis.coroutine.ViewModelCoroutineScope
import com.stonehiy.jetpackdemo.ApiSource
import com.stonehiy.jetpackdemo.entity.User
import io.github.stonehiy.lib.core.CoreLiveData
import io.github.stonehiy.lib.core.coroutineJob

class LoginViewModel : ViewModel() {


    // Two-way databinding, exposing MutableLiveData
    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    init {
        username.value = "shigang"
        password.value = "123456"
    }


    val mLogin: CoreLiveData<User> = CoreLiveData()
    /**
     * 登录
     */
    fun login() {
        val mapOf = mapOf<String, String>("username" to username.value!!, "password" to password.value!!)
        coroutineJob({
            ApiSource.instance.login(mapOf)
        }, mLogin, ViewModelCoroutineScope())

    }
}