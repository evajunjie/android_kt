package com.evajj.ktnetwork.weaknetwork

import okio.*
import java.io.IOException
import java.io.OutputStream
import java.nio.ByteBuffer
import java.nio.charset.Charset

/**
 * Author:wenjunjie
 * Date:2023/3/2
 * Time:下午4:35
 * Description:
 **/
class ByteCountBufferedSink(private var byteCount :Long = 0, private var originalSink :Sink?,

                            ) : BufferedSink {
    lateinit var mDelegate : BufferedSink
    init {
        mDelegate = originalSink!!.buffer()
    }

    override val buffer: Buffer
    get() = mDelegate.buffer

    @Deprecated(
        "moved to val: use getBuffer() instead",
        replaceWith = ReplaceWith("buffer"),
        level = DeprecationLevel.WARNING
    )
    override fun buffer(): Buffer {
       return  mDelegate.buffer
    }


    override fun writeAll(source: Source): Long {
        requireNotNull(source) { "source == null" }
        var totalBytesRead: Long = 0
        var readCount: Long
        while (source.read(buffer, byteCount).also { readCount = it } != -1L) {
            totalBytesRead += readCount
            emitCompleteSegments()
        }
        return totalBytesRead
    }

    @Throws(IOException::class)
    override fun write(source: ByteArray, offset: Int, byteCount: Int): BufferedSink {
        check(isOpen) { "closed" }
        //计算出要写入的次数
        val count = Math.ceil(source.size.toDouble() / this.byteCount).toLong()
        for (i in 0 until count) {
            //让每次写入的字节数精确到mByteCount 分多次写入
            val newOffset = i * this.byteCount
            val writeByteCount = Math.min(this.byteCount, source.size - newOffset)
            buffer().write(source, newOffset.toInt(), writeByteCount.toInt())
            emitCompleteSegments()
        }
        return this
    }

    @Throws(IOException::class)
    override fun emitCompleteSegments(): BufferedSink {
        val buffer = buffer()
        originalSink!!.write(buffer, buffer.size)
        return this
    }


    @Throws(IOException::class)
    override fun write(byteString: ByteString): BufferedSink {
        return mDelegate!!.write(byteString!!)
    }

    override fun write(byteString: ByteString, offset: Int, byteCount: Int): BufferedSink {
        return mDelegate!!.write(byteString!!,offset,byteCount)
    }

    @Throws(IOException::class)
    override fun write(source: ByteArray): BufferedSink {
        return mDelegate!!.write(source!!)
    }

    @Throws(IOException::class)
    override fun write(source: Source, byteCount: Long): BufferedSink {
        return mDelegate!!.write(source!!, byteCount)
    }

    @Throws(IOException::class)
    override fun writeUtf8(string: String): BufferedSink {
        return mDelegate!!.writeUtf8(string!!)
    }

    @Throws(IOException::class)
    override fun writeUtf8(string: String, beginIndex: Int, endIndex: Int): BufferedSink {
        return mDelegate!!.writeUtf8(string!!, beginIndex, endIndex)
    }

    @Throws(IOException::class)
    override fun writeUtf8CodePoint(codePoint: Int): BufferedSink {
        return mDelegate!!.writeUtf8CodePoint(codePoint)
    }

    @Throws(IOException::class)
    override fun writeString(string: String, charset: Charset): BufferedSink {
        return mDelegate!!.writeString(string!!, charset!!)
    }

    @Throws(IOException::class)
    override fun writeString(
        string: String,
        beginIndex: Int,
        endIndex: Int,
        charset: Charset
    ): BufferedSink {
        return mDelegate!!.writeString(string!!, beginIndex, endIndex, charset!!)
    }

    @Throws(IOException::class)
    override fun writeByte(b: Int): BufferedSink {
        return mDelegate!!.writeByte(b)
    }

    @Throws(IOException::class)
    override fun writeShort(s: Int): BufferedSink {
        return mDelegate!!.writeShort(s)
    }

    @Throws(IOException::class)
    override fun writeShortLe(s: Int): BufferedSink {
        return mDelegate!!.writeShortLe(s)
    }

    @Throws(IOException::class)
    override fun writeInt(i: Int): BufferedSink {
        return mDelegate!!.writeInt(i)
    }

    @Throws(IOException::class)
    override fun writeIntLe(i: Int): BufferedSink {
        return mDelegate!!.writeIntLe(i)
    }

    @Throws(IOException::class)
    override fun writeLong(v: Long): BufferedSink {
        return mDelegate!!.writeLong(v)
    }

    @Throws(IOException::class)
    override fun writeLongLe(v: Long): BufferedSink {
        return mDelegate!!.writeLongLe(v)
    }

    @Throws(IOException::class)
    override fun writeDecimalLong(v: Long): BufferedSink {
        return mDelegate!!.writeDecimalLong(v)
    }

    @Throws(IOException::class)
    override fun writeHexadecimalUnsignedLong(v: Long): BufferedSink {
        return mDelegate!!.writeHexadecimalUnsignedLong(v)
    }

    @Throws(IOException::class)
    override fun flush() {
        mDelegate!!.flush()
    }

    @Throws(IOException::class)
    override fun emit(): BufferedSink {
        return mDelegate!!.emit()
    }

    override fun outputStream(): OutputStream {
        return mDelegate!!.outputStream()
    }

    @Throws(IOException::class)
    override fun write(src: ByteBuffer?): Int {
        return mDelegate!!.write(src)
    }

    override fun isOpen(): Boolean {
        return mDelegate!!.isOpen
    }

    @Throws(IOException::class)
    override fun write(source: Buffer, byteCount: Long) {
        mDelegate!!.write(source!!, byteCount)
    }

    override fun timeout(): Timeout {
        return mDelegate!!.timeout()
    }

    @Throws(IOException::class)
    override fun close() {
        mDelegate!!.close()
    }
}

