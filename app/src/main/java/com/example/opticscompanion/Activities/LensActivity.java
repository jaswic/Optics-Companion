package com.example.opticscompanion.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.Toolbar;

import com.example.opticscompanion.Fragments.DiameterFragment;
import com.example.opticscompanion.Fragments.RadiusFragment;
import com.example.opticscompanion.Fragments.SagFragment;
import com.example.opticscompanion.R;

public class LensActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lens_navigation);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Fragment fragment = new SagFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();
    }

    public void clickRadiusButton(View view){
        Fragment radiusFragment = new RadiusFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, radiusFragment).commit();
    }

    public void clickSagButton(View view){
        Fragment sagFragment = new SagFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, sagFragment).commit();
    }

    public void clickDiameterButton(View view){
        Fragment diameterFragment = new DiameterFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, diameterFragment).commit();
    }
}
