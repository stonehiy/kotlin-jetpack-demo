package io.github.stonehiy.lib.core


import android.arch.lifecycle.Observer
import io.github.stonehiy.lib.net.AppException
import io.github.stonehiy.lib.result.SResult


abstract class CoreObserver<T> constructor(val view: IView) : Observer<SResult<out IResult<T>>> {


    override fun onChanged(t: SResult<out IResult<T>>?) {
        when (t) {
            is SResult.Success -> {
                onSuccess(t.data)
                onComplete()

            }

            is SResult.Error -> {
                onError(t.exception)
                onComplete()
            }

            is SResult.Authentication401 -> {
                onAuthentication401()
                onComplete()
            }

            SResult.Loading
            -> {
                onLoading()

            }


        }

    }

    abstract fun onSuccess(r: IResult<T>)

    open fun onError(exception: AppException) {
        view.showError(exception.errorMsg)
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