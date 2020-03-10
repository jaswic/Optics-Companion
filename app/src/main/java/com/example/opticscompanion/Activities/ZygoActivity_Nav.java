package com.example.opticscompanion.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.opticscompanion.Fragments.EntryFragment;
import com.example.opticscompanion.R;
import com.example.opticscompanion.Adapters.RecyclerAdapter;
import com.example.opticscompanion.Adapters.SphereViewModel;
import com.example.opticscompanion.Utils.ZygoSphere;
import com.google.android.material.navigation.NavigationView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ZygoActivity_Nav extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_navigation);

        //Set up navigation drawer and action bar
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

        //Set up button to call recycler view
        Button button = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get recycler view handle and set up adapter
                RecyclerView rv = findViewById(R.id.rv);

                //Get lens parameters
                //Set up the lens information fields
                TextView lensRadiusText = findViewById(R.id.lensRadius);
                TextView lensDiameterText = findViewById(R.id.lensDiameter);
                RadioGroup lensShapeGroup = findViewById(R.id.lensShapeGroup);
                RadioButton convexButton = findViewById(R.id.shapeConvex);


                double lensDiameter, lensRadius;
                boolean convex;
                int selectedShape;
                lensDiameter = Double.valueOf(lensDiameterText.getText().toString());
                lensRadius = Double.valueOf(lensRadiusText.getText().toString());
                selectedShape = lensShapeGroup.getCheckedRadioButtonId();
                if (selectedShape == convexButton.getId()){
                    convex = true;
                } else
                    convex = false;

                RecyclerAdapter adapter = new RecyclerAdapter(getSpheres(lensRadius, lensDiameter, convex));
                LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
                rv.setLayoutManager(llm);
                rv.setAdapter(adapter);
            }
        });

    }

    private List<SphereViewModel> getSpheres(double lensRadius, double lensDiameter, boolean isConvex){
        ArrayList<SphereViewModel> mList = new ArrayList<SphereViewModel>();
        Context context = getApplicationContext();
        int i = 1;
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("zygoAttributes.txt")));
            String inLine;
            String[] sphereAttributes;

            while((inLine = br.readLine())!= null) {
                List<String> results = new ArrayList<>();
                //Read name, f/#, and radius from file
                sphereAttributes = inLine.split(", ");

                ZygoSphere sphere = new ZygoSphere(Double.valueOf(sphereAttributes[1]),
                        Double.valueOf(sphereAttributes[2]), sphereAttributes[0]);

                results = sphere.evaluateLens(lensRadius, lensDiameter, isConvex);

                //Write values to view model
                SphereViewModel sphereModel = new SphereViewModel(results.get(0),
                        results.get(1), results.get(2));
                mList.add(sphereModel);

            }

        } catch (IOException e){
            e.printStackTrace();
        }
        return mList;
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


}
