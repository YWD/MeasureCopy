package com.up.measurecopy.main.home

import com.up.measurecopy.config.Url
import com.up.measurecopy.model.HomeConfig
import com.up.measurecopy.model.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by ywd on 2017/10/31.
 * retrofit 网络请求接口
 */
interface HomeService {

//    @GET(Url.APP_CONFIG)
//    fun getHomeConfig(@Query("timestamp") timestamp: Long?, @Query("sign") sign: String): Call<Response<HomeConfig>>

    @GET(Url.APP_CONFIG)
    fun getHomeConfig(@Query("timestamp") timestamp: Long?, @Query("sign") sign: String): Observable<Response<HomeConfig>>

    @GET(Url.APP_CONFIG)    // Method return type must not include a type variable or wildcard: io.reactivex.Observable<com.up.measurecopy.model.Response<T>>。固定接口只会返回固定model，so generic useless
    fun <T>getHomeConfigG(@Query("timestamp") timestamp: Long?, @Query("sign") sign: String): Observable<Response<T>>
}
