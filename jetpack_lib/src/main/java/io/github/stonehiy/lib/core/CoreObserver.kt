package io.github.stonehiy.lib.core

import androidx.lifecycle.Observer
import io.github.stonehiy.lib.entity.ResultEntity
import io.github.stonehiy.lib.result.MyResult


abstract class CoreObserver<T> constructor(val view: IView, val showLoading: Boolean = true) : Observer<MyResult<ResultEntity<T>>> {


    override fun onChanged(t: MyResult<ResultEntity<T>>?) {
        when (t) {
            is MyResult.Success -> {
                onSuccess(t.data)
                onComplete()

            }

            is MyResult.Error -> {
                onError(t.exception)
                view.showError(t.exception.message!!)
                onComplete()

            }

            is MyResult.Authentication401 -> {
                view.reLoginActivity()
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

    abstract fun onSuccess(r: ResultEntity<T>)
    open fun onError(exception: Exception) {}
    open fun onLoading() {}
    open fun onComplete() {
        view.hideLoading()
    }


}