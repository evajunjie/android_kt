package com.evajj.ktbase.event

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Author:wenjunjie
 * Date:2023/2/7
 * Time:下午3:24
 * Description:
 **/
open class SingleLiveEvent<T> : MutableLiveData<T>() {
      private val TAG = SingleLiveEvent::class.java.name

      private val mPending : AtomicBoolean  = AtomicBoolean(false);

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) {
            Log.w(
                TAG,
                "Multiple observers registered but only one will be notified of changes."
            )
        }

        // Observe the internal MutableLiveData
        super.observe(owner) { t ->
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        }
    }

    @MainThread
    override fun setValue(t: T?) {
        mPending.set(true)
        super.setValue(t)
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    fun call() {
       value = null
    }

    fun postCall(){
        postValue(null)
    }
}