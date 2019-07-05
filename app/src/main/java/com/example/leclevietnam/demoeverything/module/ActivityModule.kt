package com.example.leclevietnam.demoeverything.module

import com.example.leclevietnam.demoeverything.kotlinDemo.DemoViewModel
import com.example.leclevietnam.demoeverything.paging.PagingViewModel
import com.example.leclevietnam.demoeverything.paging.api.PagingApi
import com.example.leclevietnam.demoeverything.paging.pageKeyed.PhotoDataFactory
import com.example.leclevietnam.demoeverything.paging.pageKeyed.PhotoDataSource
import com.example.leclevietnam.demoeverything.room.ProductRepos
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class ActivityModule {

    @Provides
    fun provideDemoViewModel(productRepos: ProductRepos): DemoViewModel =
            DemoViewModel(productRepos)

    @Provides
    fun providePagingViewModel(productRepos: ProductRepos,
                               @Named("paging") pagingApi: PagingApi,
                               @Named("paging") photoDataFactory: PhotoDataFactory): PagingViewModel =
            PagingViewModel(productRepos, pagingApi, photoDataFactory)

    @Provides
    @Named("paging")
    fun provideBaseUrlPaging(): String = "https://jsonplaceholder.typicode.com"

    @Provides
    @Named("paging")
    fun provideRetrofit(@Named("paging") baseUrl: String): Retrofit =
            Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

    @Provides
    @Named("paging")
    fun providePagingApi(@Named("paging") retrofit: Retrofit): PagingApi =
            retrofit.create(PagingApi::class.java)

    @Provides
    @Named("paging")
    fun providePhotoDataSource(@Named("paging") pagingApi: PagingApi) =
            PhotoDataSource(pagingApi)

    @Provides
    @Named("paging")
    fun providePhotoDataFactory(@Named("paging") photoDataSource: PhotoDataSource) =
            PhotoDataFactory(photoDataSource)
}