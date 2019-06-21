package com.test05;

import java.lang.reflect.Array;

/**
 * 一维数组
 * 返射的作用是动态(Runtime)创建对象,或通过对象取得class对象的成员信息
 */
public class Test05Main05 {
    public static void main(String[] args) throws Exception{
        Class<?> classType = Class.forName("java.lang.String");
        Object array = Array.newInstance(classType, 10);
        //给数组第5个元素付值
        Array.set(array,5,"hello");
        //取出数组第5个元素值
        String str = (String) Array.get(array,5);
        System.out.println(str);
    }
}
