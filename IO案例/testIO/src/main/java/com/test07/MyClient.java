package com.test07;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyClient {
    private static ExecutorService executor = new ThreadPoolExecutor(2,2,60L, TimeUnit.SECONDS,new ArrayBlockingQueue(2));
    public static void main(String[] args){
        try {
            //创建SocketChannel通道
            SocketChannel socketChannel = SocketChannel.open();
            //通道设为非阻塞
            socketChannel.configureBlocking(false);
            //设置联接端口号
            socketChannel.connect(new InetSocketAddress(8899));

            //创建Selector
            Selector selector = Selector.open();
            //把socketChannel通道注册到selector中 int是OP_CONNECT
            socketChannel.register(selector, SelectionKey.OP_CONNECT);

            while (true){
                //阻塞状态等待响应事件发生
                selector.select();
                //取得SelectionKey集合 注意客户端是selector.keys()服务器为selector.selectedKeys()
                Set<SelectionKey> selectionKeys = selector.keys();
                selectionKeys.forEach(selectionKey -> {
                    //定义一个SocetChanle变量
                    final SocketChannel socketChannelClient;
                    if(selectionKey.isConnectable()){
                        //从selectionKey获取得相应的SocketChannel通道
                        socketChannelClient =(SocketChannel) selectionKey.channel();
                        //是否处于连接过程当中
                        if(socketChannelClient.isConnectionPending()){
                            try {
                                //连接完成
                                socketChannelClient.finishConnect();
                                //创建一个ByteBuffer存贮读进的数据
                                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                //键盘用线程管理
                                executor.execute(new MyClientScanner(socketChannelClient));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
