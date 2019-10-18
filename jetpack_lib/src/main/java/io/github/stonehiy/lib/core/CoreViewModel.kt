package io.github.stonehiy.lib.core

import androidx.lifecycle.*
import io.github.stonehiy.lib.exception.ApiException
import io.github.stonehiy.lib.exception.ServerException
import io.github.stonehiy.lib.result.MyResult
import kotlinx.coroutines.*
import retrofit2.Response
import timber.log.Timber
import java.lang.Exception


open class CoreViewModel : ViewModel() {


//    private val FACTORY = object : ViewModelProvider.Factory {
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            val viewModel = FragmentManagerViewModel(true)
//            return viewModel as T
//        }
//    }
//
//    fun getInstance(viewModelStore: ViewModelStore): FragmentManagerViewModel {
//        val viewModelProvider = ViewModelProvider(viewModelStore,
//                FACTORY)
//        return viewModelProvider.get(FragmentManagerViewModel::class.java)
//    }


    /**
     * 开启协程
     */
    fun <T> coroutineJob(block: suspend () -> Response<out IResult<T>>, liveData: CoreLiveData<T>) {
        viewModelScope.launch {

            coroutineJobScope(block, liveData)

        }
    }

    private suspend fun <T> coroutineJobScope(block: suspend () -> Response<out IResult<T>>, liveData: CoreLiveData<T>) = withContext(Dispatchers.IO) {

        Timber.i("coroutineJobScope: I'm working in thread ${Thread.currentThread().name}")

        // Heavy work
        liveData.postValue(MyResult.Loading)
        delay(5000)
        try {
            val runJob = runJob(block)
            if (runJob.isSuccessful) {
                val body = runJob.body()
                if (body?.success() == true) {
                    liveData.postValue(MyResult.Success(body))
                } else {
                    if (body?.authentication401() == true) {
                        liveData.postValue(MyResult.Authentication401)
                    } else {
                        liveData.postValue(MyResult.Error(ServerException.handleException(ApiException(body?.errorMsg()
                                ?: ""))))
                    }
                }
            } else {
                if (runJob.code() == 401) {
                    liveData.postValue(MyResult.Authentication401)
                } else {
                    val errorBody = runJob.errorBody()
                    liveData.postValue(MyResult.Error(ServerException.handleException(Exception(errorBody?.string()))))
                }
            }
        } catch (e: Exception) {
            liveData.postValue(MyResult.Error(ServerException.handleException(e)))
        }
    }

    private suspend fun <T> runJob(block: suspend () -> Response<out IResult<T>>): Response<out IResult<T>> {
        return block()
    }


}