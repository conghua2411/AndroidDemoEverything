package com.example.leclevietnam.demoeverything.javaDemo.dagger2.dagger;

import com.example.leclevietnam.demoeverything.javaDemo.dagger2.car.Driver;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class DriverModule {
    @Provides
    @Singleton
    static Driver provideDriver() {
        return new Driver();
    }
}
