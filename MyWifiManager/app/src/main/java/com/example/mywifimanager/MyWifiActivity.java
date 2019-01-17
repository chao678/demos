package com.example.mywifimanager;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Scanner;

public class MyWifiActivity extends AppCompatActivity {
    private Context context;
    private WifiManager mWifiManager;
    private LayoutInflater Inflater;
    public Scanner mScanner;

    private TextView wifi_status_txt;
    private Switch wifiSwitch;
    private ListView mlistview;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mywifi);
        context = this;
        mWifiManager=(WifiManager) context.getSystemService(Service.WIFI_SERVICE);
        Inflater= LayoutInflater.from(context);
        mScanner = new Scanner(this);
        linkWifi=new LinkWifi(context);
        initViews();
        registerListener();
    }

    private void initViews() {
        wifi_status_txt = findViewById(R.id.switch_txt);
        wifiSwitch = findViewById(R.id.switch_status);
        layout = findViewById(R.id.ListView_LinearLayout);
    }

    private void registerListener() {
        wifiSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
//                    wifi_status_txt.setText("开启");
                    if (!mWifiManager.isWifiEnabled()) { // 当前wifi不可用
                        mWifiManager.setWifiEnabled(true);
                    }
                    mWifiManager.startScan();
                } else {
//                    wifi_status_txt.setText("关闭");
                    if (mWifiManager.isWifiEnabled()) {
                        mWifiManager.setWifiEnabled(false);
                    }
                }
            }
        });
    }

    /**
     * 广播接收，监听网络
     */
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            // wifi状态发生改变。
            if (action.equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
			/*int wifiState = intent.getIntExtra(
					WifiManager.EXTRA_WIFI_STATE, 0);*/
                int wifiState=intent.getIntExtra(
                        WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);
                updateWifiStateChanged(wifiState);
            } else if (action.equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
                updateWifiList();

            }
        }
    };

    private void updateWifiStateChanged(int state) {
        switch (state) {
            case WifiManager.WIFI_STATE_ENABLING://正在打开WiFi
                wifiSwitch.setEnabled(false);
                Log.i("aaaaaa", "正在打开WiFi");
                break;
            case WifiManager.WIFI_STATE_ENABLED://WiFi已经打开
                //setSwitchChecked(true);
                wifiSwitch.setEnabled(true);
                wifiSwitch.setChecked(true);
                layout.removeAllViews();
                layout.addView(listView);
                mScanner.resume();
                Log.i("aaaaaa", "WiFi已经打开");
                break;
            case WifiManager.WIFI_STATE_DISABLING://正在关闭WiFi
                wifiSwitch.setEnabled(false);
                Log.i("aaaaaa", "正在关闭WiFi");
                break;
            case WifiManager.WIFI_STATE_DISABLED://WiFi已经关闭
                //setSwitchChecked(false);
                wifiSwitch.setEnabled(true);
                wifiSwitch.setChecked(false);
                layout.removeAllViews();
                Log.i("aaaaaa", "WiFi已经关闭  ");
                break;
            default:
                //setSwitchChecked(false);
                wifiSwitch.setEnabled(true);
                break;
        }
        mScanner.pause();//移除message通知
    }
}
