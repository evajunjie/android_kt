package com.evajj.module_home.app

import android.app.Application
import com.evajj.ktbase.app.BaseApplication
import dagger.hilt.android.HiltAndroidApp


/**
 * Author:wenjunjie
 * Date:2023/2/21
 * Time:下午6:32
 * Description:
 **/
@HiltAndroidApp
open class HomeApp : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
    }
}