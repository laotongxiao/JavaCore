package com.test05;

import java.io.ByteArrayOutputStream;

public abstract class BasicProtocol {
    //byte是从0开始的
    //协议构成顺序为 LENGTH_LEN + VER_LEN + TYPE_LEN
    //重点注意byte[0]byte[1]byte[2]byte[3]表示LENGTH_LEN
    //byte[4] 表示VER_LEN
    //byte[5] 表示TYPE_LEN
    // 长度均以字节（byte）为单位
    // 长度均以字节（byte）为单位
    public static final int LENGTH_LEN = 4;     //记录整条数据长度数值的长度
    protected static final int VER_LEN = 1;       //协议的版本长度（其中前3位作为预留位，后5位作为版本号）
    protected static final int TYPE_LEN = 1;      //协议的数据类型长度

    private int reserved = 0;                     //预留信息
    private int version = Config.VERSION;         //版本号

    /**
     * 获取整条数据长度
     * 单位：字节（byte）
     *
     * @return
     */
    protected int getLength() {
        return LENGTH_LEN + VER_LEN + TYPE_LEN;
    }

    public int getReserved() {
        return reserved;
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    /**
     * 解析出协议类型
     * @param data 它是协议protocol总数据
     * @return
     */
    public static int parseType(byte[] data) {
        byte t = data[LENGTH_LEN + TYPE_LEN];//前4个字节（0，1，2，3）为数据长度的int值，以及TYPE占一个字节
        //把一个byte变成整数十进制
        return t & 0xFF;
    }

    /**
     * 解析出预留位
     * 因为byte[0]是开始位所以data[LENGTH_LEN]
     * @param data
     * @return
     */
    protected int parseReserved(byte[] data) {
        byte r = data[LENGTH_LEN];//前4个字节（0，1，2，3）为数据长度的int值，与版本号组成一个字节
        return (r >> 5) & 0xFF;
    }

    /**
     * 解析出版本号
     * 因为byte[0]是开始位所以data[LENGTH_LEN]
     * @param data
     * @return
     */
    protected int parseVersion(byte[] data) {
        byte v = data[LENGTH_LEN]; //与预留位组成一个字节
        return ((v << 3) & 0xFF) >> 3;
    }

    /**
     * 获取协议类型，由子类实现
     *
     * @return
     */
    public abstract int getProtocolType();

    /**
     * 由预留值和版本号计算完整版本号的byte[]值
     *reserved = 0, version = 1 , vLen = 5
     * @return
     */
    private int getVer(byte r, byte v, int vLen) {
        int num = 0;
        int rLen = 8 - vLen;
        for (int i = 0; i < rLen; i++) {
            num += (((r >> (rLen - 1 - i)) & 0x1) << (7 - i));
        }
        return num + v;
    }

    /**
     * 拼接发送数据，此处拼接了协议版本、协议类型和数据长度，具体内容子类中再拼接
     * 按顺序拼接
     *
     * @return
     */
    public byte[] genContentData() {
        byte[] length = SocketUtil.int2ByteArrays(getLength());
        byte reserved = (byte) getReserved();
        byte version = (byte) getVersion();
        //reserved = 0, version = 1 , 5
        byte[] ver = {(byte) getVer(reserved, version, 5)};
        byte[] type = {(byte) getProtocolType()};

        ByteArrayOutputStream baos = new ByteArrayOutputStream(LENGTH_LEN + VER_LEN + TYPE_LEN);
        baos.write(length, 0, LENGTH_LEN);
        baos.write(ver, 0, VER_LEN);
        baos.write(type, 0, TYPE_LEN);
        return baos.toByteArray();
    }


    /**
     * 解析接收数据，此处解析了协议版本、协议类型和数据长度，具体内容子类中再解析
     *
     * @param data
     * @return
     * @throws ProtocolException 协议版本不一致，抛出异常
     */
    public int parseContentData(byte[] data) throws ProtocolException {
        int reserved = parseReserved(data);
        int version = parseVersion(data);
        int protocolType = parseType(data);
        if (version != getVersion()) {
            throw new ProtocolException("input version is error: " + version);
        }
        //整条数据长度byte个数,协议的版本长度byte个数,协议的数据类型长度,4+1+1到达的是byte[5]位置
        return LENGTH_LEN + VER_LEN + TYPE_LEN;
    }
}
