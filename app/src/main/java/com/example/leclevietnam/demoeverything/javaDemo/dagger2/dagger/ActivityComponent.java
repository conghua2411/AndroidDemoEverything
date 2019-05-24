package com.example.leclevietnam.demoeverything.javaDemo.dagger2.dagger;

import com.example.leclevietnam.demoeverything.javaDemo.JavaDemoActivity;
import com.example.leclevietnam.demoeverything.javaDemo.dagger2.car.Car;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {WheelsModule.class, PetrolEngineModule.class})
public interface ActivityComponent {
    Car getCar();

    void inject(JavaDemoActivity activity);

//    void inject(DemoApplication application);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder housePower(@Named("house power") int housePower);

        @BindsInstance
        Builder engineCapacity(@Named("engine capacity") int engineCapacity);

        Builder appComponent(AppComponent component);

        ActivityComponent build();
    }
}
