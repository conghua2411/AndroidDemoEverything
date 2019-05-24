package com.example.leclevietnam.demoeverything.javaDemo;

import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.leclevietnam.demoeverything.DemoApplication;
import com.example.leclevietnam.demoeverything.R;
import com.example.leclevietnam.demoeverything.javaDemo.annotation.AnnoClass;
import com.example.leclevietnam.demoeverything.javaDemo.annotation.BaseAnnotation;
import com.example.leclevietnam.demoeverything.javaDemo.annotation.DemoAnnotation;
import com.example.leclevietnam.demoeverything.javaDemo.annotation.DemoClass;
import com.example.leclevietnam.demoeverything.javaDemo.dagger2.car.Car;
import com.example.leclevietnam.demoeverything.javaDemo.dagger2.dagger.ActivityComponent;
import com.example.leclevietnam.demoeverything.javaDemo.dagger2.dagger.DaggerActivityComponent;

import java.lang.reflect.Method;

import javax.inject.Inject;

public class JavaDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtInfo;
    private Button btnShowAnnotation;

    // dagger 2
    @Inject
    Car car1, car2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_demo);

//        btnShowAnnotation = findViewById(R.id.btnShowAnnotation);
//        txtInfo = findViewById(R.id.txtInfo);
//
//        btnShowAnnotation.setOnClickListener(this);


//        ActivityComponent carComponent = DaggerActivityComponent.builder()
//                .dieselEngineModule(new DieselEngineModule(100))
//                .build();


//        ActivityComponent carComponent = DaggerActivityComponent.builder()
//                .housePower(200)
//                .engineCapacity(2000)
//                .build();

//        ActivityComponent carComponent = ((DemoApplication) getApplication()).getCarComponent();

//        car = carComponent.getCar();

//        carComponent.inject(this);

        AnnoClass annoClass = new AnnoClass();

        ActivityComponent component = DaggerActivityComponent.builder()
                .housePower(300)
                .engineCapacity(3000)
                .appComponent(((DemoApplication)getApplication()).getAppComponent())
                .build();

        component.inject(this);

        car1.drive();
        car2.drive();
    }

    @Override
    public void onClick(View v) {

        Class<?> clazz = DemoClass.class;

        boolean isDemoAnnotation = clazz.isAnnotationPresent(DemoAnnotation.class);

        StringBuilder sb = new StringBuilder();

        if (isDemoAnnotation) {
            DemoAnnotation demoAnnotation = clazz.getAnnotation(DemoAnnotation.class);

            sb.append(clazz.getName());
            sb.append("\n");

            String name = demoAnnotation.name();
            String description = demoAnnotation.description();

            sb.append("name : " + name + "\n");
            sb.append("description : " + description + "\n");

            try {
                Method method = clazz.getMethod("someMethod");

                DemoAnnotation demoAnnotation1 = method.getAnnotation(DemoAnnotation.class);

                sb.append("\n\n");

                sb.append("method: " + method.getName() + "\n");
                name = demoAnnotation1.name();
                description = demoAnnotation1.description();
                sb.append("name : " + name + "\n");
                sb.append("description : " + description + "\n");

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }


            txtInfo.setText(sb.toString());
        }
    }
}
