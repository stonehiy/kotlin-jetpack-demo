package com.stonehiy.jetpackdemo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stonehiy.jetpackdemo.entity.Author
import io.github.stonehiy.lib.core.CoreViewModel
import io.github.stonehiy.lib.entity.ResultEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Exception
import kotlin.concurrent.timerTask

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
class WeatherViewModel : CoreViewModel<List<Author>>() {

//    private val mWeather: MutableLiveData<WeatherEntity> = MutableLiveData()
//    private val viewModelJob = SupervisorJob()
//
//    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    fun getData() {
        coroutineJob {
            ApiSource.instance.getWeather()

        }

    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("onCleared")
    }


}