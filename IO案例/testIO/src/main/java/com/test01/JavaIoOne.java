package com.test01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

public class JavaIoOne {
    public static void main(String[] args){
        try {
            ServerSocket serverSocket = new ServerSocket(8899);
            while (true){
                Socket socket= serverSocket.accept();
                System.out.println("成功接入一个客户端");
                InputStream inputStream = socket.getInputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);
                //OutputStream outputStream = socket.getOutputStream();
                byte[] bytes = new byte[1024];
                int red = 0;
                int tempe = 0;
                int totalLength = 0;
                while (true){
                    //System.out.println("读前");

                    if(tempe == 0) {
                        totalLength = dataInputStream.readInt();
                    }
                    System.out.println("----------" + totalLength);
                    red = dataInputStream.read(bytes);
                    tempe = tempe + red;
                    System.out.println(new String(bytes, 0, red));
                    //System.out.println("读后");
                    if(tempe == totalLength){
                        red = 0;
                        tempe = 0;
                        totalLength = 0;
                        System.out.println("------进入给客户端返回数据区");
                        //break;
                    }
                }
                //System.out.println("---退出读取-----");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
