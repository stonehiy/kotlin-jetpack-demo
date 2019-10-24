package io.github.stonehiy.lib.core

import androidx.lifecycle.*
import io.github.stonehiy.lib.exception.ApiException
import io.github.stonehiy.lib.exception.ServerException
import io.github.stonehiy.lib.result.MyResult
import kotlinx.coroutines.*
import retrofit2.Response
import timber.log.Timber
import java.lang.Exception

/**
 * use [ViewModel#coroutineJob]
 */
@Deprecated("Deprecated")
open class CoreViewModel : ViewModel() {


    private suspend fun <T> coroutineJobScope(block: suspend () -> IResult<T>, liveData: CoreLiveData<T>) = withContext(Dispatchers.IO) {

        Timber.i("coroutineJobScope: I'm working in thread ${Thread.currentThread().name}")

        // Heavy work
        liveData.postValue(MyResult.Loading)
//        delay(5000)
        try {
            val body = runJob(block)
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

    private suspend fun <T> runJob(block: suspend () -> IResult<T>): IResult<T> {
        return block()
    }


    /**
     * 开启协程（单任务）
     */
    @Deprecated("Deprecated")
    fun <T> coroutineJob(block: suspend () -> IResult<T>, liveData: CoreLiveData<T>) {
        viewModelScope.launch {

            coroutineJobScope(block, liveData)

        }
    }


}