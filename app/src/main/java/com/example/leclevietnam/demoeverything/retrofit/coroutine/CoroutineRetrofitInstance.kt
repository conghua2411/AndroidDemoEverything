package com.example.leclevietnam.demoeverything.retrofit.coroutine

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CoroutineRetrofitInstance {
    const val BASE_URL = "https://jsonplaceholder.typicode.com"
    var retrofit: Retrofit? = null

    fun getCoroutineService() : CoroutineService {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .build()
        }

        return retrofit!!.create(CoroutineService::class.java)
    }
}