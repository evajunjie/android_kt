package com.evajj.module_home.data.entity

import java.io.Serializable

/**
 * Author:wenjunjie
 * Date:2023/3/10
 * Time:下午3:38
 * Description:
 **/
data class WanResponse<out T>(val errorCode: Int, val errorMsg: String, val data: T): Serializable
