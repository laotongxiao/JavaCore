package com.test01;

import org.junit.Test;

public class TestApp {
    @Test
    public void test(){
        byte[] a = TestApp.toHH(127);
        int c = a[0] | a[1] | a[2] | a[3];
        System.out.println(c);
    }
    public static byte[] toHH(int n) {
        byte[] b = new byte[4];
        b[0] = (byte) (n >> 24 & 0xff);
        b[1] = (byte) (n >> 16 & 0xff);
        b[2] = (byte) (n >> 8 & 0xff);
        b[3] = (byte) (n & 0xff);
        return b;
    }
}
