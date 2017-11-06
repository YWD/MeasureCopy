package com.up.measurecopy.main.apartment

import com.up.measurecopy.config.Url
import com.up.measurecopy.model.Apartment
import com.up.measurecopy.model.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by ywd on 2017/11/2.
 * Apartment相关请求
 */
interface ApartmentService {

    @GET(Url.APARTMENTS)
    fun getApartments(@QueryMap queryMap:Map<String,String>):Observable<Response<List<Apartment>>>
}