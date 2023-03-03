package com.evajj.ktnetwork.weaknetwork

import android.os.SystemClock
import okhttp3.*
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException

/**
 * Author:wenjunjie
 * Date:2023/3/2
 * Time:下午5:26
 * Description:
 **/
object WeakNetworkManager {
    //模拟断网
    val TYPE_OFF_NETWORK = 1

    //模拟超时
    val TYPE_TIMEOUT = 2

    //模拟弱网
    val TYPE_SPEED_LIMIT = 3

    //默认超时时间
    val DEFAULT_TIMEOUT_MILLIS = 2000

    //默认限速字节（单位kb）：1 kb
    val DEFAULT_REQUEST_SPEED = 1
    val DEFAULT_RESPONSE_SPEED = 1

    private var mType = 0
    private var mTimeOutMillis = DEFAULT_TIMEOUT_MILLIS.toLong()
    private var mRequestSpeed = DEFAULT_REQUEST_SPEED.toLong()
    private var mResponseSpeed = DEFAULT_RESPONSE_SPEED.toLong()

    fun isActive(): Boolean {
        return mType != 0
    }


    fun setParameter(timeOutMillis: Long, requestSpeed: Long, responseSpeed: Long) {
        if (timeOutMillis > 0) {
            mTimeOutMillis = timeOutMillis
        }
        mRequestSpeed = requestSpeed
        mResponseSpeed = responseSpeed
    }

    fun setType(type: Int) {
        mType = type
    }

    fun getType(): Int {
        return mType
    }

    fun getTimeOutMillis(): Long {
        return mTimeOutMillis
    }

    fun getRequestSpeed(): Long {
        return mRequestSpeed
    }

    fun getResponseSpeed(): Long {
        return mResponseSpeed
    }

    /**
     * 模拟断网
     */
    @Throws(IOException::class)
    fun simulateOffNetwork(chain: Interceptor.Chain): Response {
        val response: Response = chain.proceed(chain.request())
        val responseBody =  "".toResponseBody(response.body!!.contentType())
        return response.newBuilder()
            .code(400)
            .message(
                String.format(
                    "Unable to resolve host %s: No address associated with hostname",
                    chain.request().url.host
                )
            )
            .body(responseBody)
            .build()
    }

    /**
     * 模拟超时
     *
     * @param chain url
     */
    @Throws(IOException::class)
    fun simulateTimeOut(chain: Interceptor.Chain): Response {
        SystemClock.sleep(mTimeOutMillis)
        val response: Response = chain.proceed(chain.request())
        val responseBody = ResponseBody.create(response.body!!.contentType(), "")
        return response.newBuilder()
            .code(400)
            .message(
                String.format(
                    "failed to connect to %s  after %dms",
                    chain.request().url.host,
                    mTimeOutMillis
                )
            )
            .body(responseBody)
            .build()
    }

    /**
     * 限速
     */
    @Throws(IOException::class)
    fun simulateSpeedLimit(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        val body = request.body
        if (body != null) {
            //大于0使用限速的body 否则使用原始body
            val requestBody: RequestBody =
                if (mRequestSpeed > 0) SpeedLimitRequestBody(mRequestSpeed, body) else body
            request = request.newBuilder().method(request.method, requestBody).build()
        }
        val response: Response = chain.proceed(request)
        //大于0使用限速的body 否则使用原始body
        val newResponseBody = response.body?.run {
            if (mResponseSpeed > 0) SpeedLimitResponseBody(
                mResponseSpeed,this)
            else this
        }
        return response.newBuilder().body(newResponseBody).build()
    }
}