package io.github.stonehiy.lib.core

import androidx.lifecycle.MutableLiveData
import io.github.stonehiy.lib.result.SResult


open class CoreLiveData<R> : MutableLiveData<SResult<out IResult<R>>>() {


}