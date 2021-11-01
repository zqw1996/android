package com.example.tcpconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    ServerSocket serverSocket;//创建ServerSocket对象
    Socket clicksSocket;//连接通道，创建Socket对象
    Button startButton;//发送按钮
    EditText portEditText;//端口号
    EditText receiveEditText;//接收消息框
    Button sendButton;//发送按钮
    EditText sendEditText;//发送消息框
    InputStream inputstream;//创建输入数据流
    OutputStream outputStream;//创建输出数据流
    InetAddress inetAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* 查询手机wifi状态下的ip地址 ， 只有知道ip才能连接*/
        System.out.println(getLocalIpAddress(MainActivity.this) + "ip地址");
        Log.i("mainActivity" ,getLocalIpAddress(MainActivity.this) + "ip地址");
        startButton = (Button) findViewById(R.id.start_button);
        portEditText = (EditText) findViewById(R.id.port_EditText);
        receiveEditText = (EditText) findViewById(R.id.receive_EditText);
        sendButton = (Button) findViewById(R.id.send_button);
        sendEditText = (EditText) findViewById(R.id.message_EditText);

        startButton.setOnClickListener(startButtonListener);
        sendButton.setOnClickListener(sendButtonListener);

    }

    private View.OnClickListener startButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ServerScoket_thread serverScoket_thread = new ServerScoket_thread();
            serverScoket_thread.start();
            //Toast.makeText(MainActivity.this , "服务启动" ,Toast.LENGTH_SHORT).show();
        }
    };


    /**
     * 服务器监听线程
     */
    class ServerScoket_thread extends Thread
    {
        @Override
        public void run()//重写Thread的run方法
        {
            try
            {
                Log.i("ServerScoket_thread" ,"服务开启");
                int port =Integer.valueOf(portEditText.getText().toString());//获取portEditText中的端口号
                serverSocket = new ServerSocket(port);//监听port端口，这个程序的通信端口就是port了
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            while (true)
            {
                try
                {
                    //监听连接 ，如果无连接就会处于阻塞状态，一直在这等着
                    Log.i("ServerScoket_thread" ,"服务监听");
                    clicksSocket = serverSocket.accept();
                    Log.i("ServerScoket_thread" ,"监听完成");
                    inputstream = clicksSocket.getInputStream();//
                    //启动接收线程
                    Receive_Thread receive_Thread = new Receive_Thread();
                    receive_Thread.start();
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *
     * 接收线程
     *
     */
    class Receive_Thread extends Thread//继承Thread
    {
        public void run()//重写run方法
        {
            while (true)
            {
                try
                {
                    final byte[] buf = new byte[1024];
                    final int len = inputstream.read(buf);
                    runOnUiThread(new Runnable()
                    {
                        public void run()
                        {
                            receiveEditText.setText(new String(buf,0,len));
                        }
                    });
                }
                catch (Exception e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 发送消息按钮事件
     */
    private View.OnClickListener sendButtonListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            try
            {
                //获取输出流
                outputStream = clicksSocket.getOutputStream();
                //发送数据
                outputStream.write(sendEditText.getText().toString().getBytes());
                //outputStream.write("0".getBytes());
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };

    /**
     * 获取wifi下的ip地址
     */
    private String getLocalIpAddress(Context context){
        WifiManager wifiManager = (WifiManager)context.getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();

        int ipAddress = wifiInfo.getIpAddress();

        return String.format("%d.%d.%d.%d" ,
                (ipAddress & 0xff) ,
                (ipAddress >> 8 & 0xff),
                (ipAddress >> 16 & 0xff),
                (ipAddress >> 24 & 0xff));
    }

}