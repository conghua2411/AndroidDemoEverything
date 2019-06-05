package com.example.leclevietnam.demoeverything.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.leclevietnam.demoeverything.room.ProductDatabase
import com.example.leclevietnam.demoeverything.room.ProductRepos
import com.example.leclevietnam.demoeverything.room.testDatabase.TestDatabase
import com.example.leclevietnam.demoeverything.room.testDatabase.student.StudentRepos
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context =
            application

    @Provides
    @Singleton
    internal fun provideProductDatabase(context: Context): ProductDatabase =
            Room.databaseBuilder(context, ProductDatabase::class.java, "Product.db").build()

    @Provides
    @Singleton
    internal fun provideTestDatabase(context: Context): TestDatabase =
            Room.databaseBuilder(context, TestDatabase::class.java, "test.db").build()

    @Provides
    @Singleton
    fun provideProductDAO(productDatabase: ProductDatabase): ProductRepos =
            ProductRepos(productDatabase.productDao())

    @Provides
    @Singleton
    fun provideStudentDAO(testDatabase: TestDatabase): StudentRepos =
            StudentRepos(testDatabase.studentDao())
}