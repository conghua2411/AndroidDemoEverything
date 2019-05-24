package com.example.leclevietnam.demoeverything.javaDemo.annotation;

import android.util.Log;

public abstract class BaseAnnotation {
    public BaseAnnotation() {
        Class<?> clazz = this.getClass();

        boolean haveAnnotation = clazz.isAnnotationPresent(DemoAnnotation.class);

        if (haveAnnotation) {
            DemoAnnotation demoAnnotation = clazz.getAnnotation(DemoAnnotation.class);

            Log.d("DemoAnnotation", "BaseAnnotation: " + demoAnnotation.name() + " - " + demoAnnotation.description());
        }
    }
}
