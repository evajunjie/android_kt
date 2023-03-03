package com.evajj.ktbase.bus

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.evajj.ktbase.util.LogUtil
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Author:wenjunjie
 * Date:2023/2/21
 * Time:下午4:10
 * Description:
 **/
internal open class BaseBusObserverWrapper<T>(
    private val mObserver: Observer<in T>,
    private val mBusLiveData: BusLiveData<T>
) : Observer<T> {

    val mPending: AtomicBoolean = AtomicBoolean(false);

    private val TAG = "BaseBusObserverWrapper"

    override fun onChanged(t: T?) {
        LogUtil.d(TAG, "msg receiver = " + t.toString())

        try {
            if (mPending.compareAndSet(true, false)) {
                mObserver.onChanged(t)
            }
        } catch (e: Exception) {
            LogUtil.e(TAG, "error on Observer onChanged() = " + e.message)
        }
    }

    open fun isAttachedTo(owner: LifecycleOwner) = false

}
