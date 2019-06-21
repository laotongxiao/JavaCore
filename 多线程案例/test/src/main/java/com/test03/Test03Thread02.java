package com.test03;

public class Test03Thread02 extends Thread {
    private Test03Data01 test03Data01;

    public Test03Thread02(Test03Data01 test03Data01) {
        this.test03Data01 = test03Data01;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                sleep((long)(Math.random()*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            test03Data01.delnumber();
        }
    }
}
