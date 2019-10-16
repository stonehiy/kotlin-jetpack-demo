package io.github.stonehiy.lib.exception

import com.google.gson.JsonParseException
import com.google.gson.JsonSerializer
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import java.io.IOException
import java.io.NotSerializableException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

class ServerException(throwable: Throwable) : Exception() {

    var msg: String = ""

    constructor(throwable: Throwable, code: Int) : this(throwable)

    companion object {
        @JvmStatic
        fun handleException(e: Throwable): ServerException {

            val ex: ServerException
            if (e is HttpException) {
                val httpException = e as HttpException
                ex = ServerException(httpException, httpException.code())
                try {
                    if (504 == httpException.code()) {
                        ex.msg = "网络连接异常，请检查您的网络状态，稍后重试"
                    } else if (404 == httpException.code() || 502 == httpException.code()) {
                        ex.msg = "网络连接异常，稍后重试"
                    } else {
                        ex.msg = httpException.response()!!.errorBody()!!.string()
                    }
                } catch (e1: IOException) {
                    e1.printStackTrace()
                    ex.msg = e1.message ?: ""
                }

                return ex
            } else if (e is SocketTimeoutException) {
                ex = ServerException(e, ERROR.TIMEOUT_ERROR)
                ex.msg = "网络连接超时，请检查您的网络状态，稍后重试"
                return ex
            } else if (e is ConnectException) {
                ex = ServerException(e, ERROR.TIMEOUT_ERROR)
                ex.msg = "网络连接异常，请检查您的网络状态，稍后重试"
                return ex
            } else if (e is ConnectTimeoutException) {
                ex = ServerException(e, ERROR.TIMEOUT_ERROR)
                ex.msg = "网络连接超时，请检查您的网络状态，稍后重试"
                return ex
            } else if (e is UnknownHostException) {
                ex = ServerException(e, ERROR.TIMEOUT_ERROR)
                ex.msg = "网络连接异常，请检查您的网络状态，稍后重试"
                return ex
            } else if (e is NullPointerException) {
                ex = ServerException(e, ERROR.NULL_POINTER_EXCEPTION)
                ex.msg = "空指针异常"
                return ex
            } else if (e is javax.net.ssl.SSLHandshakeException) {
                ex = ServerException(e, ERROR.SSL_ERROR)
                ex.msg = "证书验证失败"
                return ex
            } else if (e is ClassCastException) {
                ex = ServerException(e, ERROR.CAST_ERROR)
                ex.msg = "类型转换错误"
                return ex
            } else if (e is JsonParseException
                    || e is JSONException
                    || e is JsonSerializer<*>
                    || e is NotSerializableException
                    || e is ParseException) {
                ex = ServerException(e, ERROR.PARSE_ERROR)
                ex.msg = "解析错误"
                return ex
            } else if (e is IllegalStateException) {
                ex = ServerException(e, ERROR.ILLEGAL_STATE_ERROR)
                ex.msg = e.message ?: ""
                return ex
            } else if (e is ApiException) {
                ex = ServerException(e, ERROR.UNKNOWN)
                ex.msg = e.message ?: ""
                return ex
            } else {
                ex = ServerException(e, ERROR.UNKNOWN)
                ex.msg = "未知错误"
                return ex
            }
        }
    }

}

/**
 * 约定异常
 */
object ERROR {
    /**
     * 未知错误
     */
    val UNKNOWN = 10000
    /**
     * 连接超时
     */
    val TIMEOUT_ERROR = 10001
    /**
     * 空指针错误
     */
    val NULL_POINTER_EXCEPTION = 10002

    /**
     * 证书出错
     */
    val SSL_ERROR = 10003

    /**
     * 类转换错误
     */
    val CAST_ERROR = 10004

    /**
     * 解析错误
     */
    val PARSE_ERROR = 10005

    /**
     * 非法数据异常
     */
    val ILLEGAL_STATE_ERROR = 10006


}