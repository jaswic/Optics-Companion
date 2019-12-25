package com.example.opticscompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;

public class ProcessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);

        Fragment fragment = new RpmFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();
    }

    public void clickDiameterButton(View view){

    }

    public void clickCuttingSpeedButton(View view){
        Fragment fragment = new CuttingSpeedFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    public void clickRpmButton(View view){
        Fragment fragment = new RpmFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }
}
