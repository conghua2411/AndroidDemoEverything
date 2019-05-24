package com.example.leclevietnam.demoeverything.javaDemo.dagger2.car;

import android.util.Log;

import javax.inject.Inject;

public class DieselEngine implements Engine {

    private int housePower;

    @Inject
    public DieselEngine(int housePower) {
        this.housePower = housePower;
    }

    @Override
    public void start() {
        Log.d("Dagger", "DieselEngine start: " + housePower);
    }
}
