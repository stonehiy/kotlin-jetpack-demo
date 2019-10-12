package io.github.stonehiy.lib.core

import androidx.lifecycle.*
import io.github.stonehiy.lib.entity.ResultEntity
import io.github.stonehiy.lib.result.MyResult
import kotlinx.coroutines.*
import retrofit2.Response
import timber.log.Timber
import java.lang.Exception


open class CoreViewModel<T> : ViewModel() {


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


    val mMutableLiveData: MutableLiveData<MyResult<ResultEntity<T>>> = MutableLiveData()

    //val mMutableLiveDataError: MutableLiveData<String> = MutableLiveData()

    /**
     * 开启协程
     */
    fun coroutineJob(block: () -> Deferred<Response<ResultEntity<T>>>) {
        viewModelScope.launch {
            coroutineJobScope(block)
        }
    }

    private suspend fun coroutineJobScope(block: () -> Deferred<Response<ResultEntity<T>>>) = withContext(Dispatchers.IO) {
        // Heavy work
        try {
            val runJob = runJob(block)
            if (runJob.isSuccessful) {
                val body = runJob.body()
                if (body?.errorCode == 0) {
                    mMutableLiveData.postValue(MyResult.Success(body))
                } else {
                    mMutableLiveData.postValue(MyResult.Error(Exception(body?.errorMsg)))
                }
            } else {
                val errorBody = runJob.errorBody()
                mMutableLiveData.postValue(MyResult.Error(Exception(errorBody?.string())))
            }
        } catch (e: Exception) {
            mMutableLiveData.postValue(MyResult.Error(e))
        }


    }

    private suspend fun runJob(block: () -> Deferred<Response<ResultEntity<T>>>): Response<ResultEntity<T>> {
        return block().await()
    }

    override fun onCleared() {
        // Cancel all coroutines
        viewModelScope.cancel()
        Timber.i("onCleared -> viewModelScope.cancel()")
        super.onCleared()

    }

}