package com.stonehiy.jetpackdemo.entity

import io.github.stonehiy.lib.core.IResult

data class ResultEntity<T>(
        val data: T,
        val errorCode: Int,
        val errorMsg: String
) : IResult<T> {
    override fun authentication401(): Boolean {
        return 401 == errorCode
    }

    override fun success(): Boolean {
        return 0 == errorCode
    }

    override fun errorCode(): Int {
        return errorCode
    }

    override fun errorMsg(): String {
        return errorMsg
    }

    override fun data(): T {
        return data
    }
}