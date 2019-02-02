package com.example.weatherapp;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Resources res = getResources();
        ListView mylist =(ListView)findViewById(R.id.mylist);
        String[] list = res.getStringArray(R.array.list);
        mylist.setAdapter(new ArrayAdapter<String>(this,R.layout.listview_layout,list));
        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailintent = new Intent(getApplicationContext(),DeviceDetailActivity.class);
                startActivity(detailintent);
            }
        });
    }
}
