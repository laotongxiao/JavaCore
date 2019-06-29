package com.test01;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class JavaIoOneClient {
    public static void main(String[] args){
        try {
            Socket socket = new Socket("localhost",8899);
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            String clientData;
            Scanner scanner = new Scanner(System.in);
            byte[] bytes = new byte[1024];
            while (true){
                clientData = scanner.next();
                byte[] msgByte = clientData.getBytes();
                int totalLength = msgByte.length;
                dataOutputStream.writeInt(totalLength);
                dataOutputStream.write(msgByte);
                dataOutputStream.flush();
//                while (true){
//                    int red = inputStream.read(bytes);
//                    System.out.println("----" + red);
//                    System.out.println(new String(bytes, 0 ,red));
//                    if(red == 0 ){
//                        break;
//                    }
//                }
                System.out.println("==========");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
