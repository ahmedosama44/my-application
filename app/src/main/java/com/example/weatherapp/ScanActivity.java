package com.example.weatherapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ScanActivity extends AppCompatActivity {
    WifiManager manager;
    ListView listview;
    int size=0;
    List<ScanResult> result;
    ArrayList<String> arrayList=new ArrayList<>();
    CustomAdapter adapter;
    ArrayList<String> SSID=new ArrayList<>();
    ArrayList<String> BSSID=new ArrayList<>();
    int image = R.drawable.esp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        listview =(ListView)findViewById(R.id.wifinames);
        manager=(WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        adapter = new CustomAdapter();
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent devicedetailintent = new Intent(getApplicationContext(), DeviceDetailActivity.class);
                startActivity(devicedetailintent);
            }
        });
        scanWifi();
    }
    public void scanWifi() {
        arrayList.clear();
        Toast.makeText(this,"Scanning Wifi...",Toast.LENGTH_SHORT).show();
        registerReceiver(wifiReciever,new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }
    BroadcastReceiver wifiReciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            result=manager.getScanResults();
            unregisterReceiver(this);
            for(ScanResult scanResult:result) {
                if(scanResult.SSID.contains("MyESP8266AP")) {
                    arrayList.add(scanResult.SSID);
                    SSID.add(scanResult.SSID);
                    BSSID.add("MAC Address: "+scanResult.BSSID);
                    adapter.notifyDataSetChanged();
                }
            }

        }
    };
    class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            view = getLayoutInflater().inflate(R.layout.listviewlayout,null);
            ImageView imageview =(ImageView)view.findViewById(R.id.imageView);
            TextView textview = (TextView)view.findViewById(R.id.textView);
            TextView textview2 = (TextView)view.findViewById(R.id.textView2);
            imageview.setImageResource(image);
            textview.setText(SSID.get(position));
            textview2.setText(BSSID.get(position));
            return view;
        }
    }
}
