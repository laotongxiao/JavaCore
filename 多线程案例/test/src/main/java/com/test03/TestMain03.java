package com.test03;

/**
 * 死锁问题
 * wait与notify方法都是定义在Object类中，而且是final的，
 * 因此会被所有的Java类所继承并且无法重写。这两个方法要求在调用时线程应该已经获得了对象的锁，
 * 因此对这两个方法的调用需要放在synchronized方法或块当中。当线程执行了wait方法时，
 * 它会释放掉对象的锁。
 */
public class TestMain03 {
    public static void main(String[] args){
        Test03Data01 test03Data01 = new Test03Data01();
        Test03Thread01 addt1 = new Test03Thread01(test03Data01);
        Test03Thread02 delt2 = new Test03Thread02(test03Data01);
        Test03Thread01 addt3 = new Test03Thread01(test03Data01);
        Test03Thread02 delt4 = new Test03Thread02(test03Data01);
        addt1.start();
        delt2.start();
        addt3.start();
        delt4.start();
    }
}
