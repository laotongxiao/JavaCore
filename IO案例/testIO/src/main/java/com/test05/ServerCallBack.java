package com.test05;

public interface ServerCallBack {
    void targetIsOffline(String reciveMsg);
    void targetIsOnline(String clientIp);
}
