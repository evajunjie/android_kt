package com.evajj.ktbase.util

import java.io.Closeable
import java.io.IOException

/**
 * Author:wenjunjie
 * Date:2023/2/9
 * Time:下午6:26
 * Description:
 **/


/**
 * 关闭IO
 *
 * @param closeables closeables
 */

fun closeIO(vararg closeables: Closeable?) {
    if (closeables == null) return
    for (closeable in closeables) {
        if (closeable != null) {
            try {
                closeable.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}

/**
 * 安静关闭IO
 *
 * @param closeables closeables
 */
fun closeIOQuietly(vararg closeables: Closeable?) {
    if (closeables == null) return
    for (closeable in closeables) {
        if (closeable != null) {
            try {
                closeable.close()
            } catch (ignored: IOException) {
            }
        }
    }
}
