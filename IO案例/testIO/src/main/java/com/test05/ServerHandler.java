package com.test05;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 服务器处理器
 */
public class ServerHandler implements Runnable {
    private ReciveTask reciveTask;
    private SendTask sendTask;
    private Socket socket;
    private ServerCallBack serverCallBack;
    private String userIp;  //它也是clientIp

    private volatile ConcurrentLinkedQueue<BasicProtocol> dataQueue = new ConcurrentLinkedQueue<>();

    public ServerHandler(Socket socket, ServerCallBack serverCallBack) {
        this.socket = socket;
        this.serverCallBack = serverCallBack;
        this.userIp = socket.getInetAddress().getHostAddress();
        System.out.println("用户IP地址：" + userIp);
    }

    @Override
    public void run() {
        try {
            //开启接收线程
            reciveTask = new ReciveTask();
            reciveTask.dataInputStream = new DataInputStream(socket.getInputStream());
            reciveTask.start();

            //开启发送线程
            sendTask = new SendTask();
            sendTask.outputStream = new DataOutputStream(socket.getOutputStream());
            sendTask.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        TestGetThreadName.getName();
    }
    //对象所处的线程进得等待
    public void toWaitAll(Object o) {
        synchronized (o) {
            try {
                o.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    //对象所处的线程被唤醒
    public void toNotifyAll(Object obj) {
        synchronized (obj) {
            obj.notifyAll();
        }
    }

    private boolean isConnectedMy() {
        if (socket.isClosed() || !socket.isConnected()) {
            //onLineClient.remove(userIP);
            //ServerResponseTask.this.stop();
            System.out.println("socket closed...");
            return false;
        }
        return true;
    }

    //接收任务
    public class ReciveTask extends Thread{
        private DataInputStream dataInputStream;       //DataInputStream提供将基础数据类型写入
        private boolean isCanle = false;
        @Override
        public void run() {
            while (!isCanle){
                if(!isConnectedMy()){
                    isCanle = true;
                    break;
                }
                //SocketUtil.redFromStream读取客户端数据
                //clientData它只是一个有类型的空对象,因为返射构造对象暂时没加数据
                BasicProtocol clientData = SocketUtil.redFromStream(dataInputStream);
                if (clientData != null) {
                    if (clientData.getProtocolType() == 0) {

                    } else if (clientData.getProtocolType() == 2) {
                        System.out.println("pingId: " + ((PingProtocol) clientData).getPingId());

                        PingAckProtocol pingAck = new PingAckProtocol();
                        pingAck.setUnused("收到心跳");
                        dataQueue.offer(pingAck);
                        toNotifyAll(dataQueue); //唤醒发送线程

                        serverCallBack.targetIsOnline(userIp);
                    }
                } else {
                    System.out.println("client is offline...");
                    break;
                }

            }
        }
    }
    //发送任务
    public class SendTask extends Thread{
        private DataOutputStream outputStream;
        private boolean isCancle;

        @Override
        public void run() {
            while (!isCancle) {
                if (!isConnectedMy()) {
                    isCancle = true;
                    break;
                }

                BasicProtocol procotol = dataQueue.poll();
                if (procotol == null) {
                    //重要点对象所在线程处于等待 循环也停在这,和Sleep区别是循环可以向下进行
                    toWaitAll(dataQueue);
                } else if (outputStream != null) {
                    synchronized (outputStream) {
                        SocketUtil.write2Stream(procotol, outputStream);
                    }
                }
            }

            SocketUtil.closeOutputStream(outputStream);
        }
    }

}
