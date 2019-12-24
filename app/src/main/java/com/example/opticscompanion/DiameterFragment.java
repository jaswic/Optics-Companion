package com.example.opticscompanion;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class DiameterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diameter, container, false);

        final EditText sagText = view.findViewById(R.id.operandValue1);
        final EditText radiusText= view.findViewById(R.id.operandValue2);
        final Button calculateButton = view.findViewById(R.id.calculateButton);
        final TextView result = view.findViewById(R.id.resultText);

        calculateButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                double diameter, radius, sag;

                sag = Double.parseDouble(sagText.getText().toString());
                radius = Double.parseDouble(radiusText.getText().toString());

                diameter = Lens.calculateDiameter(radius, sag);
                result.setText(String.format("%,.3f", diameter));

            }
        });


        // Inflate the layout for this fragment
        return view;
    }

}