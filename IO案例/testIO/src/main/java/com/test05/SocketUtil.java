package com.test05;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SocketUtil {
    private static Map<Integer, String> msgImp = new HashMap<>();

    static {
        msgImp.put(DataProtocol.PROTOCOL_TYPE, "com.test05.DataProtocol");       //0
        msgImp.put(DataAckProtocol.PROTOCOL_TYPE, "com.test05.DataAckProtocol"); //1
        msgImp.put(PingProtocol.PROTOCOL_TYPE, "com.test05.PingProtocol");       //2
        msgImp.put(PingAckProtocol.PROTOCOL_TYPE, "com.test05.PingAckProtocol"); //3
    }
    /**
     * 读数据
     * 功能1服务器从客户端读取协议数据
     * 功能2客户端从服务器读取协议数据
     * @param inputStream 注意前面传过来是包装流DataInputStream 对应下现的inputstream
     * @return
     */
    public static BasicProtocol redFromStream(InputStream inputStream){
        BasicProtocol protocol;
        BufferedInputStream bufferedInputStream;    //增加缓冲功能,避免频繁读写硬盘

        //header中保存的是整个数据的长度值，4个字节表示。在下述write2Stream方法中，会先写入header
        byte[] header = new byte[BasicProtocol.LENGTH_LEN];

        //再一次包装流,把DataInputStream包装成BufferedInputStream
        bufferedInputStream = new BufferedInputStream(inputStream);
        int temp = 0;
        int len = 0;

        try {
            //第1步 先读取一条请求协议长度字节
            while (len < header.length){
                //从包装流读取header.length - len个长度数据到header的数组中,它是表示协议总长度用4个字节存贮
                //len 表示从len这个位置开始读取
                temp = bufferedInputStream.read(header, len, header.length - len);
                if(temp > 0){
                    len += temp;
                }else if(temp == -1){
                    bufferedInputStream.close();
                    //这个是一定要返回值的因为这方法要有返回置,要不就会抛异常
                    return null;
                }
            }

            //第2步 再读取此条协议剩下字节,那么一条协议就读取完成
            len = 0;
            //取得整条协议包含的总byte数
            int length = byteArrayToInt(header);
            byte[] protocolBytes = new byte[length];
            while (len < length){
                temp = bufferedInputStream.read(protocolBytes, len, length - len);
                if(temp > 0){
                    len += temp;
                }
            }
            //解析协议
            protocol = parseProtocolMsg(protocolBytes);
        }catch (IOException e){
            e.printStackTrace();
            //这个是一定要返回值的因为这方法要有返回置,抛异出现就返回null
            return null;
        }
        //返回的是解析的协议
        return protocol;
    }

    /**
     * 写数据
     *
     * @param protocol
     * @param outputStream
     */
    public static void write2Stream(BasicProtocol protocol, OutputStream outputStream) {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        byte[] buffData = protocol.genContentData();
        byte[] header = int2ByteArrays(buffData.length);
        try {
            bufferedOutputStream.write(header);
            bufferedOutputStream.write(buffData);
            bufferedOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭输入流
     *
     * @param is
     */
    public static void closeInputStream(InputStream is) {
        try {
            if (is != null) {
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭输出流
     *
     * @param os
     */
    public static void closeOutputStream(OutputStream os) {
        try {
            if (os != null) {
                os.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //bye[4]个字节数组还原成十进制表示
    public static int byteArrayToInt(byte[] b) {
        int intValue = 0;
        for (int i = 0; i < b.length; i++) {
            intValue += (b[i] & 0xFF) << (8 * (3 - i)); //int占4个字节（0，1，2，3）
        }
        return intValue;
    }
    //bye[n]n<=4个字节数组还原成十进制表示
    public static int byteArrayToInt(byte[] b, int byteOffset, int byteCount) {
        int intValue = 0;
        for (int i = byteOffset; i < (byteOffset + byteCount); i++) {
            intValue += (b[i] & 0xFF) << (8 * (3 - (i - byteOffset)));
        }
        return intValue;
    }

    /**
     * 将int转为大端，低字节存储高位(即[0]>> 24)  Java默认的也是大端 tcp规定的也是大端
     */
    public static byte[] int2ByteArrays(int i) {
        byte[] result = new byte[4];
        result[0] = (byte) ((i >> 24) & 0xFF);
        result[1] = (byte) ((i >> 16) & 0xFF);
        result[2] = (byte) ((i >> 8) & 0xFF);
        result[3] = (byte) (i & 0xFF);
        return result;
    }

    /**
     * 解析协议内容
     * @param data
     * @return
     * 对象包含了 protocolType协议类型
     * -----------------------用反射创建一个类型相同的协议空对象
     * parseContentData(data) 调用其方法进得付值它是子类方法,不是父类方法
     */
    public static BasicProtocol parseProtocolMsg(byte[] data) {
        //取得协议类型(并把bye还原成int)
        int protocolType = BasicProtocol.parseType(data);
        //根据类型从map中找到协议实现类的类名给下面返射构建相应协议对象
        String className = msgImp.get(protocolType);
        //协议其体类是继承基础类BasicProtocol它是抽象类
        BasicProtocol basicProtocol;
        try {
            //把协议其体类转为它父类类型BasicProtocol
            basicProtocol = (BasicProtocol) Class.forName(className).newInstance();
            //调用这个parseContentData方法是子类方法,因为子类从写了父类,且它本质就是子类只是被强转为父类型
            basicProtocol.parseContentData(data);
        } catch (Exception e) {
            basicProtocol = null;
            e.printStackTrace();
        }
        return basicProtocol;
    }
}
