package com.test04;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 返射的作用是动态(Runtime)创建对象,或通过对象取得class对象的成员信息
 * 其体带不同个数参数的构造方法返射取得对象
 * 创建对像并对成员变量付值
 */
public class Test04Class04 {
    public Object coppyObject (Object object) throws Exception{
        Class<?> classType = object.getClass();
        Constructor cons = classType.getConstructor(new Class[]{});
        Object obj = cons.newInstance(new Object[]{});
        Field[] fields = classType.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            //取字段第一个字母变大写
            String nameStr = name.substring(0,1).toUpperCase();
            String getMethodName = "get" + nameStr + name.substring(1,name.length());
            String setMethodName = "set" + nameStr + name.substring(1,name.length());
            Method getMethod = classType.getMethod(getMethodName,new Class[]{});
            Method setMethod = classType.getMethod(setMethodName,new Class[]{field.getType()});
            Object value = getMethod.invoke(object,new Object[]{});
            setMethod.invoke(obj,new Object[]{value});
            //System.out.println(setMethodName);
        }
        System.out.println("" + obj);
        return obj;
    }
}
