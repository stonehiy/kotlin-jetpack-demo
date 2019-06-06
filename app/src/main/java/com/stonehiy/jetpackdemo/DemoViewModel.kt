package com.stonehiy.jetpackdemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author ShiGang <ShiGang, stonehiy@163.com>
 * @version v1.0
 * @Description TODO
 * @encoding UTF-8
 * @date 2019/5/20
 * @time 11:36
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
class DemoViewModel : ViewModel() {

    //https://www.tianqiapi.com/api/?version=v1

    val value: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }


}