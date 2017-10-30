package com.up.measurecopy.view.banner

import android.content.Context
import android.widget.Scroller

/**
 * Created by ywd on 2017/10/30.
 * PagerScroller
 */
class PagerScroller(context: Context, private val time: Int): Scroller(context) {

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int) {
        super.startScroll(startX, startY, dx, dy)
    }

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
        super.startScroll(startX, startY, dx, dy, time)
    }
}