package com.stonehiy.jetpackdemo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stonehiy.jetpackdemo.entity.Author
import com.stonehiy.jetpackdemo.entity.ResultEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

/**
 * @author ShiGang <ShiGang, stonehiy@163.com>
 * @version v1.0
 * @Description TODO
 * @encoding UTF-8
 * @date 2019/5/20
 * @time 16:05
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
class WeatherViewModel : ViewModel() {
    val TAG = WeatherViewModel::class.java.name

//    private val mWeather: MutableLiveData<WeatherEntity> = MutableLiveData()


//    private val viewModelJob = SupervisorJob()
//
//    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
//        viewModelJob.cancel() // Cancel all coroutines
    }

    fun launchDataLoad() {
//        uiScope.launch {
//            Log.i(TAG, "----------uiScope.launch-------------")
//            sortList()
//            // Modify UI
//        }

        viewModelScope.launch {
            Log.i(TAG, "----------viewModelScope.launch-------------")
            sortList()
            // Modify UI
        }
    }

    suspend fun sortList() = withContext(Dispatchers.IO) {
        // Heavy work
        Log.i(TAG, "----------sortList-------------")
        try {
            val weather = ApiSource.instance.getWeather().await()
            Log.i(TAG, "weather.code() = ${weather.code()}")
            if (weather.isSuccessful) {
                val body = weather.body()
                Log.i(TAG, "body = $body")
                mWeather.postValue(body)
            } else {
                val errorBody = weather.errorBody()
                Log.i(TAG, "errorBody = ${errorBody?.string()}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }


    private val mWeather: MutableLiveData<ResultEntity<List<Author>>> by lazy {
        MutableLiveData<ResultEntity<List<Author>>>().also {
            //            launchDataLoad()
            Log.i(TAG, "----------mWeather-------------")
        }

    }


    fun getWeather(): LiveData<ResultEntity<List<Author>>> {
        return mWeather
    }


}