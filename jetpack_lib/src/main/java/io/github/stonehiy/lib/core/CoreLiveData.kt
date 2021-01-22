package io.github.stonehiy.lib.core


import android.arch.lifecycle.MutableLiveData
import io.github.stonehiy.lib.result.SResult

@Deprecated("")
open class CoreLiveData<R> : MutableLiveData<SResult<out IResult<R>>>() {


}