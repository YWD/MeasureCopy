package com.up.measurecopy.main

import android.app.Activity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.up.measurecopy.R
import com.up.measurecopy.util.CommonUtil
import com.up.measurecopy.view.banner.Banner
import com.up.measurecopy.view.banner.BannerController
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_head.*
import kotlinx.android.synthetic.main.home_content.*

/**
 * Created by ywd on 2017/10/26.
 * App 首页
 */
class HomeActivity : Activity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initView()
        setListener()
    }

    private fun setListener() {
        head_left_iv1.setOnClickListener(this)
        head_right_iv1.setOnClickListener(this)
        head_right_iv2.setOnClickListener(this)
    }

    private fun initView() {

        head_title.text = getString(R.string.app_name)
        head_left_iv1.setImageResource(R.drawable.icon_gengduo)
        head_left_iv1.visibility = View.VISIBLE
        head_right_iv1.setImageResource(R.drawable.icon_kefu)
        head_right_iv1.visibility = View.VISIBLE
        head_right_iv2.setImageResource(R.drawable.icon_lanya)
        head_right_iv2.visibility = View.VISIBLE

        // 自定义banner
        val banner = Banner(this)
        banner.layoutParams = ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, CommonUtil.dp2px(this, 230))
        content_ll.addView(banner, 0)

        val bannerPicRes: List<Int> = mutableListOf(R.drawable.banner1, R.drawable.banner2, R.drawable.banner2)
        banner.setBanners(HomeBannerController(),bannerPicRes)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.head_left_iv1 ->{
                drawer.openDrawer(Gravity.START)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (drawer.isDrawerOpen(Gravity.START)) {
            drawer.closeDrawer(Gravity.START)
        }
    }

    class HomeBannerController : BannerController<Int> {
        override fun getLoopView(container:ViewGroup,data: Int): View {
            val imageView = ImageView(container.context)
            imageView.layoutParams = container.layoutParams
            imageView.layoutParams.width = ViewPager.LayoutParams.MATCH_PARENT
            imageView.layoutParams.height = ViewPager.LayoutParams.MATCH_PARENT
            imageView.scaleType = ImageView.ScaleType.FIT_XY    //图片比例导致page间有间距
            imageView.setImageResource(data)
            return imageView
        }

    }
}