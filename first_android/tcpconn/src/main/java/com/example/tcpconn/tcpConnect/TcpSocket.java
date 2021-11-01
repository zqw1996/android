package com.example.tcpconn.tcpConnect;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Arrays;

public class TcpSocket {

    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    public TcpSocket(InetAddress inetAddress, int port) throws IOException {
        socket = new Socket(inetAddress , port);
        if(socket != null){
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        }
    }

    boolean isConnected(){
        return socket.isConnected();
    }

    public byte[] read() throws IOException {
        byte[] buffer = new byte[1086];
        int sum = inputStream.read(buffer);
        return Arrays.copyOfRange(buffer , 0 , sum);
    }

    public void write(byte[] data) throws IOException {
        outputStream.write(data);
        outputStream.flush();
    }

    public Socket getSocket(){
        return socket;
    }

    public void close() throws IOException {
        inputStream.close();
        outputStream.close();
        socket.close();
    }

}
