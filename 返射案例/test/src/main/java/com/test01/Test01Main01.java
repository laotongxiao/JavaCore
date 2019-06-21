package com.test01;

import java.lang.reflect.Method;

/**
 * 返射的作用是动态(Runtime)创建对象,或通过对象取得class对象的成员信息
 * classType.newInstance() 所在的类有不带参数的构造方法生成
 */
public class Test01Main01 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> classType = Class.forName("java.lang.String");
        Method[] methods = classType.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
    }
}
