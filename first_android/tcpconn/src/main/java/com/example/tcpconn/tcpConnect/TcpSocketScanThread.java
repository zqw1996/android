package com.example.tcpconn.tcpConnect;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.net.InetAddress;

import com.example.tcpconn.frame.Frame;


public class TcpSocketScanThread extends Thread{

    protected InetAddress inetAddress;
    protected int port;
    private Handler handler;

    public TcpSocketScanThread(Handler handler, InetAddress inetAddress, int port) {
        this.inetAddress = inetAddress;
        this.port = port;
        this.handler = handler;
    }

    public TcpSocketScanThread(InetAddress byName, int parseInt) {
        this.port = port;
        this.handler = handler;
    }

    @Override
    public synchronized void start() {
        super.start();
    }

    @Override
    public void run() {
        super.run();
        try {
            TcpSocket tcpSocket = new TcpSocket(inetAddress , 8899);
            if(!tcpSocket.isConnected()){
                return;
            }
            byte[] req = Frame.makeCmdFrm(Frame.PKT_CLS_DEV_INFO,new byte[0]);
            tcpSocket.write(req);
            byte[] data = tcpSocket.read();

            for(int tries=0; tries<15; tries++)
            {
                while(true)
                {
                    byte[] frm = Frame.searchFrame(data);

                    if (frm.length == 0)
                    {
                        break;
                    }
                    else
                    {
                        byte[] pktData = new byte[256];
                        if(Frame.bIsDevInfoFrame(frm , pktData)){
                            Message message = Message.obtain();
                            message.what = 0x12;
                            handler.sendMessage(message);
                        }
                        return;
                    }
                }
                byte[] data1 = tcpSocket.read();
                byte[] s = new byte[data.length + data1.length];
                System.arraycopy(data, 0, s, 0, data.length);
                System.arraycopy(data1, 0, s, data.length, data1.length);
                data = s;
            }
            tcpSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
