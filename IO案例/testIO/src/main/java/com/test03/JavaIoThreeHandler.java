package com.test03;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class JavaIoThreeHandler extends Thread{
    private Socket socket;

    public JavaIoThreeHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[2];
            while (true){
                int red = inputStream.read(bytes);
                if (red != -1){
                    System.out.println(new String(bytes, 0, red));
                }else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
