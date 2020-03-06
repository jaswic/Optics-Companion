package com.example.opticscompanion;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class LensActivity_Nav extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lens_navigation);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Add drawer menu icon and drawer toggle to appbar
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.nav_open_drawer, R.string.nav_close_drawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Set the activity to be a listener for menu item selection
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
                fragment = new CenteringFragment();
                break;
            case R.id.nav_zygo:
                intent = new Intent(this, ZygoActivity.class);
                break;
            case R.id.nav_process:
                intent = new Intent(this, ProcessActivity.class);
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
}
