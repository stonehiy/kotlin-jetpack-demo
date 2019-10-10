package com.stonehiy.jetpackdemo.entity

data class ResultEntity<T>(
        val data: T,
        val errorCode: Int,
        val errorMsg: String
)