package com.example.leclevietnam.demoeverything.javaDemo.annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface DemoAnnotation {

    public String name();

    public String description() default "default";
}