package com.up.measurecopy.main.base

import android.app.Activity
import android.widget.Toast

/**
 * Created by ywd on 2017/11/13.
 * BaseAct
 */
open class BaseAct:Activity() {

    fun showToast(message: String) {
        runOnUiThread {
            Toast.makeText(this, message,Toast.LENGTH_SHORT).show()
        }
    }
}