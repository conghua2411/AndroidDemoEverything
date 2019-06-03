package com.example.leclevietnam.demoeverything.retrofit.rxjava

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RxRetrofitInstance {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com"
    private var retrofit: Retrofit? = null

    fun getRxService() : RxService {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }

        return retrofit!!.create(RxService::class.java)
    }
}