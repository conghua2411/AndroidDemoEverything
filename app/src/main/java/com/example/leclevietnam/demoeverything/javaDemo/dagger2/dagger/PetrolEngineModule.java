package com.example.leclevietnam.demoeverything.javaDemo.dagger2.dagger;

import com.example.leclevietnam.demoeverything.javaDemo.dagger2.car.Engine;
import com.example.leclevietnam.demoeverything.javaDemo.dagger2.car.PetrolEngine;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class PetrolEngineModule {

    @Binds
    abstract Engine bindEngine(PetrolEngine engine);
}
