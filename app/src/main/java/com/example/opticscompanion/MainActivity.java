package com.example.opticscompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void chooseLensActivity(View v){
        Intent intent = new Intent(this, LensActivity.class);
        startActivity(intent);
    }

    public void chooseProcessActivity(View v){
        Intent intent = new Intent(this, ProcessActivity.class);
        startActivity(intent);
    }

    public void chooseZygoActivity(View v){
        Intent intent = new Intent(this, ZygoActivity.class);
        startActivity(intent);
    }
}
