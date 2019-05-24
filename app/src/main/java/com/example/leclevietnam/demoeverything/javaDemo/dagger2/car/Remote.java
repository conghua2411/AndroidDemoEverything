package com.example.leclevietnam.demoeverything.javaDemo.dagger2.car;

import android.util.Log;

import javax.inject.Inject;

public class Remote {

    @Inject
    public Remote() {

    }

    public void setListener(Car car) {
        Log.d("Dagger", "setListener: remote");
    }
}
