package com.test02;
/**
 * synchronized(object) 代码块同步锁
 */
public class TestMain02 {
    public static void main(String[] args){
        Test02Data01 test02Data01 = new Test02Data01();
        Test02Thread01 test02Thread01 = new Test02Thread01(test02Data01);
        Test02Thread02 test02Thread02 = new Test02Thread02(test02Data01);
        test02Thread01.start();
        test02Thread02.start();
    }
}
