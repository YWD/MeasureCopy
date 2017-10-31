package com.up.measurecopy.util

import android.content.Context
import java.security.MessageDigest
import java.util.*
import kotlin.experimental.and

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

        fun makeSign(paramsMap: MutableMap<String, Any>): String {
            paramsMap.put("key", Native.getSignKey())
            val sortedMap = sortMapByKey(paramsMap)
            val urlParams = getUrlParams(sortedMap)
            return MD5Encrypt(urlParams)
        }

        private fun getUrlParams(sortedMap: Map<String, Any>): String {
            val sb = StringBuilder()
            for ((key, value) in sortedMap) {
                sb.append("$key=$value&")
            }
            return sb.substring(0, sb.lastIndexOf("&"))
        }

        private fun sortMapByKey(paramsMap: MutableMap<String, Any>): Map<String, Any> {
            val sortedMap = TreeMap<String, Any>()
            sortedMap.putAll(paramsMap)
            return sortedMap
        }

        private fun MD5Encrypt(s: String): String {
            val hexDigits = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')
            try {
                val btInput = s.toByteArray()
                // 获得MD5摘要算法的 MessageDigest 对象
                val mdInst = MessageDigest.getInstance("MD5")
                // 使用指定的字节更新摘要
                mdInst.update(btInput)
                // 获得密文
                val md = mdInst.digest()
                // 把密文转换成十六进制的字符串形式
                val j = md.size
                val str = CharArray(j * 2)
                var k = 0
                for (i in 0 until j) {
                    val byte = md[i]
                    str[k++] = hexDigits[byte.toInt() ushr 4 and 0xf]
                    str[k++] = hexDigits[(byte and 0xf).toInt()]
                }
                return String(str).toLowerCase()
            } catch (e: Exception) {
                e.printStackTrace()
                return ""
            }

        }
    }
}