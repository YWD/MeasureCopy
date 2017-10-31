package com.up.measurecopy.main.net.presenter

import com.up.measurecopy.config.Url
import com.up.measurecopy.main.net.NetApi
import com.up.measurecopy.util.CommonUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by ywd on 2017/10/31.
 * 首页数据请求类
 */
class HomePresenter<T> {

    private val retrofit = Retrofit.Builder().baseUrl(Url.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

    init{
    }

    fun getHomeConfig(): HomePresenter<T> {
        val request:NetApi.HomeApi = retrofit.create(NetApi.HomeApi::class.java)

        val timestamp = System.currentTimeMillis() / 1000
        val paramsMap = mutableMapOf<String ,Any>(Pair("timestamp", timestamp))
        val sign = CommonUtil.makeSign(paramsMap)

       /* val homeConfig = request.getHomeConfig<T>(timestamp, sign)
        homeConfig.enqueue(object : Callback<com.up.measurecopy.model.Response<T>> {
            override fun onResponse(call: Call<com.up.measurecopy.model.Response<T>>?, response: Response<com.up.measurecopy.model.Response<T>>?) {
                dataObserver?.onDataLoadFinish(response?.body()!!.data)
            }

            override fun onFailure(call: Call<com.up.measurecopy.model.Response<T>>?, t: Throwable?) {
                dataObserver?.onDataLoadFailed()
            }

        })*/
        return this
    }

    interface DataT<in T> {
        fun onDataLoadFinish(data:T)
        fun onDataLoadFailed()
    }

    private var dataObserver:DataT<T>? = null

    fun setDataLoadListener(listener: DataT<T>) {
        dataObserver = listener
    }
}