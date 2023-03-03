package com.evajj.ktbase.bus

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.evajj.ktbase.util.LogUtil

/**
 * Author:wenjunjie
 * Date:2023/2/21
 * Time:下午4:03
 * Description:
 **/
class BusLiveData<T> (private val mKey:String) : MutableLiveData<T>() {

    private val TAG = "BusLiveData"

    private val mObserverMap: MutableMap<Observer<in T>, BaseBusObserverWrapper<T>> = mutableMapOf()

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        val exist = mObserverMap.getOrPut(observer) {
            BusLifecycleObserver(observer, owner, this).apply {
                mObserverMap[observer] = this
                owner.lifecycle.addObserver(this)
            }
        }
        super.observe(owner, exist)
        Log.d(TAG,"observe() called with: owner = [$owner], observer = [$observer]")
    }

    @MainThread
    override fun observeForever(observer: Observer<in T>) {
        super.observeForever(observer)
        val exist = mObserverMap.getOrPut(observer ){
            BusAlwaysActiveObserver(observer,this).apply {
                mObserverMap[observer] = this
            }
        }
        super.observeForever(exist)
        LogUtil.d(TAG, "observeForever() called with: observer = [$observer]")
    }

    @MainThread
    fun observeSticky(owner: LifecycleOwner, observer: Observer<T>) {
        super.observe(owner, observer)
        LogUtil.d(TAG, "observeSticky() called with: owner = [$owner], observer = [$observer]")
    }

    @MainThread
    fun observeStickyForever(observer: Observer<T>){
        super.observeForever(observer)
        LogUtil.d(TAG, "observeStickyForever() called with: observer = [$observer]")
    }

    @MainThread
    override fun removeObserver(observer: Observer<in T>) {
        val exist = mObserverMap.remove(observer) ?: observer
        super.removeObserver(exist as Observer<in T>)
        LogUtil.d(TAG, "removeObserver() called with: observer = [$observer]")
    }

    @MainThread
    override fun removeObservers(owner: LifecycleOwner) {
        mObserverMap.iterator().forEach {
            if (it.value.isAttachedTo(owner)) {
                mObserverMap.remove(it.key)
            }
        }
        super.removeObservers(owner)
        LogUtil.d(TAG, "removeObservers() called with: owner = [$owner]")
    }

    @MainThread
    override fun setValue(t: T?) {
        mObserverMap.iterator().forEach {
            it.value.mPending.set(true)
        }
        super.setValue(t)
    }


    @MainThread
    override fun onInactive() {
        super.onInactive()
        if (!hasObservers()) {
            // 当 LiveData 没有活跃的观察者时，可以移除相关的实例
            LiveDataBus.mBusMap.remove(mKey)
        }
        LogUtil.d(TAG, "onInactive() called")
    }


}


