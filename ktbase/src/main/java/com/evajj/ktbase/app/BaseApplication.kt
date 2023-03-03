package com.evajj.ktbase.app

import android.app.Application
import android.app.LauncherActivity
import android.content.Context
import android.util.Log
import com.evajj.ktbase.BuildConfig
import com.evajj.ktbase.R
import com.evajj.ktbase.util.LogUtil
import com.tpcdroid.crash.CaocConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

/**
 * Author:wenjunjie
 * Date:2023/2/21
 * Time:上午10:15
 * Description:
 **/

open class BaseApplication : Application() {

    private val mLoadModuleProxy by lazy(mode = LazyThreadSafetyMode.NONE) { LoadModuleProxy() }

    private val mCoroutineScope by lazy(mode = LazyThreadSafetyMode.NONE) { MainScope() }


    companion object {
        lateinit var appContext: Context
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        appContext = this
        mLoadModuleProxy.onAttachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        mLoadModuleProxy.onCreate(this)
        if(BuildConfig.DEBUG){
            initCrash()
            LogUtil.init(BuildConfig.DEBUG)
        }
        initDepends()
    }

    /**
     * 初始化第三方依赖
     */
    private fun initDepends() {
        // 开启一个 Default Coroutine 进行初始化不会立即使用的第三方
        mCoroutineScope.launch(Dispatchers.Default) {
            mLoadModuleProxy.initByBackstage()
        }

        // 前台初始化
        val allTimeMillis = measureTimeMillis {
            val depends = mLoadModuleProxy.initByFrontDesk()
            var dependInfo: String
            depends.forEach {
                val dependTimeMillis = measureTimeMillis { dependInfo = it() }
                Log.d("BaseApplication", "initDepends: $dependInfo : $dependTimeMillis ms")
            }
        }
        Log.d("BaseApplication", "初始化完成 $allTimeMillis ms")
    }

    override fun onTerminate() {
        super.onTerminate()
        mLoadModuleProxy.onTerminate(this)
        mCoroutineScope.cancel()
    }


    private  fun initCrash() {
        CaocConfig.Builder.create()
            .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //背景模式,开启沉浸式
            .enabled(true) //是否启动全局异常捕获
            .showErrorDetails(true) //是否显示错误详细信息
            .showRestartButton(true) //是否显示重启按钮
            .trackActivities(true) //是否跟踪Activity
            .minTimeBetweenCrashesMs(2000) //崩溃的间隔时间(毫秒)
            .errorDrawable(R.mipmap.base_ic_launcher) //错误图标
            .restartActivity(LauncherActivity::class.java) //重新启动后的activity
            //                .errorActivity(YourCustomErrorActivity.class) //崩溃后的错误activity
            //                .eventListener(new YourCustomEventListener()) //崩溃后的错误监听
            .apply()
    }


}