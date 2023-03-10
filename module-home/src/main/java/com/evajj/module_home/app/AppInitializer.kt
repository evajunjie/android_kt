package com.evajj.module_home.app

import android.app.Application
import android.content.Context
import android.content.IntentFilter
import com.evajj.ktbase.app.ApplicationLifecycle
import com.evajj.ktbase.util.LogUtil
import com.evajj.ktnetwork.custom.HomeNetWorkApi

import com.google.auto.service.AutoService

/**
 * Author:wenjunjie
 * Date:2023/3/9
 * Time:下午1:56
 * Description:
 **/
@AutoService(ApplicationLifecycle::class)
class AppInitializer : ApplicationLifecycle {
    companion object{
        lateinit var mContext: Context
    }

    override fun onAttachBaseContext(context: Context) {
        LogUtil.d("onAttachBaseContext")
        mContext = context
    }

    override fun onCreate(application: Application) {

    }

    override fun onTerminate(application: Application) {
    }

    override fun initByFrontDesk(): MutableList<() -> String> {
        var initList = mutableListOf<() -> String>()
        initList.add { initNetWorkInfo()  }
        return initList
    }

    override fun initByBackstage() {
    }

    private fun initNetWorkInfo() : String{
        HomeNetWorkApi.init(HomeNetworkRequestInfo(mContext ))
        return "initNetWorkInfo success"
    }


}