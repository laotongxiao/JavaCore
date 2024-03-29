package com.test04;


/**
 * Created by meishan on 16/12/1.
 */
public class ConnectionClient {

    private boolean isClosed;

    private ClientRequestTask mClientRequestTask;

    public ConnectionClient(RequestCallBack requestCallBack) {
        mClientRequestTask = new ClientRequestTask(requestCallBack);
        new Thread(mClientRequestTask).start();
    }

    public void addNewRequest(DataProtocol data) {
        if (mClientRequestTask != null && !isClosed) {
            mClientRequestTask.addRequest(data);
        }
    }

    public void closeConnect() {
        isClosed = true;
        mClientRequestTask.stop();
    }
    public static void main(String[] args){
        new ConnectionClient(new RequestCallBackImp());
    }
}