package io.github.stonehiy.lib.core

import androidx.lifecycle.Observer
import io.github.stonehiy.lib.exception.ServerException
import io.github.stonehiy.lib.result.MyResult


abstract class CoreObserver<T> constructor(val view: IView) : Observer<MyResult<out IResult<T>>> {


    override fun onChanged(t: MyResult<out IResult<T>>?) {
        when (t) {
            is MyResult.Success -> {
                onSuccess(t.data)
                onComplete()

            }

            is MyResult.Error -> {
                onError(t.exception)
                onComplete()
            }

            is MyResult.Authentication401 -> {
                onAuthentication401()
                onComplete()
            }

            MyResult.Loading
            -> {
                onLoading()

            }


        }

    }

    abstract fun onSuccess(r: IResult<T>)

    open fun onError(exception: ServerException) {
        view.showError(exception.msg)
    }

    open fun onLoading() {
        view.showLoading()
    }

    open fun onComplete() {
        view.hideLoading()
    }

    open fun onAuthentication401() {
        view.reLoginActivity()
    }


}