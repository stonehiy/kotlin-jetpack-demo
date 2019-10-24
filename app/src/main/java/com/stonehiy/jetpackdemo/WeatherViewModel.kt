package com.stonehiy.jetpackdemo

import androidx.lifecycle.ViewModel
import com.stonehiy.jetpackdemo.entity.Author
import com.stonehiy.jetpackdemo.entity.Banner
import io.github.stonehiy.lib.core.CoreLiveData
import io.github.stonehiy.lib.core.CoreViewModel
import io.github.stonehiy.lib.core.coroutineJob

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

//    private val mWeather: MutableLiveData<WeatherEntity> = MutableLiveData()
//    private val viewModelJob = SupervisorJob()
//
//    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    val mChapters: CoreLiveData<List<Author>> = CoreLiveData()

    val mBanners: CoreLiveData<List<Banner>> = CoreLiveData()


    fun getChapters() {
        coroutineJob({
            ApiSource.instance.getChapters()
        }, mChapters)

    }

    fun getBanners() {
        coroutineJob({
            ApiSource.instance.getBanners()
        }, mBanners)
    }


}