package com.test01;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Test01MyAnnotation01 {
    public String valueName01() default "hello";
    public String valueName02();
}
