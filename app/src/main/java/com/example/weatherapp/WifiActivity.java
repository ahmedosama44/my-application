package com.example.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WifiActivity extends AppCompatActivity implements View.OnClickListener {
    WifiManager manager;
    Button wifi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        manager=(WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifi=(Button)findViewById(R.id.wifi_button);
        wifi.setOnClickListener(this);
    }
    public void onClick(View view) {
        if(view==wifi) {
            manager.setWifiEnabled(false);
            manager.setWifiEnabled(true);
            Intent scanintent = new Intent(getApplicationContext(), ScanActivity.class);
            startActivity(scanintent);
        }
    }
}
