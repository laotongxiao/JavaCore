package com.test05;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ConnectionServer {
    private static boolean isStart = true;
    private static ExecutorService executor = new ThreadPoolExecutor(4,4,60L, TimeUnit.SECONDS,new ArrayBlockingQueue(4));
    public static void main(String[] args){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(Config.PORT);
            while (isStart){
                Socket socket = serverSocket.accept();
                System.out.println("客户端成功接入");
                ServerHandler serverHandler = new ServerHandler(socket, new ServerCallBack() {
                    @Override
                    public void targetIsOffline(String reciveMsg) {
                        //暂时没用
                    }

                    @Override
                    public void targetIsOnline(String clientIp) {
                        System.out.println(clientIp + "is onLine");
                        System.out.println("--------------------------");
                    }
                });
                if(socket.isConnected()){
                    executor.execute(serverHandler);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null){
                    isStart = false;
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
