package com.evajj.ktbase.bus

import androidx.lifecycle.*

/**
 * Author:wenjunjie
 * Date:2023/2/21
 * Time:下午4:06
 * Description:
 **/
internal class BusLifecycleObserver<T>(private val observer: Observer<in T>, private val owner: LifecycleOwner, private val liveData: BusLiveData<T>)
    : BaseBusObserverWrapper<T>(observer,liveData), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(){
        liveData.removeObserver(observer)
        owner.lifecycle.removeObserver(this)
    }
}
