package com.evajj.ktnetwork.commoninterceptor

import com.evajj.ktbase.util.LogUtil
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Author:wenjunjie
 * Date:2023/3/2
 * Time:下午4:32
 * Description:
 **/
class CommonResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val TAG = CommonResponseInterceptor::class.java.name
        val requestTime = System.currentTimeMillis()
        val response = chain.proceed(chain.request())
        LogUtil.d(
            TAG,
            "requestTime=" + (System.currentTimeMillis() - requestTime)
        )
        return response
    }
}