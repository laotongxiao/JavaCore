package com.test07;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.UUID;

public class MyServer {
    public static void main(String[] args){
        try {
            //创建ServerSocketChannel通道
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            //通道设为非阻塞
            serverSocketChannel.configureBlocking(false);
            ServerSocket serverSocket = serverSocketChannel.socket();
            //绑定端口号
            serverSocket.bind(new InetSocketAddress(8899));
            //创建Select对象
            Selector selector = Selector.open();
            //把serverSocketChannel注册到Selector上 int是OP_ACCEPT
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true){
                //阻塞状态等待响应事件发生
                selector.select();
                //取得SelectionKey集合 注意服务器为selector.selectedKeys()客户端是selector.keys()
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                //编历出每个SelectionKey
                selectionKeys.forEach(selectionKey -> {
                    //定义一个SocetChanle变量
                    final SocketChannel socketChannel;
                    //如果是建立连接事件发生
                    if(selectionKey.isAcceptable()){
                        try {
                            //从selectionKey获取得相应的serverSocketChannel通道
                            ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();
                            //从serverSocketChannel取得新建立的SocketChang通道
                            socketChannel = serverChannel.accept();
                            //把socketChannel设置为非阻塞状态
                            socketChannel.configureBlocking(false);
                            //把新建立的socketChannel通道注册到Select中
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            System.out.println("客户端连接接入成功!");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else if(selectionKey.isReadable()){
                        try {
                            //从selectionKey取得新建立的SocketChang通道
                            socketChannel = (SocketChannel) selectionKey.channel();
                            //创建一个ByteBuffer存贮读进的数据
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            while (true){
                                //清空byteBuffer数据
                                byteBuffer.clear();
                                //把数据读到ByteBuffer中
                                int rednumber = socketChannel.read(byteBuffer);
                                if(rednumber <= 0){
                                    break;
                                }
                                //从ByteBuffer中读出数据反转
                                byteBuffer.flip();
                                //创建字符集
                                Charset charset = Charset.forName("utf-8");
                                String msg = String.valueOf(charset.decode(byteBuffer).array());
                                System.out.println("msg:" + msg);
                            }
                            //给客户端写数据,注意telnet是阻塞有点不正常
                            byteBuffer.clear();
                            String responseMsg = UUID.randomUUID().toString();
                            byteBuffer.put(responseMsg.getBytes());
                            byteBuffer.flip();
                            socketChannel.write(byteBuffer);
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                });
                //重点是对它一次完成清空Set<SelectionKey> selectionKeys = selector.selectedKeys()
                selectionKeys.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
