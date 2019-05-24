package com.example.leclevietnam.demoeverything.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.leclevietnam.demoeverything.kotlinDemo.DemoViewModel
import com.example.leclevietnam.demoeverything.room.ProductDatabase
import com.example.leclevietnam.demoeverything.room.ProductRepos
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun provideProductDatabase(context: Context): ProductDatabase =
            Room.databaseBuilder(context, ProductDatabase::class.java, "Product.db").build()

    @Provides
    @Singleton
    fun provideProductDAO(productDatabase: ProductDatabase): ProductRepos = ProductRepos(productDatabase.productDao())
}