package com.up.measurecopy.main.home

import android.content.Context
import android.widget.Toast
import com.up.measurecopy.config.Url
import com.up.measurecopy.model.HomeConfig
import com.up.measurecopy.model.Response
import com.up.measurecopy.util.CommonUtil
import com.up.measurecopy.util.LogUtil
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by ywd on 2017/10/31.
 * 首页数据请求类
 */
class HomePresenter(val context: Context) {

    private val retrofit = Retrofit.Builder().baseUrl(Url.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    init{
    }

    fun getHomeConfig(): HomePresenter {
        val homeService: HomeService = retrofit.create(HomeService::class.java)

        val timestamp = System.currentTimeMillis() / 1000
        val paramsMap = mutableMapOf(Pair("timestamp", "" + timestamp))
        val sign = CommonUtil.makeSign(paramsMap)

       /* val homeConfig = homeService.getHomeConfig(timestamp, sign)
        homeConfig.enqueue(object : Callback<Response<HomeConfig>> {
            override fun onResponse(call: Call<Response<HomeConfig>>?, response: retrofit2.Response<Response<HomeConfig>>?) {
                dataObserver?.onDataLoadFinish(response?.body()!!.data)
            }

            override fun onFailure(call: Call<Response<HomeConfig>>?, t: Throwable?) {
                dataObserver?.onDataLoadFailed()
            }

        })*/

        val homeConfig = homeService.getHomeConfig(timestamp, sign)
        homeConfig.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(object : Observer<Response<HomeConfig>> {
            override fun onComplete() {
                Toast.makeText(context, "onComplete", Toast.LENGTH_SHORT).show()
                LogUtil.d("ywd","onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                Toast.makeText(context, "onSubscribe", Toast.LENGTH_SHORT).show()
                LogUtil.d("ywd","onSubscribe")
            }

            override fun onNext(t: Response<HomeConfig>) {
                Toast.makeText(context, "onNext", Toast.LENGTH_SHORT).show()
                LogUtil.d("ywd","onNext")
                dataObserver?.onDataLoadFinish(t.data)
            }

            override fun onError(e: Throwable) {
                LogUtil.d("ywd","onError:" + e.message)
                e.printStackTrace()
                Toast.makeText(context, "onError", Toast.LENGTH_SHORT).show()
            }

        })
        return this
    }

    interface DataT {
        fun onDataLoadFinish(data: com.up.measurecopy.model.HomeConfig)
        fun onDataLoadFailed()
    }

    private var dataObserver: DataT? = null

    fun setDataLoadListener(listener: DataT) {
        dataObserver = listener
    }
}