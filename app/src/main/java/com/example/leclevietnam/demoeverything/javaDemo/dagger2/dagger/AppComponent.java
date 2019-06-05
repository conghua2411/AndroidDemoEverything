package com.example.leclevietnam.demoeverything.javaDemo.dagger2.dagger;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.example.leclevietnam.demoeverything.DemoApplication;
import com.example.leclevietnam.demoeverything.javaDemo.dagger2.car.Driver;
import com.example.leclevietnam.demoeverything.module.ActivityModule;
import com.example.leclevietnam.demoeverything.module.AppModule;
import com.example.leclevietnam.demoeverything.room.ProductDatabase;
import com.example.leclevietnam.demoeverything.room.ProductRepos;
import com.example.leclevietnam.demoeverything.room.testDatabase.student.StudentRepos;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {DriverModule.class, AppModule.class})
public interface AppComponent {

    Driver getDriver();

//    Context getContext();
//
//    ProductDatabase getProductDatabase();

    ProductRepos getProductRepos();

    StudentRepos getStudentRepos();

    void inject(DemoApplication application);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
