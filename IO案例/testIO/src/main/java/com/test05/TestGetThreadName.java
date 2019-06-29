package com.test05;

public class TestGetThreadName {
    public static void getName(){
        Thread threadName = Thread.currentThread();
        System.out.println("----:" + threadName.getName());
    }
}
