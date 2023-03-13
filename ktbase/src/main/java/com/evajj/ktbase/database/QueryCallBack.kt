package com.evajj.ktbase.database


import com.evajj.ktbase.R
import com.evajj.ktbase.exception.ExceptionHandle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Author:wenjunjie
 * Date:2023/3/13
 * Time:下午3:47
 * Description:
 **/
class QueryCallBack<Result> {
    internal lateinit var query: suspend () -> Flow<Result>

    internal var onStart: (() -> Boolean?)? = null

    internal var onResult: (Result.() -> Unit)? = null

    internal var onError: (Throwable.() -> Boolean?)? = null

    internal var onFinally: (() -> Boolean?)? = null

    internal var onTransform: Result.() -> Result = {this}


    fun onStart(onStart: (() -> Boolean?)?) {
        this.onStart = onStart
    }

    infix fun onQuery(query: suspend () -> Flow<Result>) {
        this.query = query
    }

    infix fun onResult(onResult: (Result.() -> Unit)?) {
        this.onResult = onResult
    }

    infix fun onError(onError: (Throwable.() -> Boolean?)?) {
        this.onError = onError
    }

    infix fun onFinally(onFinally: (() -> Boolean?)?) {
        this.onFinally = onFinally
    }

    infix fun onTransform(onTransform: Result.() -> Result){
        this.onTransform = onTransform
    }


    fun collect(viewModelScope: CoroutineScope) {
        try {
            viewModelScope.launch(context = Dispatchers.Main) {
                query.invoke().flowOn(Dispatchers.IO)
                    .onStart {
                        onStart?.invoke()
                    }.
                    transform {
                        emit(onTransform.invoke(it))
                    }.catch {
                        it.printStackTrace()
                        onError?.invoke(it)
                    }.onCompletion {
                        onFinally?.invoke()
                    }.collect {
                        onResult?.invoke(it)
                    }

            }

        }catch (e:Exception){
            e.printStackTrace()
            onError?.invoke(e)
        }

    }
}