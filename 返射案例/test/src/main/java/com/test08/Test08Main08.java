package com.test08;

import java.lang.reflect.Field;

/**
 * 反射在class外访问私有变量
 * getDeclaredField()  取得定义过的变量包括私有变量
 */
public class Test08Main08 {
    public static void main(String[] args) throws Exception{
        Test08Class08 test08Class08 = new Test08Class08();
        Class<?> classType = test08Class08.getClass();
        Field field = classType.getDeclaredField("name");
        //压制java对私有成员的访问控制检查
        field.setAccessible(true);
        field.set(test08Class08,"lisi");
        System.out.println(test08Class08.getName());
    }
}
