package com.evajj.ktbase.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Author:wenjunjie
 * Date:2023/2/6
 * Time:下午5:55
 * Description:
 **/
open class BaseModel : IModel {
    override fun onCleared() {

    }

    /**
     * 发起请求封装
     * 该方法将flow的执行切换至IO线程
     *
     * @param requestBlock 请求的整体逻辑
     * @return Flow<T> @BuilderInference block: suspend FlowCollector<T>.() -> Unit
     */
    protected fun <T> request(requestBlock: suspend FlowCollector<T>.() -> Unit): Flow<T> {
        return flow( requestBlock).flowOn(Dispatchers.IO)
    }


}