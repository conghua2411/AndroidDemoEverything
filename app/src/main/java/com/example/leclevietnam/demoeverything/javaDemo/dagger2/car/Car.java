package com.example.leclevietnam.demoeverything.javaDemo.dagger2.car;

import android.util.Log;

import com.example.leclevietnam.demoeverything.javaDemo.dagger2.dagger.PerActivity;

import javax.inject.Inject;

@PerActivity
public class Car {
    private Driver driver;
    private Engine engine;
    private Wheels wheels;

    @Inject
    public Car(Driver driver, Engine engine, Wheels wheels) {
        this.driver = driver;
        this.engine = engine;
        this.wheels = wheels;
    }

    @Inject
    public void enableRemote(Remote remote) {
        remote.setListener(this);
    }

    public void drive() {
        engine.start();
        Log.d("Dagger", driver + " drive: " + this + "wheels tires : " + wheels.showTires());
    }
}
