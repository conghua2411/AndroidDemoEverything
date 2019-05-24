package com.example.leclevietnam.demoeverything.javaDemo.dagger2.car;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Named;

public class PetrolEngine implements Engine {

    private int housePower;
    private int engineCapacity;

    @Inject
    public PetrolEngine(@Named("house power") int housePower,
                        @Named("engine capacity") int engineCapacity) {
        this.housePower = housePower;
        this.engineCapacity = engineCapacity;
    }

    @Override
    public void start() {
        Log.d("Dagger", "PetrolEngine start: " + housePower + ", engineCapacity : " + engineCapacity);
    }
}
