package com.evajj.ktnetwork.base

import android.app.Application
import android.content.Context
import okhttp3.Interceptor

/**
 * Author:wenjunjie
 * Date:2023/3/2
 * Time:下午2:08
 * Description:
 **/
interface INetworkRequiredInfo {
    fun getAppVersionName(): String?
    fun getAppVersionCode(): String?
    fun isDebug(): Boolean
    fun getApplicationContext(): Context
    fun getInterceptor(): Interceptor?

}