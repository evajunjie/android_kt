package com.evajj.ktbase.bus

import androidx.lifecycle.Observer

/**
 * Author:wenjunjie
 * Date:2023/2/21
 * Time:下午4:50
 * Description:
 **/
internal class BusAlwaysActiveObserver<T>(private val mObserver: Observer<in T>, private val mBusLiveData: BusLiveData<T>)
    : BaseBusObserverWrapper<T>(mObserver, mBusLiveData)
