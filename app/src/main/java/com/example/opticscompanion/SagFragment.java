package com.example.opticscompanion;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SagFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sag, container, false);
        final EditText diameterText = view.findViewById(R.id.diameter);
        final EditText radiusText= view.findViewById(R.id.radius);
        Button calculateButton = view.findViewById(R.id.calculateButton);
        final TextView result = view.findViewById(R.id.resultText);

        calculateButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                double diameter, radius, sag;

                diameter = Double.parseDouble(diameterText.getText().toString());
                radius = Double.parseDouble(radiusText.getText().toString());

                sag = Lens.calculateSag(radius, diameter);
                result.setText(String.format("%,.3f", sag));

            }
        });


        // Inflate the layout for this fragment
        return view;
    }

}
