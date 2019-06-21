package com.test01;

public class TestThread03 extends Thread{
    private TestData01 testData01;

    public TestThread03(TestData01 testData01) {
        this.testData01 = testData01;
    }

    @Override
    public void run() {
        this.testData01.test03();
    }
}
