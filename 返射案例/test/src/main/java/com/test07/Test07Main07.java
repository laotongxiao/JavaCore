package com.test07;

import java.lang.reflect.Method;

/**
 * 反射在class外访问私有方法
 * getDeclaredMethod()  取得定义过的方法包括私有方法
 */
public class Test07Main07 {
    public static void main(String[] args) throws Exception{
        Test07Class07 test07Class07 = new Test07Class07();
        Class<?> classType = test07Class07.getClass();
        Method method = classType.getDeclaredMethod("testPrivate", new Class[]{String.class});
        //压制java对私有成员的访问控制检查
        method.setAccessible(true);
        method.invoke(test07Class07,new Object[]{"zhangsan"});
    }
}
