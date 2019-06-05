package com.example.leclevietnam.demoeverything.paging.api

import com.example.leclevietnam.demoeverything.paging.model.Photo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface PagingApi {

    @GET("/photos")
    fun getAllPhoto(): Call<List<Photo>>

    @GET("/photos")
    fun getPhotoId(@Query("id") id: Int) : Call<Photo>

    @GET("/photos")
    fun getPhotosByAlbum(@Query("albumId") albumId: Int): Call<List<Photo>>
}