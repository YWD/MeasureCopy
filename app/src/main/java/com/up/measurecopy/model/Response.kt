package com.up.measurecopy.model

/**
 * Created by ywd on 2017/10/31.
 * 服务器返回Response基本格式
 */
data class Response<out T>(val code: Int, val message: String, val data: T)

data class HomeConfig(val qq_group_id: String, val show_service_staff: String,
                      val android_show_3d: String, val during_check_version: String, val qq_group_key: String, val banners: List<Banner>)

data class Banner(val order: Int, val title: String, val link: String, val pic_url: String, val no: String)
