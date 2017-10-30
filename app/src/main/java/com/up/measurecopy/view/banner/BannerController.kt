package com.up.measurecopy.view.banner

import android.content.Context
import android.view.View
import android.view.ViewGroup

/**
 * Created by ywd on 2017/10/30.
 * Banner 控制器
 */

interface BannerController<in T> {
    fun getLoopView(container:ViewGroup,data : T): View
//    fun UpdateUI(loopView: View, data: T): Void
}
