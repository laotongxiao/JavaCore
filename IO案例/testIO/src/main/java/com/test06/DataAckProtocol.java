package com.test06;

public class DataAckProtocol extends BasicProtocol {
    public static final int PROTOCOL_TYPE = 1;
    @Override
    public int getProtocolType() {
        return PROTOCOL_TYPE;
    }
}
