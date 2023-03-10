package com.evajj.ktnetwork.viewmode

import android.app.Application
import com.evajj.ktbase.base.BaseModel
import com.evajj.ktbase.base.BaseViewModel
import com.evajj.ktbase.event.SingleLiveEvent
import com.evajj.ktnetwork.base.RequestCallback

/**
 * Author:wenjunjie
 * Date:2023/3/10
 * Time:下午2:41
 * Description:针对网络请求添加loading环节
 **/
open class LoadViewModel<M : BaseModel>(application: Application) :BaseViewModel<M>(application) {

    val  isLoading = SingleLiveEvent<Boolean>();
    val  error = SingleLiveEvent<Boolean>();


    protected fun<Response> requestWithLoading(requestCallback: RequestCallback<Response>.() ->Unit){
        request<Response> {
            val requestCallbackDelegate = RequestCallback<Response>()
            onRequest{
                requestCallbackDelegate.apply(requestCallback).request.invoke()
            }

            onStart{
                showLoading(true)
                requestCallbackDelegate.apply(requestCallback).onStart?.invoke()

            }
            onTransform{
                requestCallbackDelegate.apply(requestCallback).onTransform.invoke(this)
            }
            onError{
                showLoading(false)
                showError()
                requestCallbackDelegate.apply(requestCallback).onError?.invoke(this)
            }
            onResponse{
                requestCallbackDelegate.apply(requestCallback).onResponse?.invoke(this)
            }
            onFinally{
                showLoading(false)
                requestCallbackDelegate.apply(requestCallback).onFinally?.invoke()

            }



        }

    }

    open fun showLoading(show: Boolean ){
        isLoading.value =  show
    }

    open fun showError(){
        error.postCall()
    }

}