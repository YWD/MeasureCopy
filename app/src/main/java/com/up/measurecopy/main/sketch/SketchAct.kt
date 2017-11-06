package com.up.measurecopy.main.sketch

import android.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import com.up.measurecopy.R
import kotlinx.android.synthetic.main.app_head.*

class SketchAct : AppCompatActivity(), View.OnClickListener {

    private var currentFragmentIndex = 0
    var currentFragment: Fragment? = null
    var sketchFragment:SketchFragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sketch)

        initView()
        setListener()
        changeFragment(currentFragmentIndex)
    }

    private fun changeFragment(currentFragmentIndex: Int) {
        when (currentFragmentIndex) {
            0 ->{
                if (sketchFragment === null) {
                    sketchFragment = SketchFragment()
                }
                currentFragment = sketchFragment
            }
        }

        fragmentManager.beginTransaction().replace(R.id.containerView,currentFragment).commit()
    }

    private fun setListener() {
        head_left_iv1.setOnClickListener(this)
    }

    private fun initView() {
        head_left_iv1.setImageResource(R.drawable.icon_back_black)
        head_left_iv1.visibility = View.VISIBLE
        head_title.text = "草图"
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.head_left_iv1 -> {
                finish()
            }
        }
    }
}
