package com.up.measurecopy.main.net.presenter

import com.up.measurecopy.config.Url
import com.up.measurecopy.main.net.NetApi
import com.up.measurecopy.model.HomeConfig
import com.up.measurecopy.model.Response
import com.up.measurecopy.util.CommonUtil
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by ywd on 2017/10/31.
 * 首页数据请求类
 */
class HomePresenterN {

    private val retrofit = Retrofit.Builder().baseUrl(Url.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build()

    init{
    }

    fun getHomeConfig(): HomePresenterN {
        val request: NetApi.HomeApi = retrofit.create(NetApi.HomeApi::class.java)

        val timestamp = System.currentTimeMillis() / 1000
        val paramsMap = mutableMapOf<String ,Any>(Pair("timestamp", timestamp))
        val sign = CommonUtil.makeSign(paramsMap)

        val homeConfig = request.getHomeConfig(timestamp, sign)
        homeConfig.enqueue(object : Callback<Response<HomeConfig>> {
            override fun onResponse(call: Call<Response<HomeConfig>>?, response: retrofit2.Response<Response<HomeConfig>>?) {
                dataObserver?.onDataLoadFinish(response?.body()!!.data)
            }

            override fun onFailure(call: Call<Response<HomeConfig>>?, t: Throwable?) {
                dataObserver?.onDataLoadFailed()
            }

        })

        val homeConfigR = request.getHomeConfigR(timestamp, sign)
        homeConfigR.subscribeOn(Schedulers.io()).subscribe(object : Observer<Response<HomeConfig>> {
            override fun onComplete() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onSubscribe(d: Disposable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onNext(t: Response<HomeConfig>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(e: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
        return this
    }

    interface DataT<HomeConfig> {
        fun onDataLoadFinish(data: com.up.measurecopy.model.HomeConfig)
        fun onDataLoadFailed()
    }

    private var dataObserver:DataT<HomeConfig>? = null

    fun setDataLoadListener(listener: DataT<HomeConfig>) {
        dataObserver = listener
    }
}