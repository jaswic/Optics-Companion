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
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.opticscompanion.Fragments.EntryFragment;
import com.example.opticscompanion.R;
import com.example.opticscompanion.Utils.Center;
import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

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
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.nav_open_drawer, R.string.nav_close_drawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Set the activity to be a listener for menu item selection
        NavigationView navigationView = findViewById(R.id.nav_view);
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

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
        TextView upperRadiusLabel = findViewById(R.id.labelUpperRadius);
        TextView upperBellLabel = findViewById(R.id.labelUpperBell);
        TextView lowerRadiusLabel = findViewById(R.id.labelLowerRadius);
        TextView lowerBellLabel = findViewById(R.id.labelLowerBell);
        EditText upperRadiusText = findViewById(R.id.upperRadiusEditText);
        EditText lowerRadiusText = findViewById(R.id.lowerRadiusEditText);
        EditText upperBellText = findViewById(R.id.upperBellDiameter);
        EditText lowerBellText = findViewById(R.id.lowerBellDiameter);
        //EditText centerThicknessText = (EditText) findViewById(R.id.centerThicknessEditText);

        switch ((int)id) {
            case 0: //convex - plano
            case 2:
                upperRadiusText.setEnabled(true);
                upperRadiusText.setVisibility(View.VISIBLE);
                upperRadiusLabel.setVisibility(View.VISIBLE);
                upperBellText.setEnabled(true);
                upperBellText.setVisibility(View.VISIBLE);
                upperBellLabel.setVisibility(View.VISIBLE);
                lowerBellText.setEnabled(false);
                lowerBellText.setVisibility(View.GONE);
                lowerBellLabel.setVisibility(View.GONE);
                lowerRadiusText.setEnabled(false);
                lowerRadiusText.setVisibility(View.GONE);
                lowerRadiusLabel.setVisibility(View.GONE);
                lowerRadius = 999999;
                lowerBellDiameter = 1.0;
                Log.v("Setting lens shape","Enabled upper. Disabled Lower");
                Log.v("Setting lens shape","Lower radius: " + lowerRadius);

                break;
            case 1: //plano - convex
            case 3:
                upperRadiusText.setEnabled(false);
                upperRadiusText.setVisibility(View.GONE);
                upperRadiusLabel.setVisibility(View.GONE);
                upperBellText.setEnabled(false);
                upperBellText.setVisibility(View.GONE);
                upperBellLabel.setVisibility(View.GONE);
                lowerBellText.setEnabled(true);
                lowerBellText.setVisibility(View.VISIBLE);
                lowerBellLabel.setVisibility(View.VISIBLE);
                lowerRadiusText.setEnabled(true);
                lowerRadiusText.setVisibility(View.VISIBLE);
                lowerRadiusLabel.setVisibility(View.VISIBLE);
                upperRadius = 999999;
                upperBellDiameter = 1.0;
                Log.v("Setting lens shape","Enabled lower. Disabled upper");
                break;
            default:
                upperRadiusText.setEnabled(true);
                upperRadiusText.setVisibility(View.VISIBLE);
                upperRadiusLabel.setVisibility(View.VISIBLE);
                upperBellText.setEnabled(true);
                upperBellText.setVisibility(View.VISIBLE);
                upperBellLabel.setVisibility(View.VISIBLE);
                lowerBellText.setEnabled(true);
                lowerBellText.setVisibility(View.VISIBLE);
                lowerBellLabel.setVisibility(View.VISIBLE);
                lowerRadiusText.setEnabled(true);
                lowerRadiusText.setVisibility(View.VISIBLE);
                lowerRadiusLabel.setVisibility(View.VISIBLE);
                Log.v("Setting lens shape","Enabled all");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //TODO: add code here
    }

    public void onClickCenterButton(View v){
        //Get view IDs and values
        EditText upperRadiusText = findViewById(R.id.upperRadiusEditText);
        if (!TextUtils.isEmpty(upperRadiusText.getText())){
            upperRadius = Double.parseDouble(upperRadiusText.getText().toString());
        }

        EditText upperBellDiameterText = findViewById(R.id.upperBellDiameter);
        if (!TextUtils.isEmpty(upperBellDiameterText.getText())){
            upperBellDiameter = Double.parseDouble(upperBellDiameterText.getText().toString());
        }

        EditText lowerRadiusText = findViewById(R.id.lowerRadiusEditText);
        if (!TextUtils.isEmpty(lowerRadiusText.getText())) {
            lowerRadius = Double.parseDouble(lowerRadiusText.getText().toString());
        }

        EditText lowerBellDiameterText = findViewById(R.id.lowerBellDiameter);
        if (!TextUtils.isEmpty(lowerBellDiameterText.getText())) {
            lowerBellDiameter = Double.parseDouble(lowerBellDiameterText.getText().toString());
        }

        EditText centerThicknessText = findViewById(R.id.centerThicknessEditText);
        centerThickness = Double.parseDouble(centerThicknessText.getText().toString());

        //Call centering calculation
        Center lens = new Center(upperBellDiameter, lowerBellDiameter, upperRadius, lowerRadius, centerThickness, shape);
        centeringValue = lens.calculateCentering();

        //Setup views to display results
        View centeringArrow = findViewById(R.id.centeringArrow);
        TextView centeringResultText = findViewById(R.id.centeringResultText);
        centeringResultText.setText(String.format("%.3f",centeringValue));
        centeringResultText.setVisibility(View.VISIBLE);
        centeringArrow.setVisibility(View.VISIBLE);
        ConstraintLayout.LayoutParams textParams = (ConstraintLayout.LayoutParams) centeringResultText.getLayoutParams();
        ConstraintLayout.LayoutParams arrowParams = (ConstraintLayout.LayoutParams) centeringArrow.getLayoutParams();
        if (centeringValue>0.5 && centeringValue <1.5) {
            textParams.horizontalBias = (float) (centeringValue-0.5);
            arrowParams.horizontalBias = (float) (centeringValue - 0.5);
        } else if (centeringValue > 1.5){
            textParams.horizontalBias = (float) 0.9;
            arrowParams.horizontalBias = (float) 0.9;
        } else{
            textParams.horizontalBias = (float) 0.1;
            arrowParams.horizontalBias = (float) 0.1;
        }
        centeringResultText.setLayoutParams(textParams);
        centeringArrow.setLayoutParams(arrowParams);
    }
}
