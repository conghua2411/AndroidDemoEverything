package com.example.leclevietnam.demoeverything;

import android.app.Application;

import com.example.leclevietnam.demoeverything.javaDemo.dagger2.dagger.AppComponent;
import com.example.leclevietnam.demoeverything.javaDemo.dagger2.dagger.DaggerAppComponent;
import com.example.leclevietnam.demoeverything.room.ProductDatabase;

import javax.inject.Inject;

import androidx.room.Room;

public class DemoApplication extends Application {

//    private ActivityComponent carComponent;

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

//        carComponent = DaggerActivityComponent.builder()
//                .housePower(100)
//                .engineCapacity(1000)
//                .build();

//        appComponent = DaggerAppComponent.create();

        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build();
    }

//    public ActivityComponent getCarComponent() {
//        return carComponent;
//    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
