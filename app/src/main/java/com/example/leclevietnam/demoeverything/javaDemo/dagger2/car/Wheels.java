package com.example.leclevietnam.demoeverything.javaDemo.dagger2.car;

import android.util.Log;

import javax.inject.Inject;

public class Wheels {

    private Rims rims;
    private Tires tires;
    private InjectClass injectClass;

    @Inject
    public Wheels(Rims rims, Tires tires, InjectClass injectClass) {
        Log.d("dagger", "Wheels: " + injectClass.toString());
        this.rims = rims;
        this.tires = tires;
        this.injectClass = injectClass;
    }

    public String showTires() {
        return tires.toString();
    }
}
