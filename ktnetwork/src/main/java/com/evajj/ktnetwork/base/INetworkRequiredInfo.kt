package com.evajj.ktnetwork.base

import android.app.Application
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
    fun getApplicationContext(): Application
    fun getInterceptor(): Interceptor?

}