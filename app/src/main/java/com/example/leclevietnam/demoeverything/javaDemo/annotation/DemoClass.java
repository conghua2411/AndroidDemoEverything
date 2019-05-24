package com.example.leclevietnam.demoeverything.javaDemo.annotation;

import javax.inject.Inject;

@DemoAnnotation(name = "cong", description = "123123")
public class DemoClass {

    @DemoAnnotation(name = "constructor", description = "constructor des")
    public DemoClass() {}

    @DemoAnnotation(name = "demoMethod")
    public void someMethod() {

    }

    public void todo(@DemoAnnotation(name = "field") String job) {

        @DemoAnnotation(name = "localVariable")
        int localVariable = 0;
    }
}
