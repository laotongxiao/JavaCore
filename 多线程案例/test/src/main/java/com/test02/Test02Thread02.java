package com.test02;

public class Test02Thread02 extends Thread {
    private Test02Data01 test02Data01;

    public Test02Thread02(Test02Data01 test02Data01) {
        this.test02Data01 = test02Data01;
    }

    @Override
    public void run() {
        this.test02Data01.test02();
    }
}
