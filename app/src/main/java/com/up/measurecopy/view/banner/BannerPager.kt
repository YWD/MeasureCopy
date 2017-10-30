package com.up.measurecopy.view.banner

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.MotionEvent

/**
 * Created by ywd on 2017/10/30.
 * BannerPager
 */
class BannerPager(context: Context) : ViewPager(context) {

    private var adSwitchTask: Runnable? = null
    private var pageCount: Int = 0
    private var turningGap:Int = 0
    private val mHandler: android.os.Handler = android.os.Handler()

    init {
        setScrollTime()
    }

    override fun setAdapter(adapter: PagerAdapter) {
        super.setAdapter(adapter)
        pageCount = adapter.count
    }

    private fun setScrollTime(time: Int = 1000) {
        val mScroller = ViewPager::class.java.getDeclaredField("mScroller")
        mScroller.isAccessible = true
        mScroller.set(this, PagerScroller(context, time))
    }

    fun startTurning(turningGap: Int = 3000) {
        stopTurning()
        this.turningGap = turningGap
        adSwitchTask = Runnable {
            val currentItem = currentItem
            val nextItem = (currentItem + 1) % pageCount
            this.setCurrentItem(nextItem, true)
            mHandler.postDelayed(adSwitchTask, turningGap.toLong())
        }

        mHandler.postDelayed(adSwitchTask, turningGap.toLong())
    }

    private fun stopTurning() {
        mHandler.removeCallbacks(adSwitchTask)
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                stopTurning()
            }
            MotionEvent.ACTION_UP,MotionEvent.ACTION_CANCEL,MotionEvent.ACTION_OUTSIDE -> {
                startTurning(turningGap)
            }
        }
        return super.onTouchEvent(ev)
    }
}