package com.example.opticscompanion.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.opticscompanion.Utils.Lens;
import com.example.opticscompanion.R;

public class SagFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sag, container, false);

        final EditText diameterText = view.findViewById(R.id.operandValue2);
        final EditText radiusText= view.findViewById(R.id.operandValue1);
        final Button calculateButton = view.findViewById(R.id.calculateButton);
        final TextView result = view.findViewById(R.id.resultText);
        final TextView resultLabel = view.findViewById(R.id.resultLabel);

        calculateButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                double diameter, radius, sag;


                if (TextUtils.isEmpty(diameterText.getText()) || TextUtils.isEmpty(radiusText.getText())) {
                    Toast toast = Toast.makeText(getActivity(), "Must enter a value", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    diameter = Double.parseDouble(diameterText.getText().toString());
                    radius = Double.parseDouble(radiusText.getText().toString());

                    sag = Lens.calculateSag(radius, diameter);
                    result.setText(String.format("%,.3f", sag));
                    resultLabel.setVisibility(View.VISIBLE);

                }
            }
        });


        // Inflate the layout for this fragment
        return view;
    }

}