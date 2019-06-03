package com.example.leclevietnam.demoeverything.paging

import com.example.leclevietnam.demoeverything.javaDemo.dagger2.dagger.AppComponent
import com.example.leclevietnam.demoeverything.javaDemo.dagger2.dagger.PerActivity
import com.example.leclevietnam.demoeverything.module.ActivityModule
import dagger.Component

@PerActivity
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface PagingComponent {

    fun getPagingViewModel(): PagingViewModel

    fun inject(pagingActivity: PagingActivity)
}