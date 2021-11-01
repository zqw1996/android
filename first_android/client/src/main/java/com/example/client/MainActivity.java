package com.example.client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.ServerSocket;

public class MainActivity extends AppCompatActivity {

    private Button connService;
    private Button send;
    private EditText sendMess;
    private EditText receiveMess;
    private EditText ipAddress;
    private EditText port;

    private TcpClient tcpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connService = findViewById(R.id.connect_button);
        send = findViewById(R.id.send_button);
        ipAddress = findViewById(R.id.ip_EditText);
        port = findViewById(R.id.port_EditText);
        sendMess = findViewById(R.id.message_EditText);
        receiveMess = findViewById(R.id.receive_EditText);

        connService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tcpClient = new TcpClient();
                try {
                    Log.i("MainActivity" , "连接服务器");
                    tcpClient.setSocket(ipAddress.getText().toString() , Integer.parseInt(port.getText().toString()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("MainActivity", "发送消息");
                tcpClient.sendMessage(sendMess.getText().toString());
            }
        });


    }


}