package com.up.measurecopy.util

import android.content.Context

/**
 * Created by ywd on 2017/10/27.
 * 通用工具类
 */
class CommonUtil {

    companion object {
        fun px2dp(context: Context, px: Int): Int {
            val density = context.resources.displayMetrics.density
            return (px / density + 0.5f).toInt()
        }

        fun dp2px(context: Context, dp: Int): Int {
            val density = context.resources.displayMetrics.density
            return (dp * density + 0.5f).toInt()
        }
    }
}