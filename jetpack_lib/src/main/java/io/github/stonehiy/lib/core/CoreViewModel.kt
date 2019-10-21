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


    private suspend fun <T> coroutineJobScope(block: suspend () -> IResult<T>, liveData: CoreLiveData<T>) = withContext(Dispatchers.IO) {

        Timber.i("coroutineJobScope: I'm working in thread ${Thread.currentThread().name}")

        // Heavy work
        liveData.postValue(MyResult.Loading)
        delay(5000)
        try {
            val body = runJob(block)
            if (body.success()) {
                liveData.postValue(MyResult.Success(body))
            } else {
                if (body.authentication401()) {
                    liveData.postValue(MyResult.Authentication401)
                } else {
                    liveData.postValue(MyResult.Error(ServerException.handleException(ApiException(body?.errorMsg()
                            ?: ""))))
                }
            }
        } catch (e: Exception) {
            liveData.postValue(MyResult.Error(ServerException.handleException(e)))
        }
    }

    private suspend fun <T> runJob(block: suspend () -> IResult<T>): IResult<T> {
        return block()
    }


    /**
     * 开启协程（单任务）
     */
    fun <T> coroutineJob(block: suspend () -> IResult<T>, liveData: CoreLiveData<T>) {
        viewModelScope.launch {

            coroutineJobScope(block, liveData)

        }
    }

    /**
     * 开启协程（多任务）
     */
    fun <T> coroutineMultiJob(vararg block: suspend () -> Response<out IResult<T>>, liveData: CoreLiveData<T>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                liveData.postValue(MyResult.Loading)
                try {
                    val result = mutableListOf<T>()
                    block.forEach {

                        val async = async {
                            val resJob = it()
//                            result.addAll(resJob)
                        }
                        /*
                        if (resJob.isSuccessful) {
                            val body = resJob.body()
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
                            if (resJob.code() == 401) {
                                liveData.postValue(MyResult.Authentication401)
                            } else {
                                val errorBody = resJob.errorBody()
                                liveData.postValue(MyResult.Error(ServerException.handleException(Exception(errorBody?.string()))))
                            }
                        }
                        */

                    }


                } catch (e: Exception) {
                    liveData.postValue(MyResult.Error(ServerException.handleException(e)))
                }
            }

        }
    }


}