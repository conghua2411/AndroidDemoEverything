package com.example.leclevietnam.demoeverything.testDemo.di

import com.example.leclevietnam.demoeverything.javaDemo.dagger2.dagger.AppComponent
import com.example.leclevietnam.demoeverything.javaDemo.dagger2.dagger.PerActivity
import com.example.leclevietnam.demoeverything.testDemo.TestActivity
import com.example.leclevietnam.demoeverything.testDemo.di.module.TestModule
import dagger.Component

@PerActivity
@Component(dependencies = [AppComponent::class], modules = [TestModule::class])
interface TestActivityComponent {

//    fun getTestViewModel(): TestViewModel

    fun inject(testActivity: TestActivity)
}