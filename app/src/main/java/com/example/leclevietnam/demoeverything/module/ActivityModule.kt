package com.example.leclevietnam.demoeverything.module

import android.content.Context
import com.example.leclevietnam.demoeverything.kotlinDemo.DemoViewModel
import com.example.leclevietnam.demoeverything.paging.PagingViewModel
import com.example.leclevietnam.demoeverything.room.ProductRepos
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {

    @Provides
    fun provideDemoViewModel(productRepos: ProductRepos): DemoViewModel = DemoViewModel(productRepos)

    @Provides
    fun providePagingViewModel(productRepos: ProductRepos): PagingViewModel = PagingViewModel(productRepos)

}