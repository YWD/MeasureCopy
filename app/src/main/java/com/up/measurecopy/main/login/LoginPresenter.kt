package com.up.measurecopy.main.login

import com.up.measurecopy.config.Url
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by ywd on 2017/11/2.
 * 登录presenter
 */
class LoginPresenter {

    val loginService:LoginService = Retrofit.Builder().baseUrl(Url.BASE_URL).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(LoginService::class.java)
}