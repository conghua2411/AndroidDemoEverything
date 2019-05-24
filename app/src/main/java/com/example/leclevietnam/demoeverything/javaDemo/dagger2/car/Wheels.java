package com.example.leclevietnam.demoeverything.javaDemo.dagger2.car;

import android.util.Log;

import javax.inject.Inject;

public class Wheels {

    private Rims rims;
    private Tires tires;

    @Inject
    public Wheels(Rims rims, Tires tires) {
        Log.d("dagger", "Wheels: ");
        this.rims = rims;
        this.tires = tires;
    }

    public String showTires() {
        return tires.toString();
    }
}
