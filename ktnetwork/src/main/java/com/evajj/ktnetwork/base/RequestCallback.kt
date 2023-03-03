package com.evajj.ktnetwork.base

interface RequestCallback<T> {
    fun onSuccess(result: ResponseSuccess<T>)
    fun onError(error: ResponseError)
}
