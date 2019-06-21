package com.test02;

public class Test02Thread01 extends Thread {
    private Test02Data01 test02Data01;

    public Test02Thread01(Test02Data01 test02Data01) {
        this.test02Data01 = test02Data01;
    }

    @Override
    public void run() {
        this.test02Data01.test01();
    }
}
