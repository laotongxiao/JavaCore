package com.test07;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

public class MyClientScanner extends Thread {
    private SocketChannel socketChannel;

    public MyClientScanner(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        Thread thread =  Thread.currentThread();
        System.out.println("-----threadName:" + thread.getName());
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        Scanner scanner = new Scanner(System.in);
        while (true){
            String clientData = scanner.next();
            byte[] msgByte = clientData.getBytes();
            byteBuffer.put(msgByte);
            byteBuffer.flip();
            try {
                socketChannel.write(byteBuffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            byteBuffer.clear();
            while (true){
                try {
                    //清空byteBuffer数据
                    byteBuffer.clear();
                    //把数据读到ByteBuffer中
                    int redNumber = socketChannel.read(byteBuffer);
                    if(redNumber <= 0){
                        break;
                    }
                    byteBuffer.flip();
                    Charset charset = Charset.forName("utf-8");
                    String msg = String.valueOf(charset.decode(byteBuffer).array());
                    System.out.println("读取到服务器回复数据msg:" + msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
