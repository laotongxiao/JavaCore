package com.test06;

import java.lang.reflect.Array;

/**
 * 多维数组返射
 * Integer.TYPE 表示新创的对像为int[]
 * Integer.Class 表示新创的对像为Interger
 * 返射的作用是动态(Runtime)创建对象,或通过对象取得class对象的成员信息
 */
public class Test06Main06 {
    public static void main(String[] args){
        int[] dims = new int[]{5, 10, 15};
        //构建一个三维数组,维度为 x = 5 ,y = 10, z = 15
        Object array = Array.newInstance(Integer.TYPE, dims);
        //取x为3
        Object arrayNew = Array.get(array,3);
        //取y为5
        arrayNew = Array.get(arrayNew,5);
        //设置坐标为 x = 3, y = 5, z = 10 三维数组所在点值为 37
        Array.set(arrayNew,10, 37);
        int[][][] arrayCast = (int[][][]) array;
        System.out.println(arrayCast[3][5][10]);
    }
}
