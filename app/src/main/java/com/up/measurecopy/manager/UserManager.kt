package com.up.measurecopy.manager

import android.content.Context
import com.google.gson.Gson
import com.up.measurecopy.app.App
import com.up.measurecopy.model.User

/**
 * Created by ywd on 2017/11/2.
 * 用户信息管理
 */
class UserManager private constructor(val context: Context?) {

    var appUser: User? = null

    companion object {

        private val USER_INFO = "user_info"
        //        val userManager = UserManager(App.getApp())
        private var userManager: UserManager? = null
        private val gson = Gson()

        fun getInstance(): UserManager? {
            if (userManager === null) {
                userManager = UserManager(App.instance)
            }
            return userManager
        }
    }

    fun saveUser(user: User) {
        val userInfo = gson.toJson(user)
        PreferenceManager.putString(USER_INFO, userInfo)
    }

    fun getUser(): User? {
        if (appUser === null) {
            val userInfo = PreferenceManager.getString(USER_INFO)
            appUser = gson.fromJson(userInfo, User::class.java)
        }
        return appUser
    }

    fun getUserId(): String? = getUser()?.user_id

    fun loginOut() {
        val user = getUser()
        user?.login_state = false
        saveUser(user!!)
    }

}