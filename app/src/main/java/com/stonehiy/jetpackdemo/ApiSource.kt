package com.stonehiy.jetpackdemo

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author ShiGang <ShiGang, stonehiy@163.com>
 * @version v1.0
 * @Description TODO
 * @encoding UTF-8
 * @date 2019/5/21
 * @time 17:00
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
class ApiSource {
    companion object {
        private val okHttpClient =
                OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor().setLevel(
                                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                                else HttpLoggingInterceptor.Level.NONE))
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .readTimeout(15, TimeUnit.SECONDS)
                        .writeTimeout(15, TimeUnit.SECONDS)
                        .build()
        @JvmField
        val instance = Retrofit.Builder()
                .baseUrl("https://wanandroid.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
                .client(okHttpClient)
                .build().create(ApiService::class.java)


    }


}