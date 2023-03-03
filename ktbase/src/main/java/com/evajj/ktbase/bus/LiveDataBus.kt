package com.evajj.ktbase.bus

/**
 * Author:wenjunjie
 * Date:2023/2/21
 * Time:下午2:34
 * Description:
 **/
object LiveDataBus {
    internal val mBusMap : MutableMap<String, BusLiveData<*>> by lazy {
        mutableMapOf<String, BusLiveData<*>>()
    }

    fun <T> getChannel(key: String) : BusLiveData<T> {
        return mBusMap.getOrPut(key){
            BusLiveData<T>(key)
        } as BusLiveData<T>
    }

    @Synchronized
    fun <T> getChannelSync(key: String) : BusLiveData<T> {
        return mBusMap.getOrPut(key){
            BusLiveData<T>(key)
        } as BusLiveData<T>
    }


}