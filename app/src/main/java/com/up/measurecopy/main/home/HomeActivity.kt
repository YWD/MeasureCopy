package com.up.measurecopy.main.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.support.v4.view.ViewPager
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.bumptech.glide.Glide
import com.up.measurecopy.R
import com.up.measurecopy.component.banner.Banner
import com.up.measurecopy.component.banner.BannerController
import com.up.measurecopy.main.apartment.ApartmentListAct
import com.up.measurecopy.main.base.BaseAct
import com.up.measurecopy.main.login.LoginAct
import com.up.measurecopy.main.sketch.SketchAct
import com.up.measurecopy.model.HomeConfig
import com.up.measurecopy.util.CommonUtil
import com.up.measurecopy.util.LogUtil
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableOnSubscribe
import io.reactivex.FlowableSubscriber
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_head.*
import kotlinx.android.synthetic.main.home_content.*
import kotlinx.android.synthetic.main.home_left_menu.*
import okhttp3.*
import org.reactivestreams.Subscription
import java.io.IOException

/**
 * Created by ywd on 2017/10/26.
 * App 首页
 */
class HomeActivity : BaseAct(), View.OnClickListener, HomePresenter.DataT, HomePresenterG.DataT<HomeConfig> {

    lateinit var banner: Banner

    private var subscription: Subscription? = null
    private var okHttpClient: OkHttpClient? = null
    private var authenticityToken: String? = null
    init {
        okHttpClient = OkHttpClient.Builder().cookieJar(object : CookieJar {
            val localCookies = HashMap<String,MutableList<Cookie>>()
            override fun saveFromResponse(url: HttpUrl?, cookies: MutableList<Cookie>?) {
                LogUtil.d("ywd-cookie", "save url:" + url.toString())
                if (url != null && cookies != null) {
                    localCookies.put("github", cookies)
                    for (cookie in cookies) {
                        LogUtil.d("ywd-cookie", cookie.name() + ":" + cookie.value())
                    }
                }

            }

            override fun loadForRequest(url: HttpUrl?): MutableList<Cookie> {
                LogUtil.d("ywd-cookie", "load url:" + url.toString())
                return localCookies["github"]?: ArrayList()
            }

        }).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initView()
        setListener()

        HomePresenter(this).getHomeConfig().setDataLoadListener(this)
//        HomePresenterG<HomeConfig>(this).getHomeConfigG().setDataLoadListener(this)

        startActivity(Intent(this, SketchAct::class.java))

    }

    private fun setListener() {
        head_left_iv1.setOnClickListener(this)
        head_right_iv1.setOnClickListener(this)
        head_right_iv2.setOnClickListener(this)
        allApartment.setOnClickListener(this)
        userAvatar.setOnClickListener(this)
        createApartment.setOnClickListener(this)
        mySchedules.setOnClickListener(this)
        myQuotation.setOnClickListener(this)
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
                startActivity(Intent(this, SketchAct::class.java))
            }
            R.id.mySchedules -> {
//                subscription!!.request(12)
                githubGet()
            }
            R.id.myQuotation -> {
//                start()
                githubPost()
            }
        }
    }

    private fun githubPost() {
        val requestBody = FormBody.Builder().
                add("login", "1347011912@qq.com").
                add("password", "g_yang321").
//                add("authenticity_token", "NIAMBzMpu1wxlvnwWL3PjbXa5U25m66vZ89er/iGf1MMwF0iXL+6LbWxcRc77MhH47RVQXTbL0Q9HjC7Y58hGA==").
                add("authenticity_token", authenticityToken!!).   //_gh_sess
                build()
        val postRequest = Request.Builder().url("https://github.com/session").post(requestBody).build()
        val postCall = okHttpClient!!.newCall(postRequest)
        postCall.enqueue(object : Callback {
            override fun onFailure(p0: Call?, p1: IOException?) {

            }

            override fun onResponse(p0: Call?, p1: Response?) {
                LogUtil.d("ywd","post response:" + p1?.body()?.string())
            }
        })
    }

    private fun githubGet() {
        val request = Request.Builder().url("https://github.com/login").build()
        val getCall = okHttpClient?.newCall(request)
        getCall?.enqueue(object : Callback {
            override fun onFailure(p0: Call?, p1: IOException?) {
                showToast("onFailure:githubGet")
            }

            override fun onResponse(p0: Call?, p1: Response?) {
                showToast("onResponse:githubGet")
                val string = p1?.body()?.string()
                LogUtil.d("ywd","get response:" + string)
                authenticityToken = string?.substring(string.indexOf("authenticity_token", 0, false))?.substring(0, 150)
                LogUtil.d("ywd", "token:" + authenticityToken)
                authenticityToken = authenticityToken?.substring(41,88 + 41)
                LogUtil.d("ywd", "token:" + authenticityToken)
            }
        })
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

    private fun start() {
        Flowable.create<Int>(FlowableOnSubscribe() {
            for (i in 0..10000) {
                LogUtil.d("ywd", "emmit:" + i)
                it.onNext(i)
            }

        }, BackpressureStrategy.DROP).subscribeOn(Schedulers.io()).subscribe(object : FlowableSubscriber<Int> {
            override fun onComplete() {
            }

            override fun onNext(t: Int?) {
                SystemClock.sleep(500)
                LogUtil.d("ywd", ":" + t)
            }

            override fun onSubscribe(s: Subscription) {
                subscription = s
                s.request(128)
            }

            override fun onError(t: Throwable?) {
            }

        })
    }
}