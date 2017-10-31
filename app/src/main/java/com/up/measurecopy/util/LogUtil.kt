package com.up.measurecopy.util

import android.util.Log
import com.up.measurecopy.BuildConfig
import java.util.*

/**
 * Created by ywd on 2017/10/30.
 * 日志工具类
 */
object LogUtil {

    private val isDebug = BuildConfig.DEBUG

    fun d(tag: String, message: String) {
        if (isDebug) {
            Log.d(tag,message)
        }
    }

    fun d(msg: String?) {
        if (isDebug) {
            Log.d(getTag(), buildMessage(msg ?: ""))
        }
    }

    private fun getTag(): String {
        val trace = Throwable().fillInStackTrace().stackTrace
        var callingClass = ""
        for (i in 2 until trace.size) {
            val clazz = trace[i].javaClass
            if (clazz != LogUtil::class.java) {
                callingClass = trace[i].className
                callingClass = callingClass.substring(callingClass.lastIndexOf('.') + 1)
                break
            }
        }
        return callingClass
    }

    private fun buildMessage(msg: String): String {
        val trace = Throwable().fillInStackTrace().stackTrace
        var caller = ""
        for (i in 2..trace.size - 1) {
            val clazz = trace[i].javaClass
            if (clazz != LogUtil::class.java) {
                caller = trace[i].methodName
                break
            }
        }
        return String.format(Locale.US, "[%d] %s: %s", Thread.currentThread().id, caller, msg)
    }
}