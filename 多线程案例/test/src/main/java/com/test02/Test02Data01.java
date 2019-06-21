package com.test02;

public class Test02Data01 {
    private Object object = new Object();
    public void test01(){
        synchronized(object){
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep((long)(Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("test01:" + i);
            }
            System.out.println("------------");
        }
    }
    public synchronized void test02(){
        synchronized(object){
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep((long)(Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("test02:" + i);
            }
        }
    }
}
