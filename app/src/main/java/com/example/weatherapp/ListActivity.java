package com.example.weatherapp;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity {
    int[] Images={R.drawable.sql,R.drawable.java,R.drawable.javascript,R.drawable.c,R.drawable.python,R.drawable.cplusplus,R.drawable.php};
    String[] Names={"SQL","JAVA","JAVA SCRIPT","C#","PYTHON","C++","PHP"};
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listview = (ListView)findViewById(R.id.listView);
        CustomAdapter custom = new CustomAdapter();
        listview.setAdapter(custom);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent devicedetailintent = new Intent(getApplicationContext(), DeviceDetailActivity.class);
                startActivity(devicedetailintent);
            }
        });

    }
    class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return Images.length;
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
            imageview.setImageResource(Images[position]);
            textview.setText(Names[position]);
            textview.setText(Names[position]);
            return view;
        }
    }
}