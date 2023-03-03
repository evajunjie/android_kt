package com.evajj.ktbase.util.constant

import androidx.annotation.IntDef

/**
 * Author:wenjunjie
 * Date:2023/2/9
 * Time:下午5:52
 * Description:
 **/

/**
 * Byte与Byte的倍数
 */
const val BYTE = 1

/**
 * KB与Byte的倍数
 */
const val KB = 1024

/**
 * MB与Byte的倍数
 */
const val MB = 1048576

/**
 * GB与Byte的倍数
 */
const val GB = 1073741824

@IntDef(BYTE, KB, MB, GB)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class MemoryUnit