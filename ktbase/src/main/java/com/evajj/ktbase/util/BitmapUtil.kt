package com.evajj.ktbase.util.constant

import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream

/**
 * Author:wenjunjie
 * Date:2023/2/9
 * Time:下午6:21
 * Description:
 **/
    /**
     * 将bitmap转换成为Base64
     * @param bitmap
     * @return
     */
    fun bitmaptoString(bitmap: Bitmap, compressFormat: CompressFormat?, quality: Int): String? {
        // 将Bitmap转换成字符串
        var string: String? = null
        val bStream = ByteArrayOutputStream()
        bitmap.compress(compressFormat, quality, bStream)
        val bytes = bStream.toByteArray()
        string = Base64.encodeToString(bytes, Base64.DEFAULT)
        return string
    }

    /**
     * 将Base64转换成为Bitmap
     * @param string
     * @return
     */
    fun stringtoBitmap(string: String?): Bitmap? {
        //将字符串转换成Bitmap类型
        var bitmap: Bitmap? = null
        try {
            val bitmapArray: ByteArray
            bitmapArray = Base64.decode(string, Base64.DEFAULT)
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.size)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return bitmap
    }

