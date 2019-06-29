package com.test02;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class JavaIoTwoHandler extends Thread{
    private Socket socket;

    public JavaIoTwoHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("当前线程名:" + t.getName());
        try {
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[2];
            while (true){
                System.out.println("开始读数据前");
                int red = inputStream.read(bytes);
                System.out.println("开始读数据后");
                if (red != -1){
                    System.out.println(new String(bytes,0, red));
                }else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
