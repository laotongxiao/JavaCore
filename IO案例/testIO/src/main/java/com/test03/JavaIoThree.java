package com.test03;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class JavaIoThree {
    private static ExecutorService executor = new ThreadPoolExecutor(2,2,60L,TimeUnit.SECONDS,new ArrayBlockingQueue(2));
    public static void main(String[] args){
        try {
            ServerSocket serverSocket = new ServerSocket(8899);
            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("客户端成功接入");
                executor.execute(new JavaIoThreeHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
