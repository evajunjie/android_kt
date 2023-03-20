package com.evajj.ktcommon

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.evajj.ktbase.app.ApplicationLifecycle
import com.evajj.ktbase.app.BaseApplication
import com.evajj.ktbase.util.SpUtils
import com.evajj.ktcommon.util.ProcessUtils
import com.google.auto.service.AutoService

/**
 * Author:wenjunjie
 * Date:2023/3/20
 * Time:下午5:22
 * Description:
 **/
@AutoService(ApplicationLifecycle::class)
class CommonApplication : ApplicationLifecycle {

    companion object {
        // 全局CommonApplication
        @SuppressLint("StaticFieldLeak")
        lateinit var mCommonApplication: CommonApplication
    }

    /**
     * 同[Application.attachBaseContext]
     * @param context Context
     */
    override fun onAttachBaseContext(context: Context) {
        mCommonApplication = this
    }

    /**
     * 同[Application.onCreate]
     * @param application Application
     */
    override fun onCreate(application: Application) {}

    /**
     * 同[Application.onTerminate]
     * @param application Application
     */
    override fun onTerminate(application: Application) {}

    /**
     * 主线程前台初始化
     * @return MutableList<() -> String> 初始化方法集合
     */
    override fun initByFrontDesk(): MutableList<() -> String> {
        val list = mutableListOf<() -> String>()
        // 以下只需要在主进程当中初始化 按需要调整
        if (ProcessUtils.isMainProcess(BaseApplication.appContext)) {
            list.add { initMMKV() }
            list.add { initARouter() }
            list.add {
                initNetworkStateClient()
            }
        }
        return list
    }

    /**
     * 不需要立即初始化的放在这里进行后台初始化
     */
    override fun initByBackstage() {
    }

    /**
     * 初始化网络状态监听客户端
     * @return Unit
     */
    private fun initNetworkStateClient(): String {
        //NetworkStateClient.register()
        return "NetworkStateClient -->> init complete"
    }


    /**
     * 腾讯 MMKV 初始化
     */
    private fun initMMKV(): String {
        val result = SpUtils.initMMKV(BaseApplication.appContext)
        return "MMKV -->> $result"
    }

    /**
     * 阿里路由 ARouter 初始化
     */
    private fun initARouter(): String {
        // 测试环境下打开ARouter的日志和调试模式 正式环境需要关闭
        if (BuildConfig.DEBUG ) {
            ARouter.openLog()     // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(BaseApplication.appContext as Application)
        return "ARouter -->> init complete"
    }


}