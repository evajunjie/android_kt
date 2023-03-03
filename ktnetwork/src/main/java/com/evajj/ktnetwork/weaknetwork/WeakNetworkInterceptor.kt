package com.evajj.ktnetwork.weaknetwork

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Author:wenjunjie
 * Date:2023/3/2
 * Time:下午5:25
 * Description:
 **/
class WeakNetworkInterceptor : Interceptor {
    val TAG = WeakNetworkInterceptor::class.java.name
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!WeakNetworkManager.isActive()) {
            val request = chain.request()
            return chain.proceed(request)
        }
        val type: Int = WeakNetworkManager.getType()
        return when (type) {
            WeakNetworkManager.TYPE_TIMEOUT -> {
                //超时
                Log.i(
                    TAG,
                    "intercept: 超时模拟"
                )
                WeakNetworkManager.simulateTimeOut(chain)
            }
            WeakNetworkManager.TYPE_SPEED_LIMIT -> {
                //限速
                Log.i(
                    TAG,
                    "intercept: 弱网模拟"
                )
                WeakNetworkManager.simulateSpeedLimit(chain)
            }
            else -> {
                //断网
                Log.i(
                    TAG,
                    "intercept: 断网模拟"
                )
                WeakNetworkManager.simulateOffNetwork(chain)
            }
        }
    }
}