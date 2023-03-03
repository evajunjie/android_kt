package com.evajj.ktnetwork.weaknetwork

import android.os.SystemClock
import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.*
import java.io.IOException

/**
 * Author:wenjunjie
 * Date:2023/3/2
 * Time:下午5:50
 * Description:
 **/
class SpeedLimitResponseBody(speedByte : Long = 0 , private var mResponseBody: ResponseBody) : ResponseBody() {

    private val TAG = "SpeedLimitResponseBody"

    /**
     * 限速字节
     */
    private var _SpeedByte  =  speedByte
    get() =  field * 1024L
    private val mBufferedSource: BufferedSource  by lazy{
        source(mResponseBody.source()).buffer()
    }



    override fun contentType(): MediaType? {
        return mResponseBody!!.contentType()
    }

    override fun contentLength(): Long {
        return mResponseBody!!.contentLength()
    }

    override fun source(): BufferedSource {
        return  mBufferedSource
    }

    private fun source(source: Source): Source {
        return object : ForwardingSource(source) {
            /**
             * 如果小于1s 会重置
             */
            private var cacheTotalBytesRead: Long = 0

            /**
             * 分片读取1024个字节开始时间 小于1s会重置
             */
            private var cacheStartTime: Long = 0

            @Throws(IOException::class)
            override fun read(sink: Buffer, byteCount: Long): Long {
                if (cacheStartTime == 0L) {
                    //记录 开机到当前的时间总数
                    cacheStartTime = SystemClock.uptimeMillis()
                }

                //byteCount默认8K  指定到读1K
                val bytesRead = super.read(sink.buffer, 1024L)
                // -1代表已经读取完毕
                if (bytesRead == -1L) {
                    return bytesRead
                }
                //读这个数据 总读取字节数
                cacheTotalBytesRead = cacheTotalBytesRead + bytesRead
                /**
                 * 判断当前请求累计消耗的时间 即相当于读取1024个字节所需要的时间
                 */
                val costTime = SystemClock.uptimeMillis() - cacheStartTime

                //如果每次分片读取时间小于1s sleep 延迟时间
                if (costTime <= 1000L) {
                    if (cacheTotalBytesRead >= _SpeedByte) {
                        val sleep = 1000L - costTime
                        SystemClock.sleep(sleep)
                        //重置计算
                        cacheStartTime = 0L
                        cacheTotalBytesRead = 0L
                    }
                }
                //如果读取超过1s，表示本来就很慢了，后面就不会延迟读取
                return bytesRead
            }
        }
    }
}