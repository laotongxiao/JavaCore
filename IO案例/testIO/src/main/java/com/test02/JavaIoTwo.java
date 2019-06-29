package com.test02;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class JavaIoTwo {
    public static void main(String[] args){
        try {
            ServerSocket serverSocket = new ServerSocket(8899);
            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("成功接入一个客户端");
                //它的实现是一个对象用一个线程管理,RunAble可以做到一个对象多个线程管理
                new JavaIoTwoHandler(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
