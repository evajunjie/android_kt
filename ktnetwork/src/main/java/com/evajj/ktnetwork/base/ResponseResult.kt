package com.evajj.ktnetwork.base

/**
 * Author:wenjunjie
 * Date:2023/3/3
 * Time:上午10:15
 * Description:
 **/
sealed class ResponseResult
data class ResponseSuccess<T>(val data: T?) : ResponseResult()
data class ResponseError(val throwable: Throwable?) : ResponseResult()