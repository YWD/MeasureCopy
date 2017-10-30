package com.up.measurecopy.main

import android.app.Activity
import android.os.Bundle
import android.view.View

import com.up.measurecopy.R

/**
 * Created by ywd on 2017/10/30.
 */

class TestActivity : Activity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.head_left_iv1 -> {
            }
        }
    }
}
