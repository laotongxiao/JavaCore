package com.test04;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * Created by meishan on 16/12/1.
 */
public class ConnectionServer {

    private static boolean isStart = true;
    private static ServerResponseTask serverResponseTask;

    public ConnectionServer() {

    }

    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        ExecutorService executorService = new ThreadPoolExecutor(2,2,60L, TimeUnit.SECONDS,new ArrayBlockingQueue(2));
        try {
            serverSocket = new ServerSocket(Config.PORT);
            while (isStart) {
                Socket socket = serverSocket.accept();
                System.out.println("接入客户端.");
                serverResponseTask = new ServerResponseTask(socket,
                        new ResponseCallback() {

                            @Override
                            public void targetIsOffline(DataProtocol reciveMsg) {// 对方不在线
                                if (reciveMsg != null) {
                                    System.out.println(reciveMsg.getData());
                                }
                            }

                            @Override
                            public void targetIsOnline(String clientIp) {
                                System.out.println(clientIp + " is onLine");
                                System.out.println("-----------------------------------------");
                            }
                        });

                if (socket.isConnected()) {
                    executorService.execute(serverResponseTask);
                }
            }

            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    isStart = false;
                    serverSocket.close();
                    if (serverSocket != null) {
                        serverResponseTask.stop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}