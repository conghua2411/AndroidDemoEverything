package com.example.leclevietnam.demoeverything.testDemo.di.module

import com.example.leclevietnam.demoeverything.room.testDatabase.student.StudentRepos
import com.example.leclevietnam.demoeverything.testDemo.TestViewModel
import dagger.Module
import dagger.Provides

@Module
class TestModule {
    @Provides
    fun provideTestViewModel(studentRepos: StudentRepos) : TestViewModel =
            TestViewModel(studentRepos)
}