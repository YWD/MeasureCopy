package com.up.measurecopy.util

import android.content.Context
import android.telephony.TelephonyManager
import com.up.measurecopy.manager.PreferenceManager
import java.util.*

/**
 * Created by ywd on 2017/11/2.
 * 设备相关工具类
 */
object DeviceUtil {

    fun getDeviceId(context: Context): String {
        val telephoneManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return telephoneManager.deviceId ?: getSavedDeviceId()
    }

    private fun getSavedDeviceId(): String {
        val deviceId = PreferenceManager.getString(PreferenceManager.DEVICE_ID)
        return deviceId?: getGenerateDeviceId()
    }

    private fun getGenerateDeviceId(): String = UUID.randomUUID().toString().replace("-", "").toLowerCase().substring(0,32)
}