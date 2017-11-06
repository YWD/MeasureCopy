package com.up.measurecopy.main.login

import com.up.measurecopy.config.Url
import com.up.measurecopy.model.Response
import com.up.measurecopy.model.User
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by ywd on 2017/11/2.
 * 用户登录接口
 */
interface LoginService {

    @FormUrlEncoded
    @POST(Url.LOGIN)
    fun login(@FieldMap queryMap: Map<String, String>): Observable<Response<User>>
}