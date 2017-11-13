package com.up.measurecopy.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.up.measurecopy.R
import com.up.measurecopy.main.home.HomeActivity
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.act_welcome.*
import java.util.concurrent.TimeUnit

/**
 * Created by ywd on 2017/11/13.
 * 欢迎页面
 */
class WelcomeAct : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_welcome)

        imgContent.setImageResource(R.drawable.pic_launch)

        Schedulers.single().scheduleDirect({
            startActivity(Intent(this, HomeActivity::class.java))
        },3 * 1000,TimeUnit.MILLISECONDS)
    }
}