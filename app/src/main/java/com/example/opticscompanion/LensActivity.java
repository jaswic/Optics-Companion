package com.example.opticscompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class LensActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lens);

//        Fragment fragment = new SagFragment();
//        getSupportFragmentManager().beginTransaction().add(R.id.lens_fragment_container, fragment).commit();
    }
}
