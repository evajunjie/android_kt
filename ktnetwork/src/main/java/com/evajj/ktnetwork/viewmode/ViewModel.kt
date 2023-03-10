package com.evajj.ktnetwork.viewmode

import androidx.lifecycle.viewModelScope
import com.evajj.ktbase.base.BaseViewModel
import com.evajj.ktnetwork.base.RequestCallback

/**
 * Author:wenjunjie
 * Date:2023/3/10
 * Time:下午2:21
 * Description:针对网络请求进行封装
 **/
inline fun <Response> BaseViewModel<*>.request(requestCallback: RequestCallback<Response>.() -> Unit) {
    RequestCallback<Response>().apply(requestCallback).collect(viewModelScope = viewModelScope)
}

