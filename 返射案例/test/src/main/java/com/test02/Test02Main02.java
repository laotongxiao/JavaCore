package com.test02;

import java.lang.reflect.Method;

/**
 * 返射的作用是动态(Runtime)创建对象,或通过对象取得class对象的成员信息
 * new Class[]{}  Object[]{} new出的对象是数组型
 * classType.newInstance() 所在的类有不带参数的构造方法生成
 * getMethod()  只能获取public办法
 */
public class Test02Main02 {
    public static void main(String[] args) throws Exception {
        //获得Class类对象
        Class<?> classType = Test02MyClass01.class;
        //classType 创建一个对象
        Object object = classType.newInstance();
        //获得对应方法test01对象
        Method test01Method = classType.getMethod("test01", new Class[] {int.class, int.class});
        Object result01 = test01Method.invoke(object,new Object[] {1, 2});
        System.out.println((Integer) result01);
        System.out.println("-----------");
        //获得对应方法test02对象
        Method test02Method = classType.getMethod("test02", new Class[]{String.class, String.class});
        Object result02 = test02Method.invoke(object,new Object[] {"hello", "word"});
        System.out.println((String)result02);
    }
}
