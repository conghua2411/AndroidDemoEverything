package com.example.leclevietnam.demoeverything.annotationProcessing.bindViewAnno;

import android.app.Activity;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class ViewBinder {

    public static void bind(final Activity activity) {
        bindViews(activity, activity.getClass().getDeclaredFields(), activity.getWindow().getDecorView());
    }

    private static void bindViews(Activity activity, Field[] fields, View rootView) {
        for (final Field field : fields) {
            Annotation annotation = field.getAnnotation(BindView2.class);

            if (annotation != null) {
                BindView2 bindView = (BindView2) annotation;
                int id = bindView.value();

                View view = rootView.findViewById(id);

                try {
                    field.setAccessible(true);
                    field.set(activity, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
