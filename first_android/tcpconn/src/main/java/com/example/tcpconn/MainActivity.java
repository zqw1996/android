package com.example.tcpconn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tcpconn.tcpConnect.TcpSocketScanThread;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    public EditText ip;
    public EditText port;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ip = findViewById(R.id.ip_addr);
        port = findViewById(R.id.port_addr);

        Button conn = findViewById(R.id.connect_service);
        conn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    TcpSocketScanThread tcpSocketScanThread = new TcpSocketScanThread(InetAddress.getByName(ip.getText().toString()) , Integer.parseInt(port.getText().toString()));
                    tcpSocketScanThread.start();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}

