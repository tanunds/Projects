package com.example.a8puzzle

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpManager {
    val retrofitService : ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.74:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}