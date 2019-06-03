package com.example.leclevietnam.demoeverything

import android.app.Application

import com.example.leclevietnam.demoeverything.javaDemo.dagger2.dagger.AppComponent
import com.example.leclevietnam.demoeverything.javaDemo.dagger2.dagger.DaggerAppComponent
import com.example.leclevietnam.demoeverything.koinDemo.appModule
import com.example.leclevietnam.demoeverything.retrofit.module2
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class DemoApplication : Application() {

    var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build()

        startKoin {
            androidLogger()
            androidContext(this@DemoApplication)
            modules(appModule, module2)
        }
    }
}
