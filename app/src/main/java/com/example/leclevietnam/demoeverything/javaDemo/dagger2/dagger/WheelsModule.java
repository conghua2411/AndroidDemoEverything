package com.example.leclevietnam.demoeverything.javaDemo.dagger2.dagger;

import com.example.leclevietnam.demoeverything.javaDemo.dagger2.car.Rims;
import com.example.leclevietnam.demoeverything.javaDemo.dagger2.car.Tires;
import com.example.leclevietnam.demoeverything.javaDemo.dagger2.car.Wheels;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class WheelsModule {

    @Provides
    static Rims provideRims() {
        return new Rims();
    }

    @Provides
    static Tires provideTires() {
//        Tires tires = new Tires();
//        tires.inflate();
//        return tires;

        return new Tires();
    }

    @Provides
    static Wheels provideWheels(Rims rims, Tires tires) {
        return new Wheels(rims, tires);
    }
}
