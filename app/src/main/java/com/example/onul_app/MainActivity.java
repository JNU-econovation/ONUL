package com.example.onul_app;

import android.nfc.cardemulation.HostNfcFService;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    HomeFragment homeFragment=new HomeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("test", "mainactivity");
        getSupportFragmentManager().beginTransaction().add(R.id.container,homeFragment).commit();
        Log.e("test", "fragment");

    }
}


