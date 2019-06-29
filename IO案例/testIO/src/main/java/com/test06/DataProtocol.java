package com.test06;

public class DataProtocol extends BasicProtocol {
    public static final int PROTOCOL_TYPE = 0;
    @Override
    public int getProtocolType() {
        return PROTOCOL_TYPE;
    }
}