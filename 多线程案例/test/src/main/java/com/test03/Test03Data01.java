package com.test03;

public class Test03Data01 {
    private int number;
    public synchronized void addnumber(){
        while (number != 0 ){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number ++;
        System.out.println("addnumber:" + number);
        notify();
    }
    public synchronized void delnumber(){
        while (number == 0 ){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number --;
        System.out.println("delnumber:" + number);
        notify();
    }
}
