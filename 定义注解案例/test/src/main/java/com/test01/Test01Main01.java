package com.test01;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ("outData", new Class[]{})   java返射
 */
public class Test01Main01 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Test01Mytest01 test01Mytest01 = new Test01Mytest01();
        Class<Test01Mytest01> c = Test01Mytest01.class;
        Method m = c.getMethod("outData", new Class[]{});
        if(m.isAnnotationPresent(Test01MyAnnotation01.class)){
            m.invoke(test01Mytest01,new Object[]{});
            Test01MyAnnotation01 annotation01 = m.getAnnotation(Test01MyAnnotation01.class);
            String valueName01 = annotation01.valueName01();
            String valueName02 = annotation01.valueName02();
            System.out.println("valueName01:" + valueName01);
            System.out.println("valueName02:" + valueName02);
        }
        Annotation[] annotations = m.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation.annotationType().getName());
        }
    }
}
