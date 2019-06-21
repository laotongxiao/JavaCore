package com.test01;

public class TestData01 {
    public synchronized void test01(){
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep((long)(Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test01:" + i);
        }
        System.out.println("-------------");
    }
    public synchronized void test02(){
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep((long)(Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test02:" + i);
        }
    }
    public synchronized void test03(){
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep((long)(Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test03:" + i);
        }
    }
}
