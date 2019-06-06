package com.stonehiy.jetpackdemo

import com.stonehiy.jetpackdemo.entity.WeatherEntity
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

/**
 * @author ShiGang <ShiGang, stonehiy@163.com>
 * @version v1.0
 * @Description TODO
 * @encoding UTF-8
 * @date 2019/5/21
 * @time 13:54
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
interface ApiService {

    /*
    @GET("/user")
    fun getUser(): Deferred<User>

    // or

    @GET("/user")
    fun getUser(): Deferred<Response<User>>
    */

    @GET("api/?version=v1")
//    @GET("1")
    fun getWeather(): Deferred<Response<WeatherEntity>>

}