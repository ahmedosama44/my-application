package com.example.weatherapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class InternetActivity extends AppCompatActivity implements View.OnClickListener {
    Button internet;
    int internetpermission=2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);
        internet=(Button)findViewById(R.id.internet_button);
        internet.setOnClickListener(this);
    }
    public void onClick(View view) {
        if(view==internet) {
            if(isReadStorageAllowed()){
                Toast.makeText(InternetActivity.this,"You already have the permission",Toast.LENGTH_LONG).show();
                return;
            }
            requestStoragePermission();
        }
    }
    public boolean isReadStorageAllowed() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;
        return false;
    }
    public void requestStoragePermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.INTERNET)){
        }
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET},internetpermission);
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == internetpermission){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permission granted Internet is now accessed",Toast.LENGTH_LONG).show();
                Intent listintent = new Intent(getApplicationContext(), ListActivity.class);
                listintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(listintent);
            }else{
                Toast.makeText(this,"Internet permission is denied",Toast.LENGTH_LONG).show();
            }
        }
    }
}