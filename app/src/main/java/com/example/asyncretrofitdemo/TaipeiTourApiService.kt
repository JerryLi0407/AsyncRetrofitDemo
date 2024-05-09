package com.example.syncretrofitdemo

import com.example.taipeitourv1.DataClass.TaipeiTourData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface TaipeiTourApiService {
    @Headers("accept: application/json")
    @GET("open-api/zh-tw/Attractions/All")
    fun getTaipeiTourData() : Call<TaipeiTourData>
}