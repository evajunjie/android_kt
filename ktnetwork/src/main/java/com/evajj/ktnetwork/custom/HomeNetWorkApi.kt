package com.evajj.ktnetwork.custom

import com.evajj.ktnetwork.base.NetworkApi

import okhttp3.Interceptor
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Author:wenjunjie
 * Date:2023/3/7
 * Time:下午5:13
 * Description:
 **/

object HomeNetWorkApi : NetworkApi() {
    val BaseUrl = "https://www.wanandroid.com"
    override fun getInterceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain -> chain.proceed(chain.request()) }
    }

    fun <T> getService(service: Class<T>): T {
      return getRetrofit(service)!!.create(service)
    }

    override fun <T> T.getAppErrorHandler(): T {
        return this
    }

    override fun getFormal(): String = BaseUrl

    override fun getTest(): String = BaseUrl;
}