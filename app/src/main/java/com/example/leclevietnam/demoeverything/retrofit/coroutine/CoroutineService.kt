package com.example.leclevietnam.demoeverything.retrofit.coroutine

import com.example.leclevietnam.demoeverything.retrofit.UserModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface CoroutineService {

    @GET("/users")
    fun getUser() : Deferred<List<UserModel>>

    @GET("/users")
    fun getUserResponse() : Deferred<Response<List<UserModel>>>

}