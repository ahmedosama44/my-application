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

public class PermissionActivity extends AppCompatActivity implements View.OnClickListener{
    Button internet;
    Button location;
    int locationpermission=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        location=(Button)findViewById(R.id.location_button);
        location.setOnClickListener(this);
    }
    public void onClick(View view) {
        if(view==location) {
            if(isReadStorageAllowed()){
                Toast.makeText(PermissionActivity.this,"You already have the permission",Toast.LENGTH_LONG).show();
                Intent internetintent = new Intent(getApplicationContext(), WifiActivity.class);
                startActivity(internetintent);
                return;
            }
            requestStoragePermission();
        }
    }
    public boolean isReadStorageAllowed() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;
        return false;
    }
    public void requestStoragePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
        }
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},locationpermission);
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == locationpermission){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permission granted Location is now accessed",Toast.LENGTH_LONG).show();
                Intent internetintent = new Intent(getApplicationContext(), WifiActivity.class);
                startActivity(internetintent);
            }else{
                Toast.makeText(this,"Location permission is denied",Toast.LENGTH_LONG).show();
            }
        }
    }
}