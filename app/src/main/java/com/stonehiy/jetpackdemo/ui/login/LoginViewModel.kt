package com.stonehiy.jetpackdemo.ui.login

import android.arch.lifecycle.MutableLiveData
import com.stonehiy.jetpackdemo.data.net.apiService
import com.stonehiy.jetpackdemo.entity.User
import io.github.stonehiy.lib.core.viewmodel.BaseViewModel
import io.github.stonehiy.lib.ext.request
import io.github.stonehiy.lib.state.ResultState

class LoginViewModel : BaseViewModel() {


    // Two-way databinding, exposing MutableLiveData
    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    init {
        username.value = "shigang"
        password.value = "123456"
    }

    val loginUser = MutableLiveData<User>()
    val loginResult = MutableLiveData<ResultState<User>>()

    /**
     * 登录
     */
    fun login() {
//        request({ apiService.login(username.value!!, password.value!!) },
//                loginResult,
//                true
//        )


        request({ apiService.login(username.value!!, password.value!!) },
                {user->{
                    loginUser.postValue(user)
                }}

        )

    }

}