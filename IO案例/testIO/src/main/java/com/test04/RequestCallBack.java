package com.test04;

/**
 * Created by meishan on 16/12/1.
 */
public interface RequestCallBack {

    void onSuccess(BasicProtocol msg);

    void onFailed(int errorCode, String msg);
}