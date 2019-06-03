package com.example.leclevietnam.demoeverything.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface GetDataService {

    @GET("/users")
    fun getAllUser(): Call<List<UserModel>>
}