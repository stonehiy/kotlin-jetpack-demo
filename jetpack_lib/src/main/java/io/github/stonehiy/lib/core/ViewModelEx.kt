package io.github.stonehiy.lib.core


import android.arch.lifecycle.ViewModel
import com.qhebusbar.basis.coroutine.ViewModelCoroutineScope
import io.github.stonehiy.lib.net.AppException
import io.github.stonehiy.lib.net.ExceptionHandle
import io.github.stonehiy.lib.result.SResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import timber.log.Timber
import kotlin.coroutines.CoroutineContext


/**
 * 开启协程（单任务）
 */
@Deprecated("")
inline fun <T> ViewModel.coroutineJob(noinline block: suspend () -> IResult<T>, liveData: CoreLiveData<T>, coroutineScope: ViewModelCoroutineScope, context: CoroutineContext = Dispatchers.Main) {
    coroutineScope.launch {
        withContext(context) {
            Timber.i("coroutineJobScope: I'm working in thread ${Thread.currentThread().name}")
            // Heavy work
            liveData.postValue(SResult.Loading)
//        delay(5000)
            try {
                val body = block()
                if (body.success()) {
                    liveData.postValue(SResult.Success(body))
                } else {
                    if (body.authentication401()) {
                        liveData.postValue(SResult.Authentication401)
                    } else {
                        liveData.postValue(SResult.Error(ExceptionHandle.handleException(AppException(body.errorCode(), body.errorMsg()))))
                    }
                }
            } catch (e: Exception) {
                Timber.w(e)
                liveData.postValue(SResult.Error(ExceptionHandle.handleException(e)))
            }

        }
    }
}




