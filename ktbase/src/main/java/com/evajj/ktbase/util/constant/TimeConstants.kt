package com.evajj.ktbase.util.constant

import androidx.annotation.IntDef
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * Author:wenjunjie
 * Date:2023/2/9
 * Time:下午5:59
 * Description:
 **/

/**
 * 毫秒与毫秒的倍数
 */
const val MSEC = 1

/**
 * 秒与毫秒的倍数
 */
const val SEC = 1000

/**
 * 分与毫秒的倍数
 */
const val MIN = 60000

/**
 * 时与毫秒的倍数
 */
const val HOUR = 3600000

/**
 * 天与毫秒的倍数
 */
const val DAY = 86400000

@IntDef(MSEC, SEC, MIN, HOUR, DAY)
@Retention(RetentionPolicy.SOURCE)
annotation class TimeUnit