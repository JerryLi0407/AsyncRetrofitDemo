package com.example.asyncretrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.syncretrofitdemo.TaipeiTourApiService
import com.example.taipeitourv1.DataClass.TaipeiTourData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tvData: TextView = findViewById(R.id.tvData)

        // 建立 Retrofit 實例的 Builder
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.travel.taipei/")
            .addConverterFactory(GsonConverterFactory.create()) // Gson
            .build()

        // 通過 Retrofit 實例創建 API 服務接口的實現
        val apiService = retrofit.create(TaipeiTourApiService::class.java)

        apiService.getTaipeiTourData().enqueue(object : Callback<TaipeiTourData> {
            override fun onResponse(
                call: Call<TaipeiTourData>,
                response: Response<TaipeiTourData>
            ) {
                // 獲得響應體中的數據部分
                val body = response.body()?.data
                if (body.isNullOrEmpty()) {
                    // 若無數據，顯示“無數據”
                    tvData.text = "No Data"
                } else {
                    // 若有數據，將數據顯示在 TextView 上
                    tvData.text = "${body.get(0).name} \n${body.get(0).introduction} " +
                            "\n\n${body.get(0).address} \n${body.get(0).url}"
                }
            }

            override fun onFailure(call: Call<TaipeiTourData>, t: Throwable) {
                // 若 HTTP 響應失敗，顯示錯誤信息
                tvData.text = "Error loading data: ${t.localizedMessage}"
            }
        })
    }
}