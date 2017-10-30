package com.up.measurecopy.util

import android.util.Log
import com.up.measurecopy.BuildConfig

/**
 * Created by ywd on 2017/10/30.
 * 日志工具类
 */
object LogUtil {

    fun d(tag: String, message: String) {
        if (BuildConfig.DEBUG) {
            Log.d(tag,message)
        }
    }
}