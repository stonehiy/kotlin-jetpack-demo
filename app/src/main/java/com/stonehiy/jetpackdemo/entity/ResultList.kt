package com.stonehiy.jetpackdemo.entity

import io.github.stonehiy.lib.core.IResult

data class ResultList<T, M>(
        val list: M,
        val data: T,
        val errorCode: Int,
        val errorMsg: String
) : IResult<T>, IList<M> {
    override fun list(): M {
        return list
    }

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