package com.test03;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * 返射的作用是动态(Runtime)创建对象,或通过对象取得class对象的成员信息
 * 其体带不同个数参数的构造方法返射取得对象
 */
public class Test03Class03{
    public Object coppyObject(Object object) throws Exception {
        Class<?> classType = object.getClass();
        Constructor cons = classType.getConstructor(new Class[]{String.class,int.class});
        Object obj = cons.newInstance(new Object[]{"lisu", 20});
        System.out.println(obj);
        return obj;
    }
}
