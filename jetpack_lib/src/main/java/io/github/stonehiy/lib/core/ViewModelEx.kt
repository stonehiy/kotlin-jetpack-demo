package io.github.stonehiy.lib.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.stonehiy.lib.exception.ApiException
import io.github.stonehiy.lib.exception.ServerException
import io.github.stonehiy.lib.result.SResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
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
                        liveData.postValue(SResult.Error(ServerException.handleException(ApiException(body.errorMsg()))))
                    }
                }
            } catch (e: Exception) {
                Timber.w(e)
                liveData.postValue(SResult.Error(ServerException.handleException(e)))
            }

        }
    }
}

/**
 * 开启协程（多任务）
 */
@Deprecated("Deprecated")
inline fun <T> ViewModel.coroutineMJob(vararg block: suspend () -> IResult<T>, liveData: CoreLiveData<T>) {
    viewModelScope.launch {
        withContext(Dispatchers.IO) {
            Timber.i("coroutineJobScope: I'm working in thread ${Thread.currentThread().name}")
            // Heavy work
            liveData.postValue(SResult.Loading)
//        delay(5000)
            try {
                block.forEach {
                    val body = it()
                    if (body.success()) {
                        liveData.postValue(SResult.Success(body))
                    } else {
                        if (body.authentication401()) {
                            liveData.postValue(SResult.Authentication401)
                        } else {
                            liveData.postValue(SResult.Error(ServerException.handleException(ApiException(body.errorMsg()))))
                        }
                    }
                }
            } catch (e: Exception) {
                Timber.w(e)
                liveData.postValue(SResult.Error(ServerException.handleException(e)))
            }

        }
    }
}



