package com.evajj.ktnetwork.commoninterceptor

import com.evajj.ktbase.util.StringUtils
import com.evajj.ktbase.util.Utils
import com.evajj.ktnetwork.base.INetworkRequiredInfo
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Author:wenjunjie
 * Date:2023/3/2
 * Time:下午4:16
 * Description:
 **/
class CommonRequestInterceptor(private var _newWorkRequiredInfo :INetworkRequiredInfo) :Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        var range :String? =  null
        var builder = chain.request().apply {
            range = headers["range"]
        }.newBuilder()
        if (!range.isNullOrEmpty()) {
            //断点续传使用网络请求
            builder.cacheControl(CacheControl.FORCE_NETWORK)
        }
        return chain.proceed(builder.build())
    }
}