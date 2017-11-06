package com.up.measurecopy.component.banner

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.up.measurecopy.R
import com.up.measurecopy.delete.adapter.ViewPagerAdapter
import com.up.measurecopy.util.CommonUtil

/**
 * Created by ywd on 2017/10/28.
 * 自定义banner
 */
class Banner(context: Context, attrs: AttributeSet?) : RelativeLayout(context, attrs), ViewPager.OnPageChangeListener {

    var indicatorContainer: LinearLayout = LinearLayout(context)
    var viewPager = BannerPager(context)
    var indicatorCount: Int = 0

    constructor(context: Context) : this(context, null)

    init {
        initBanner()
    }

    private fun initBanner() {
        viewPager.layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
//        viewPager.pageMargin = 0
//        viewPager.setPadding(0,0,0,0)
        addView(viewPager, 0)

        indicatorContainer.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        (indicatorContainer.layoutParams as LayoutParams).addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, TRUE)
        (indicatorContainer.layoutParams as LayoutParams).addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE)
        (indicatorContainer.layoutParams as LayoutParams).bottomMargin = CommonUtil.dp2px(context, 10)
        (indicatorContainer.layoutParams as LayoutParams).rightMargin = CommonUtil.dp2px(context, 20)
        addView(indicatorContainer, indicatorContainer.layoutParams)

    }

    fun <T> setBanners(bannerController: BannerController<T>, data: List<T>) {
        viewPager.adapter = ViewPagerAdapter(bannerController, data)
        setIndicator(data.size, R.drawable.banner_indicator_bg)
        viewPager.startTurning()
        indicatePage(0)
        viewPager.addOnPageChangeListener(this)
    }

    private fun setIndicator(count: Int = 3, res: Int = R.drawable.banner_indicator_bg) {
        indicatorCount = count
        for (i in 0 until count) {
            val imageView = ImageView(context)
            imageView.layoutParams = LinearLayout.LayoutParams(CommonUtil.dp2px(context, 10), CommonUtil.dp2px(context, 10))
            (imageView.layoutParams as LinearLayout.LayoutParams).rightMargin = CommonUtil.dp2px(context, 5)
//            imageView.setPadding(5,5,5,5)
//            imageView.background = resources.getDrawable(res,context.theme)
//            imageView.background = resources.getDrawable(res)
            imageView.setImageResource(res)
//            imageView.isActivated = true
//            if (i == count - 1) {
//                imageView.isActivated = true
//            }
            indicatorContainer.addView(imageView)
        }
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        indicatePage(position)
    }

    private fun indicatePage(position: Int) {
        indicatorContainer.getChildAt((position + indicatorCount - 1) % indicatorCount).isActivated = false
        indicatorContainer.getChildAt(position).isActivated = true
    }
}