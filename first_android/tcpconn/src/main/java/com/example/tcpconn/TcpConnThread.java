package com.example.tcpconn;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class TcpConnThread extends Thread{
    private InetAddress ipAddress;
    private int port;
    public InputStream inputStream;
    public OutputStream outputStream;
    public Socket socket;
    public TcpConnThread(InetAddress ipAddress, int port){
        this.ipAddress = ipAddress;
        this.port = port;
    }

    @Override
    public void run() {
        super.run();
        try {
            if(socket != null){
                socket.close();
                socket = null;
            }
            socket = new Socket(ipAddress,port);
            if(!socket.isConnected()){
                return;
            }
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
