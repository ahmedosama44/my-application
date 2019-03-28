package com.example.weatherapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeviceDetailActivity extends AppCompatActivity {
    String SSID="";
    String Mac="";
    TextView name;
    TextView macaddress;
    TextView reading;
    DatabaseReference firebasedatabase;
    DatabaseReference myref;
    int value;
    int k;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_detail);
        FirebaseApp.initializeApp(DeviceDetailActivity.this);
        name=(TextView)findViewById(R.id.ssid_textView);
        macaddress=(TextView)findViewById(R.id.mac_textView);
        reading=(TextView)findViewById(R.id.reading_textView);
        String data= getIntent().getStringExtra("DATA");
        for(int i=0;i<data.length();i++) {
            if(data.charAt(i)=='-') {
                k=i;
            }
        }
        for(int i=0;i<k;i++) {
            SSID=SSID+data.charAt(i);
        }
        for(int i=k+1;i<data.length();i++) {
            Mac=Mac+data.charAt(i);
        }
        name.setText("SSID: "+SSID);
        macaddress.setText("MAC ADDRESS: "+Mac.toUpperCase());
        firebasedatabase= FirebaseDatabase.getInstance().getReference().child(Mac.toUpperCase());
        reading.setText("Still Reading....");
    }
    public void onStart() {
        super.onStart();
        firebasedatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    Float value = snapshot.getValue(Float.class);
                    reading.setText("Temperature Reading: "+value+"");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}