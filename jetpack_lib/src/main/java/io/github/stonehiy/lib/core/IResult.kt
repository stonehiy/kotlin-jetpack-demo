package io.github.stonehiy.lib.core

import java.io.Serializable

interface IResult<T> : Serializable {
    fun success(): Boolean = false
    fun authentication401(): Boolean = false
    fun errorCode(): Int = 0
    fun errorMsg(): String = ""
    fun data(): T
}