package io.github.stonehiy.lib.core

import androidx.lifecycle.Observer
import io.github.stonehiy.lib.exception.ServerException
import io.github.stonehiy.lib.result.MyResult


abstract class CoreObserver<T> constructor(val view: IView, val showLoading: Boolean = true) : Observer<MyResult<out IResult<T>>> {


    override fun onChanged(t: MyResult<out IResult<T>>?) {
        when (t) {
            is MyResult.Success -> {
                onSuccess(t.data)
                onComplete()

            }

            is MyResult.Error -> {
                onError(t.exception)
                view.showError(t.exception.msg)
                onComplete()

            }

            is MyResult.Authentication401 -> {
                view.reLoginActivity()
                onComplete()
            }

            MyResult.Loading
            -> {
                onLoading()
                if (showLoading) {
                    view.showLoading()
                }
            }


        }

    }

    abstract fun onSuccess(r: IResult<T>)
    open fun onError(exception: ServerException) {}
    open fun onLoading() {}
    open fun onComplete() {
        view.hideLoading()
    }


}