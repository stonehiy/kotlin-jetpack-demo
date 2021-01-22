/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package io.github.stonehiy.lib.result

import io.github.stonehiy.lib.net.AppException


/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class SResult<out R> {

    data class Success<out T>(val data: T) : SResult<T>()
    data class Error(val exception: AppException) : SResult<Nothing>()
    object Authentication401 : SResult<Nothing>()
    object Loading : SResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            is Authentication401 -> "Authentication401"
            Loading -> "Loading"
        }
    }
}

/**
 * `true` if [Result] is of type [Success] & holds non-null [Success.data].
 */
val SResult<*>.succeeded
    get() = this is SResult.Success && data != null

fun <T> SResult<T>.successOr(fallback: T): T {
    return (this as? SResult.Success<T>)?.data ?: fallback
}

