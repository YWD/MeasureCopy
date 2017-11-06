package com.up.measurecopy.main.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.bumptech.glide.Glide
import com.up.measurecopy.R
import com.up.measurecopy.model.HomeConfig
import com.up.measurecopy.util.CommonUtil
import com.up.measurecopy.component.banner.Banner
import com.up.measurecopy.component.banner.BannerController
import com.up.measurecopy.main.apartment.ApartmentListAct
import com.up.measurecopy.main.login.LoginAct
import com.up.measurecopy.main.sketch.SketchAct
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_head.*
import kotlinx.android.synthetic.main.home_content.*
import kotlinx.android.synthetic.main.home_left_menu.*

/**
 * Created by ywd on 2017/10/26.
 * App 首页
 */
class HomeActivity : Activity(), View.OnClickListener, HomePresenter.DataT, HomePresenterG.DataT<HomeConfig> {

    lateinit var banner: Banner

    init {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initView()
        setListener()

        HomePresenter(this).getHomeConfig().setDataLoadListener(this)
//        HomePresenterG<HomeConfig>(this).getHomeConfigG().setDataLoadListener(this)

        startActivity(Intent(this,SketchAct::class.java))
    }

    private fun setListener() {
        head_left_iv1.setOnClickListener(this)
        head_right_iv1.setOnClickListener(this)
        head_right_iv2.setOnClickListener(this)
        allApartment.setOnClickListener(this)
        userAvatar.setOnClickListener(this)
        createApartment.setOnClickListener(this)
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
//        val banner = Banner(this)
        banner = Banner(this)
        banner.layoutParams = ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, CommonUtil.dp2px(this, 230))
        content_ll.addView(banner, 0)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.head_left_iv1 -> {
                drawer.openDrawer(Gravity.START)
            }
            R.id.allApartment -> {
                startActivity(Intent(this, ApartmentListAct::class.java))
            }
            R.id.userAvatar -> {
                startActivity(Intent(this, LoginAct::class.java))
            }
            R.id.createApartment -> {
                startActivity(Intent(this,SketchAct::class.java))
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (drawer.isDrawerOpen(Gravity.START)) {
            drawer.closeDrawer(Gravity.START)
        }
    }

    private inner class HomeBannerController : BannerController<com.up.measurecopy.model.Banner> {
        override fun getLoopView(container: ViewGroup, data: com.up.measurecopy.model.Banner): View {
            val imageView = ImageView(container.context)
            imageView.layoutParams = container.layoutParams
            imageView.layoutParams.width = ViewPager.LayoutParams.MATCH_PARENT
            imageView.layoutParams.height = ViewPager.LayoutParams.MATCH_PARENT
            imageView.scaleType = ImageView.ScaleType.FIT_XY    //图片比例导致page间有间距
//            imageView.setImageResource(data)
            Glide.with(this@HomeActivity).load(data.pic_url).into(imageView)
            return imageView
        }
    }

    override fun onDataLoadFinish(data: HomeConfig) {
        if (data.banners.isNotEmpty()) {
            setBanner(data.banners)
        }
    }

    private fun setBanner(banners: List<com.up.measurecopy.model.Banner>) {
//        val bannerPicRes: List<Int> = mutableListOf(R.drawable.banner1, R.drawable.banner2, R.drawable.banner2)
        banner.setBanners(HomeBannerController(), banners)
    }

    override fun onDataLoadFailed() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
    }
}