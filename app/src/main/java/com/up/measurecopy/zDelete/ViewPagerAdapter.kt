package com.up.measurecopy.delete.adapter

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.up.measurecopy.component.banner.BannerController

/**
 * Created by ywd on 2017/10/27.
 * viewpager adapter
 */
class ViewPagerAdapter<T>(private val bannerController: BannerController<T>, private val mData: List<T>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val pageView = getPageView(container, position)
        container.addView(pageView)
        return pageView
    }

    private fun getPageView(container: ViewGroup, position: Int): View = bannerController.getLoopView(container, mData[position])

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container?.removeView(`object` as View?)
    }

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean = view === `object`

    override fun getCount(): Int = mData.size

    override fun finishUpdate(container: ViewGroup?) {
        super.finishUpdate(container)
    }
}