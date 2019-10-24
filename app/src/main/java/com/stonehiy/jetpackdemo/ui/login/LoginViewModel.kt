package com.stonehiy.jetpackdemo.ui.login

import androidx.lifecycle.MutableLiveData
import com.stonehiy.jetpackdemo.ApiSource
import com.stonehiy.jetpackdemo.entity.Banner
import com.stonehiy.jetpackdemo.entity.User
import io.github.stonehiy.lib.core.CoreLiveData
import io.github.stonehiy.lib.core.CoreViewModel

class LoginViewModel : CoreViewModel() {


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
        }, mLogin)

    }
}