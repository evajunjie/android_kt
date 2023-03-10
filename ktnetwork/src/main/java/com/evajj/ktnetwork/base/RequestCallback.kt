package com.evajj.ktnetwork.base

import com.evajj.ktbase.exception.ExceptionHandle.handleException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Author:wenjunjie
 * Date:2023/3/10
 * Time:上午11:21
 * Description:
 **/
class RequestCallback<Response> {
    internal lateinit var request: suspend () -> Flow<Response>

    internal var onStart: (() -> Boolean?)? = null

    internal var onResponse: (ResponseSuccess<Response>.() -> Unit)? = null

    internal var onError: (ResponseError.() -> Boolean?)? = null

    internal var onFinally: (() -> Boolean?)? = null

    internal var onTransform: Response.() -> Response = {this}


    fun onStart(onStart: (() -> Boolean?)?) {
        this.onStart = onStart
    }

    infix fun onRequest(request: suspend () -> Flow<Response>) {
        this.request = request
    }

    infix fun onResponse(onResponse: (ResponseSuccess<Response>.() -> Unit)?) {
        this.onResponse = onResponse
    }

    infix fun onError(onError: (ResponseError.() -> Boolean?)?) {
        this.onError = onError
    }

    infix fun onFinally(onFinally: (() -> Boolean?)?) {
        this.onFinally = onFinally
    }

    infix fun onTransform(onTransform: Response.() -> Response){
        this.onTransform = onTransform
    }


    fun collect(viewModelScope: CoroutineScope) {
        viewModelScope.launch(context = Dispatchers.Main) {
            request.invoke().flowOn(Dispatchers.IO)
                .onStart {
                    onStart?.invoke()
                }.transform {
                    emit(onTransform.invoke(it))
                }.catch {
                    it.printStackTrace()
                    onError?.invoke(ResponseError(handleException(it)))
                }.onCompletion {
                   onFinally?.invoke()
                }.collect {
                    onResponse?.invoke(ResponseSuccess(it))
                }


        }
    }
}