package com.example.leclevietnam.demoeverything.retrofit.rxjava

import com.example.leclevietnam.demoeverything.retrofit.UserModel
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RxService {

    @GET("/users")
    fun rxGetUser() : Observable<List<UserModel>>

    @GET("/users")
    fun rxGetUserId(@Query("id") id: Int): Observable<List<UserModel>>

    @GET("/users")
    fun rxGetUserIdSingle(@Query("id") id: Int): Single<List<UserModel>>
}