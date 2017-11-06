package com.up.measurecopy.model

/**
 * Created by ywd on 2017/11/2.
 * 用户模型
 */
data class User(val first_name: String, val avatar_url: String, val user_id: String, val mobile: String, val sex: String,
                val city: String, val province_id: String, val province_name: String, val company: String, val job: String,
                val seniority_id: Int, val if_active: Int, val token: String, val email: String, val city_id: String,
                var login_state:Boolean)