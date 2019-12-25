package com.example.opticscompanion;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class RadiusFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_radius, container, false);
        final EditText diameterText = view.findViewById(R.id.operandValue1);
        final EditText sagText= view.findViewById(R.id.operandValue2);
        Button calculateButton = view.findViewById(R.id.calculateButton);
        final TextView result = view.findViewById(R.id.resultText);
        final TextView resultLabel = view.findViewById(R.id.resultLabel);

        calculateButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                double diameter, radius, sag;

                diameter = Double.parseDouble(diameterText.getText().toString());
                sag = Double.parseDouble(sagText.getText().toString());

                radius = Lens.calculateRadius(sag, diameter);
                result.setText(String.format("%,.3f", radius));
                resultLabel.setVisibility(View.VISIBLE);

            }
        });


        // Inflate the layout for this fragment
        return view;
    }

}
