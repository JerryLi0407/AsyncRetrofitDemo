package com.example.asyncretrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.travel.taipei/")
            .addConverterFactory(GsonConverterFactory.create()) // Gson
            .build()

        val apiService = retrofit.create(TaipeiTourApiService::class.java)
        apiService.getTaipeiTourData().enqueue(object : Callback<TaipeiTourData> {
            override fun onResponse(
                call: Call<TaipeiTourData>,
                response: Response<TaipeiTourData>
            ) {
                val tvData: TextView = findViewById(R.id.tvData)
                val body = response.body()?.data
                if (body != null) {
                    tvData.text = "${body.get(0).name} \n${body.get(0).introduction} \n\n${body.get(0).address} \n${body.get(0).url}"
                } else {
                    tvData.text = "No Data"
                }
            }

            override fun onFailure(call: Call<TaipeiTourData>, t: Throwable) {
                val tvData: TextView = findViewById(R.id.tvData)
                tvData.text = "Error: ${t.message}"
            }
        })
    }
}