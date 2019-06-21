package com.test01;

/**
 * synchronized 方法加上就锁住创建对象
 * synchronized static 方法加上就锁住类对象
 */
public class TestMain01 {
    public static void main(String[] args){
        TestData01 testData01 = new TestData01();
        TestThread01 testThread01 = new TestThread01(testData01);
        TestThread02 testThread02 = new TestThread02(testData01);
        TestThread03 testThread03 = new TestThread03(testData01);
        testThread01.start();
        testThread02.start();
        testThread03.start();
    }
}
