package com.example.opticscompanion;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.opticscompanion.R;


public class CuttingSpeedFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cutting_speed, container, false);
        final EditText diameterText = view.findViewById(R.id.operandValue1);
        final EditText rpmText = view.findViewById(R.id.operandValue2);
        final Button calculateButton = view.findViewById(R.id.calculateButton);
        final TextView resultText = view.findViewById(R.id.resultValue);
        final TextView resultLabel = view.findViewById(R.id.resultLabel);

        calculateButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                double diameter, speed, rpm;

                diameter = Double.parseDouble(diameterText.getText().toString());
                rpm = Double.parseDouble(rpmText.getText().toString());

                speed = ProcessFunctions.calculateCuttingSpeed(diameter, rpm);
                resultText.setText(String.format("%.2f", speed));
                resultLabel.setVisibility(View.VISIBLE);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
