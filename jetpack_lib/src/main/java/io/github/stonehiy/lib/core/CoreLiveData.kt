package io.github.stonehiy.lib.core

import androidx.lifecycle.MutableLiveData
import io.github.stonehiy.lib.result.MyResult


open class CoreLiveData<R> : MutableLiveData<MyResult<out IResult<R>>>() {


}