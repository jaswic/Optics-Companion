package com.example.opticscompanion;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ZygoActivity extends AppCompatActivity {
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
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


}
