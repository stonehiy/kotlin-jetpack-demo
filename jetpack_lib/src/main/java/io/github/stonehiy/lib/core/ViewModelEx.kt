package io.github.stonehiy.lib.core

import androidx.lifecycle.*
import io.github.stonehiy.lib.exception.ApiException
import io.github.stonehiy.lib.exception.ServerException
import io.github.stonehiy.lib.result.SResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import kotlin.coroutines.CoroutineContext


/**
 * 开启协程（单任务）
 */
inline fun <T> ViewModel.coroutineJob(noinline block: suspend () -> IResult<T>, liveData: CoreLiveData<T>, context: CoroutineContext = Dispatchers.Main) {
    viewModelScope.launch {
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




