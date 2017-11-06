package com.up.measurecopy.main.apartment

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.view.View
import android.widget.Toast
import com.up.measurecopy.R
import com.up.measurecopy.config.Url
import com.up.measurecopy.manager.UserManager
import com.up.measurecopy.model.Apartment
import com.up.measurecopy.model.Response
import com.up.measurecopy.util.CommonUtil
import com.up.measurecopy.util.DeviceUtil
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_apartment_list.*
import kotlinx.android.synthetic.main.app_head.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by ywd on 2017/11/2.
 * 全部户型
 */
class ApartmentListAct : Activity(), View.OnClickListener {
    override fun onClick(v: View) {
        when (v.id) {
            R.id.head_left_iv1 -> {
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apartment_list)

        initView()
        setListener()
        getData()
    }

    private fun getData() {
        val retrofit = Retrofit.Builder().baseUrl(Url.BASE_URL).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
        val apartmentService = retrofit.create(ApartmentService::class.java)
        val queryMap = mutableMapOf(Pair("timestamp", "" + System.currentTimeMillis()))
        queryMap.put("uuid",DeviceUtil.getDeviceId(this))
        queryMap.put("device_id",DeviceUtil.getDeviceId(this))
        queryMap.put("start_index","0")
        queryMap.put("count","5")
        queryMap.put("token",UserManager.getInstance()!!.getUser()!!.token)
        queryMap.put("user_id",UserManager.getInstance()!!.getUserId()!!)
        queryMap.put("origin","1")
        val sign = CommonUtil.makeSign(queryMap)
        queryMap.put("sign",sign)

       /*        map.put("user_id", userId);
        map.put("uuid", uuid);
        map.put("timestamp", time);
        map.put("start_index", start_index);
        map.put("count", count);
        map.put("origin", origin);
        String token = new UserManager(FWApplication.getInstance()).getLoginToken();
        map.put("token", token);
        String deviceId = TelephoneUtil.getIMEI(FWApplication.getInstance());
        map.put("device_id", deviceId);*/
        apartmentService.getApartments(queryMap).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<Response<List<Apartment>>> {
            override fun onSubscribe(d: Disposable) {
                Toast.makeText(this@ApartmentListAct, "onSubscribe",Toast.LENGTH_SHORT).show()
            }

            override fun onError(e: Throwable) {
                Toast.makeText(this@ApartmentListAct, "onError",Toast.LENGTH_SHORT).show()
            }

            override fun onComplete() {
                Toast.makeText(this@ApartmentListAct, "onComplete",Toast.LENGTH_SHORT).show()
            }

            override fun onNext(t: Response<List<Apartment>>) {
                Toast.makeText(this@ApartmentListAct, t.data[0].community_name,Toast.LENGTH_SHORT).show()
                recyclerView.adapter = RecyclerAdapter(t.data)
            }


        })

    }

    private fun setListener() {
        head_left_iv1.setOnClickListener(this)
    }

    private fun initView() {
        head_title.text = "全部户型"
        head_left_iv1.setImageResource(R.drawable.icon_back_black)
        head_left_iv1.visibility = View.VISIBLE
        head_right_iv1.setImageResource(R.drawable.icon_yuntongbu_p)
        head_right_iv1.visibility = View.VISIBLE

        recyclerView.layoutManager = LinearLayoutManager(this)
        (recyclerView.layoutManager as LinearLayoutManager).orientation = OrientationHelper.VERTICAL
    }


}