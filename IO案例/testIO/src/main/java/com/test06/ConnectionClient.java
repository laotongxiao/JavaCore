package com.test06;

public class ConnectionClient {
    private boolean isClosed;

    private ClientHandler clientHandler;

    public ConnectionClient(ClientCallBack clientCallBack) {
        clientHandler = new ClientHandler(clientCallBack);
        new Thread(clientHandler).start();
    }

    public void addNewRequest(DataProtocol data) {
        if (clientHandler != null && !isClosed) {
            clientHandler.addRequest(data);
        }
    }

    public void closeConnect() {
        isClosed = true;
        clientHandler.stop();
    }
    public static void main(String[] args){

        new ConnectionClient(new ClientCallBackImp());
    }
}
