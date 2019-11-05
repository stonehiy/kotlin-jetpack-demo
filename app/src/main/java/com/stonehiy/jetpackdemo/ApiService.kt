package com.stonehiy.jetpackdemo

import androidx.paging.PagedList
import com.stonehiy.jetpackdemo.entity.Author
import com.stonehiy.jetpackdemo.entity.Banner
import com.stonehiy.jetpackdemo.entity.ResultEntity
import com.stonehiy.jetpackdemo.entity.User
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

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


    @GET("/wxarticle/chapters/json")
    suspend fun getChapters(): ResultEntity<List<Author>>

    @GET("/banner/json")
    suspend fun getBanners(): ResultEntity<List<Banner>>


    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(@FieldMap map: Map<String, String>): ResultEntity<User>


}