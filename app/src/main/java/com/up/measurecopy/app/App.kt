package com.up.measurecopy.app

import android.app.Application

/**
 * Created by ywd on 2017/11/2.
 * 应用application
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    companion object {
        var instance: Application? = null
    }
}