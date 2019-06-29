package com.test06;

public interface ClientCallBack {
    void onSuccess(BasicProtocol msg);

    void onFailed(int errorCode, String msg);
}
