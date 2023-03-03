package com.evajj.ktbase.exception

import android.net.ParseException
import com.evajj.ktbase.util.NetworkUtil
import com.evajj.ktbase.util.Utils
import com.google.gson.JsonParseException
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException
import javax.net.ssl.SSLPeerUnverifiedException

/**
 * Author:wenjunjie
 * Date:2023/3/3
 * Time:上午10:37
 * Description:
 **/
object ExceptionHandle {

    const val UNAUTHORIZED = 401
    const val FORBIDDEN = 403
    const val NOT_FOUND = 404
    const val REQUEST_TIMEOUT = 408
    const val INTERNAL_SERVER_ERROR = 500
    const val BAD_GATEWAY = 502
    const val SERVICE_UNAVAILABLE = 503
    const val GATEWAY_TIMEOUT = 504
    const val APP_NO_EXIST = 801


    fun handleException(e: Throwable): ResponeThrowable? {
        val ex: ResponeThrowable
        return if (e is HttpException) {
            ex = ResponeThrowable(e, ERROR.HTTP_ERROR)
            when (e.code()) {
                UNAUTHORIZED, FORBIDDEN, NOT_FOUND, REQUEST_TIMEOUT, GATEWAY_TIMEOUT, INTERNAL_SERVER_ERROR, BAD_GATEWAY, SERVICE_UNAVAILABLE -> ex.message =
                    "网络错误"
                else -> ex.message = "网络错误"
            }
            ex
        } else if (e is ServerException) {
            val resultException = e
            ex = ResponeThrowable(resultException, resultException.code)
            ex.message = resultException.message
            ex
        } else if (e is JsonParseException
            || e is JSONException
            || e is ParseException
        ) {
            ex = ResponeThrowable(e, ERROR.PARSE_ERROR)
            ex.message = "解析错误"
            ex
        } else if (e is ConnectException) {
            ex = ResponeThrowable(e, ERROR.NETWORD_ERROR)
            ex.message = "连接失败"
            ex
        } else if (e is SSLHandshakeException) {
            ex = ResponeThrowable(e, ERROR.SSL_ERROR)
            ex.message = "证书验证失败"
            ex
        } else if (e is ConnectTimeoutException) {
            ex = ResponeThrowable(e, ERROR.TIMEOUT_ERROR)
            ex.message = "连接超时"
            ex
        } else if (e is SocketTimeoutException) {
            ex = ResponeThrowable(e, ERROR.TIMEOUT_ERROR)
            ex.message = "连接超时"
            ex
        } else if (e is UnknownHostException) {
            ex = ResponeThrowable(e, ERROR.NETWORD_ERROR)
            ex.message = e.message
            ex
        } else if (e is NetWorkException) {
            ex = ResponeThrowable(e, ERROR.NETWORK_DISCONNECT)
            ex.message = "没连接网络"
            ex
        } else if (e is SocketException) {
            ex = ResponeThrowable(e, ERROR.NETWORD_ERROR)
            if (!NetworkUtil.isNetworkAvailable(Utils.context)) {
                ex.message = "网络出错"
            }
            ex
        } else if (e is NoSpaceLeftException) {
            ex = ResponeThrowable(e, ERROR.NO_SPACE_LEFT)
            ex.message = "没有存储空间"
            ex
        } else if (e is SSLPeerUnverifiedException) {
            ex = ResponeThrowable(e, ERROR.SSL_ERROR)
            ex.message = "SSL认证出错"
            ex
        } else {
            ex = ResponeThrowable(e, ERROR.UNKNOWN)
            ex.message = "未知错误"
            ex
        }
    }


    /**
     * 约定异常
     */
    object ERROR {
        /**
         * 未知错误
         */
        const val UNKNOWN = 1000

        /**
         * 解析错误
         */
        const val PARSE_ERROR = 1001

        /**
         * 网络错误
         */
        const val NETWORD_ERROR = 1002

        /**
         * 协议出错
         */
        const val HTTP_ERROR = 1003

        /**
         * 证书出错
         */
        const val SSL_ERROR = 1005

        /**
         * 连接超时
         */
        const val TIMEOUT_ERROR = 1006

        /**
         * 没连接网络
         */
        const val NETWORK_DISCONNECT = 1007

        /**
         * 没有存储空间
         */
        const val NO_SPACE_LEFT = 1008

        /**
         * 文件损坏
         */
        const val FILE_CORRUPTION = 1009
    }

    class ResponeThrowable(throwable: Throwable?, var code: Int) : Exception(throwable) {
        override var message: String? = null
    }

    class ServerException : RuntimeException() {
        var code = 0
        override var message: String? = null
    }

    class NetWorkException : RuntimeException() {
        var code = 0
        override var message: String? = null
    }

    class NoSpaceLeftException : RuntimeException() {
        var code = 0
        override var message = "存储空间不足"
    }

    class FileException : RuntimeException() {
        var code = 0
        override var message: String? = null
    }
}