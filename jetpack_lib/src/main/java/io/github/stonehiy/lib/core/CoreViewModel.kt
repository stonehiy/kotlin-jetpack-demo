package io.github.stonehiy.lib.core

import androidx.lifecycle.*
import io.github.stonehiy.lib.exception.ApiException
import io.github.stonehiy.lib.exception.ServerException
import io.github.stonehiy.lib.result.SResult
import kotlinx.coroutines.*
import timber.log.Timber
import java.lang.Exception

/**
 * use [ViewModel.coroutineJob]
 */
@Deprecated("Deprecated")
open class CoreViewModel : ViewModel() {


    private suspend fun <T> coroutineJobScope(block: suspend () -> IResult<T>, liveData: CoreLiveData<T>) = withContext(Dispatchers.IO) {

        Timber.i("coroutineJobScope: I'm working in thread ${Thread.currentThread().name}")

        // Heavy work
        liveData.postValue(SResult.Loading)
//        delay(5000)
        try {
            val body = runJob(block)
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

    private suspend fun <T> runJob(block: suspend () -> IResult<T>): IResult<T> {
        return block()
    }


    /**
     * 开启协程（单任务）
     *
     */
    @Deprecated("Deprecated")
    fun <T> coroutineJob(block: suspend () -> IResult<T>, liveData: CoreLiveData<T>) {
        viewModelScope.launch {

            coroutineJobScope(block, liveData)

        }
    }


}