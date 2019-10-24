package io.github.stonehiy.lib.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.stonehiy.lib.exception.ApiException
import io.github.stonehiy.lib.exception.ServerException
import io.github.stonehiy.lib.result.MyResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Exception


/**
 * 开启协程（单任务）
 */
inline fun <T> ViewModel.coroutineJob(noinline block: suspend () -> IResult<T>, liveData: CoreLiveData<T>) {
    viewModelScope.launch {
        withContext(Dispatchers.IO) {
            Timber.i("coroutineJobScope: I'm working in thread ${Thread.currentThread().name}")
            // Heavy work
            liveData.postValue(MyResult.Loading)
//        delay(5000)
            try {
                val body = block()
                if (body.success()) {
                    liveData.postValue(MyResult.Success(body))
                } else {
                    if (body.authentication401()) {
                        liveData.postValue(MyResult.Authentication401)
                    } else {
                        liveData.postValue(MyResult.Error(ServerException.handleException(ApiException(body.errorMsg()))))
                    }
                }
            } catch (e: Exception) {
                Timber.w(e)
                liveData.postValue(MyResult.Error(ServerException.handleException(e)))
            }

        }
    }
}