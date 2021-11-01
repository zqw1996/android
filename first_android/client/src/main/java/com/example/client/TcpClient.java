package com.example.client;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class TcpClient {

    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    public TcpClient() {
    }


    public TcpClient setSocket(String ip , int port) throws IOException {
        InetAddress ipAddress = InetAddress.getByName(ip);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket(ipAddress , port);
                    Log.i("TcpSocket", "连接成功");
                    inputStream = socket.getInputStream();
                    outputStream = socket.getOutputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        return this;
    }

    public void sendMessage(String message){
        if(message.length() == 0 || outputStream == null)
            return;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.i("TcpClient" , message);
                    outputStream.write(message.getBytes());
                    Log.i("TcpClient" , "发送成功");
                    outputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public String receiveMessage(){
        byte[] buffer = new byte[1024];
        try {
            int count = inputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

}
