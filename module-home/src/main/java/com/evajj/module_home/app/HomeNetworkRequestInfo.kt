package com.evajj.module_home.app

import android.app.Application
import android.content.Context
import android.util.Log
import com.evajj.ktnetwork.base.INetworkRequiredInfo

import okhttp3.Interceptor
import com.evajj.module_home.BuildConfig

/**
 * Author:wenjunjie
 * Date:2023/3/10
 * Time:下午4:57
 * Description:
 **/
class HomeNetworkRequestInfo(private val mApplicationContext : Context) : INetworkRequiredInfo {

    val TAG = HomeNetworkRequestInfo ::class.java.name

    override fun getAppVersionName(): String? = BuildConfig.VERSION_NAME

    override fun getAppVersionCode(): String? = BuildConfig.VERSION_CODE

    override fun isDebug(): Boolean {
        return BuildConfig.DEBUG || Log.isLoggable(TAG, Log.VERBOSE)
    }

    override fun getApplicationContext(): Context {
        return mApplicationContext
    }

    override fun getInterceptor(): Interceptor? {
        return null
    }
}