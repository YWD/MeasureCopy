package com.up.measurecopy.main.net

import com.up.measurecopy.model.HomeConfig
import com.up.measurecopy.model.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by ywd on 2017/10/31.
 * retrofit 网络请求接口
 */

class NetApi {

    interface HomeApi {
        //@GET("config/?timestamp={timestamp}&sign={sign}")
        /*@GET("config/")
        fun <T> getHomeConfig(@Query("timestamp") timestamp: Long?, @Query("sign") sign: String): Call<Response<T>>*/
        @GET("config/")
        fun getHomeConfig(@Query("timestamp") timestamp: Long?, @Query("sign") sign: String): Call<Response<HomeConfig>>
    }
}
