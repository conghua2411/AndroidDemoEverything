package com.example.leclevietnam.demoeverything.javaDemo.dagger2.dagger;

import com.example.leclevietnam.demoeverything.javaDemo.dagger2.car.DieselEngine;
import com.example.leclevietnam.demoeverything.javaDemo.dagger2.car.Engine;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public class DieselEngineModule {

    private int housePower;

    public DieselEngineModule(int housePower) {
        this.housePower = housePower;
    }

    @Provides
    int provideHousePower() {
        return housePower;
    }

    @Provides
    Engine provideEngine(DieselEngine engine) {
        return engine;
    }
}
