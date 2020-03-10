package com.example.opticscompanion.Activities;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.opticscompanion.Fragments.EntryFragment;
import com.example.opticscompanion.R;
import com.example.opticscompanion.Utils.Center;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class CenteringActivity_Nav extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener {
    private int shape=0;
    private double upperRadius;
    private double upperBellDiameter;
    private double lowerRadius;
    private double lowerBellDiameter;
    private double centerThickness;
    private double centeringValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centering_navigation);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Hide result views
        View arrow = findViewById(R.id.centeringArrow);
        arrow.setVisibility(View.INVISIBLE);
        TextView resultText = findViewById(R.id.centeringResultText);
        resultText.setVisibility(View.INVISIBLE);

        //Add drawer menu icon and drawer toggle to appbar
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.nav_open_drawer, R.string.nav_close_drawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Set the activity to be a listener for menu item selection
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Set up spinner
        Spinner shape  = findViewById(R.id.spinnerLensShape);
        shape.setOnItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;
        Intent intent = null;

        switch (id) {
            case R.id.nav_spheres:
                intent = new Intent(this, LensActivity_Nav.class);
                break;
            case R.id.nav_centering:
                intent = new Intent(this, CenteringActivity_Nav.class);
                break;
            case R.id.nav_zygo:
                intent = new Intent(this, ZygoActivity_Nav.class);
                break;
            case R.id.nav_process:
                intent = new Intent(this, ProcessActivity_Nav.class);
                break;

            default:
                fragment = new EntryFragment();
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        } else {
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed(){
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //set shape value
        shape = (int) id;

        //deactivate any unnecessary views
        EditText upperRadiusText = (EditText) findViewById(R.id.upperRadiusEditText);
        EditText lowerRadiusText = (EditText) findViewById(R.id.lowerRadiusEditText);
        EditText upperBellText = (EditText) findViewById(R.id.upperBellDiameter);
        EditText lowerBellText = (EditText) findViewById(R.id.lowerBellDiameter);
        //EditText centerThicknessText = (EditText) findViewById(R.id.centerThicknessEditText);

        Toast toast = Toast.makeText(this, "ID: " + Long.toString(id), Toast.LENGTH_SHORT);
        toast.show();

        /*switch ((int)id) {
            case 0: //convex - plano
            case 2:
                lowerBellText.setEnabled(false);
                lowerRadiusText.setEnabled(false);
                lowerRadius = 999999;
                lowerBellDiameter = 0;
                break;
            case 1: //plano - convex
            case 4:
                upperRadiusText.setEnabled(false);
                upperBellText.setEnabled(false);
                upperRadius = 999999;
                upperBellDiameter = 0.0;
                break;
        }*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //TODO: add code here
    }

    public void onClickCenterButton(View v){
        Toast toast = Toast.makeText(this, "inside method", Toast.LENGTH_SHORT);
        toast.show();


        //Get view IDs
        EditText upperRadiusText = findViewById(R.id.upperRadiusEditText);
        upperRadius = Double.parseDouble(upperRadiusText.getText().toString());

        EditText upperBellDiameterText = findViewById(R.id.upperBellDiameter);
        upperBellDiameter = Double.parseDouble(upperBellDiameterText.getText().toString());

        EditText lowerRadiusText = findViewById(R.id.lowerRadiusEditText);
        lowerRadius = Double.parseDouble(lowerRadiusText.getText().toString());

        EditText lowerBellDiameterText = findViewById(R.id.lowerBellDiameter);
        lowerBellDiameter = Double.parseDouble(lowerBellDiameterText.getText().toString());

        EditText centerThicknessText = findViewById(R.id.centerThicknessEditText);
        centerThickness = Double.parseDouble(centerThicknessText.getText().toString());

        //Call centering calculation
        Center lens = new Center(upperBellDiameter, lowerBellDiameter, upperRadius, lowerRadius, centerThickness, shape);
        centeringValue = lens.calculateCentering();

        //Setup views to display results
        TextView centeringResultText = findViewById(R.id.centeringResultText);
        centeringResultText.setText(String.format("%.3f",centeringValue));
        centeringResultText.setVisibility(View.VISIBLE);

        if (centeringValue>0.0 && centeringValue <1.0) {
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) centeringResultText.getLayoutParams();
            params.horizontalBias = (float) (centeringValue * 0.5);
            centeringResultText.setLayoutParams(params);
        } else {
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) centeringResultText.getLayoutParams();
            params.horizontalBias = (float) (3.0 - centeringValue);
            centeringResultText.setLayoutParams(params);
        }


    }
}
