package com.example.tcpconnection;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiEnterpriseConfig;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.util.List;

public class WifiAdmin {
    // 定义WifiManger对象
    private WifiManager wifiManager;
    //定义WifiInfo对象
    private WifiInfo wifiInfo;
    //扫描出的网络连接列表
    private List<ScanResult> wifiList;
    //网络连接列表
    private List<WifiConfiguration> wifiConfiguration;
    //定义一个WifiLock'
    private WifiManager.WifiLock wifiLock;

    public WifiAdmin(Context context) {
        //取得WifiManger对象
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        //取得WifiInfo对象
        wifiInfo = wifiManager.getConnectionInfo();
    }

    public void openWifi(){
        if(!wifiManager.isWifiEnabled()){
            wifiManager.setWifiEnabled(true);
        }
    }

    public void closeWifi(){
        if(wifiManager.isWifiEnabled()){
            wifiManager.setWifiEnabled(false);
        }
    }

    public int checkWifi(){
        return wifiManager.getWifiState();
    }

    public void acquireWifiLock(){
        wifiLock.acquire();
    }

    public void releaseWifiLock(){
        if(wifiLock.isHeld()){
            wifiLock.acquire();
        }
    }

    public void createWifiLock(){
        wifiLock = wifiManager.createWifiLock("Test");
    }

    public List<WifiConfiguration> getConfiguration(){
        return wifiConfiguration;
    }

    public void connectConfiguration(int index){
        if(index > wifiConfiguration.size()){
            return;
        }
        wifiManager.enableNetwork(wifiConfiguration.get(index).networkId,true);
    }

    public List<ScanResult> getWifiList(){
        return wifiList;
    }

    public StringBuilder lookUPScan() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < wifiList.size(); i++) {
            stringBuilder.append("index" + new Integer(i + 1).toString() + ":");
            stringBuilder.append(wifiList.get(i).toString());
            stringBuilder.append("/n");
        }
        return stringBuilder;
    }

    @SuppressLint("MissingPermission")
    public String getMacAddress(){
        return (wifiInfo == null) ? "NULL" : wifiInfo.getMacAddress();
    }

}
