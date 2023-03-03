package com.evajj.ktnetwork.base

import android.util.ArrayMap
import com.evajj.ktbase.exception.ExceptionHandle.handleException
import com.evajj.ktnetwork.commoninterceptor.CommonRequestInterceptor
import com.evajj.ktnetwork.commoninterceptor.CommonResponseInterceptor
import com.evajj.ktnetwork.environment.EnvironmentActivity
import com.evajj.ktnetwork.environment.IEnvironment
import com.evajj.ktnetwork.util.HttpsUtils
import com.evajj.ktnetwork.weaknetwork.WeakNetworkInterceptor
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Author:wenjunjie
 * Date:2023/3/2
 * Time:下午1:59
 * Description:
 **/

abstract class NetworkApi : IEnvironment {
    protected lateinit var _NetworkRequiredInfo: INetworkRequiredInfo
    protected var isFormat = true
    protected val mBaseUrl by lazy {
        if (!isFormat) {
            getTest()
        } else {
            getFormal()
        }
    }


    companion object {
        var retrofitHashMap: MutableMap<String, Retrofit> = ArrayMap();

    }

    protected abstract fun getInterceptor(): Interceptor

    open fun init(networkRequiredInfo: INetworkRequiredInfo) {
        _NetworkRequiredInfo = networkRequiredInfo
        isFormat =
            EnvironmentActivity.isOfficialEnvironment(networkRequiredInfo.getApplicationContext())
        val networkType: Int =
            EnvironmentActivity.getNetWorkType(networkRequiredInfo.getApplicationContext())
        //WeakNetworkManager.get().setType(networkType)
    }

    protected fun getRetrofit(service: Class<*>): Retrofit? {
        if (retrofitHashMap.get(mBaseUrl + service.name) != null) {
            return NetworkApi.retrofitHashMap.get(mBaseUrl + service.name)
        }
        val retrofitBuilder = Retrofit.Builder()
        retrofitBuilder.baseUrl(mBaseUrl)
        retrofitBuilder.client(_okHttpClient)
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create())
        //retrofitBuilder.addCallAdapterFactory(Flow.create())
        val retrofit = retrofitBuilder.build()
        NetworkApi.retrofitHashMap.put(mBaseUrl + service.name, retrofit)
        return retrofit
    }

    private val _okHttpClient by lazy {
        getOkHttpClient()
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(getInterceptor())
            _NetworkRequiredInfo.getInterceptor()?.let {
                addInterceptor(it)
            }

            val cacheSize = 100 * 1024 * 1024 // 10MB
            // okHttpClientBuilder.cache(new Cache(iNetworkRequiredInfo.getApplicationContext().getCacheDir(), cacheSize));
            val sslParams: HttpsUtils.SSLParams = HttpsUtils.getSslSocketFactory()
            sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
            addInterceptor(WeakNetworkInterceptor())
            addInterceptor(CommonRequestInterceptor(_NetworkRequiredInfo))
            addInterceptor(CommonResponseInterceptor())
            if (this@NetworkApi::_NetworkRequiredInfo.isInitialized && _NetworkRequiredInfo.isDebug()) {
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
                addInterceptor(httpLoggingInterceptor)
            }
        }.build()


    }

    abstract fun <T> T.getAppErrorHandler(): T

    suspend fun <T> Flow<T>.applySchedulers(
        requestCallback: RequestCallback<T>? = null,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
    ) {
        flowOn(dispatcher)
            .transform {
                emit(it.getAppErrorHandler())
            }
            .catch {
                requestCallback?.onError(ResponseError(handleException(it)))
            }.collect { result ->
                requestCallback?.onSuccess(ResponseSuccess(result))
            }
    }

}
