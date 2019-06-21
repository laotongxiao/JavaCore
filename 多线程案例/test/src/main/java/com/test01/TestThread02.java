package com.test01;

public class TestThread02 extends Thread {
    private TestData01 testData01;

    public TestThread02(TestData01 testData01) {
        this.testData01 = testData01;
    }

    @Override
    public void run() {
        this.testData01.test02();
    }
}
