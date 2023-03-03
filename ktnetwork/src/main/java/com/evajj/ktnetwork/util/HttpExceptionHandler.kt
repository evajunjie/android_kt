package com.evajj.ktnetwork.util

import com.evajj.ktbase.exception.ExceptionHandle

/**
 * Author:wenjunjie
 * Date:2023/3/3
 * Time:上午10:35
 * Description:
 **/

fun Throwable.handleException()  = ExceptionHandle.handleException(this)