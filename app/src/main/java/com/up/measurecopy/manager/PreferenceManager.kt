package com.up.measurecopy.manager

import android.content.SharedPreferences
import com.up.measurecopy.app.App

/**
 * Created by ywd on 2017/10/30.
 * SharedPreferences 管理
 */
object PreferenceManager {

    val DEVICE_ID = "device_id"

    var preferences: SharedPreferences? = null

    init {
        if (preferences === null) {
            preferences = android.preference.PreferenceManager.getDefaultSharedPreferences(App.instance)
        }
    }

    fun getString(key: String): String? = preferences?.getString(key, null)
    fun getString(key: String, defValue: String?): String? = preferences?.getString(key, defValue)

    fun putString(key: String, value: String) {
        preferences?.edit()?.putString(key, value)?.apply()
    }


}