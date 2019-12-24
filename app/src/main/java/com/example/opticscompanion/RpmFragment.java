package com.example.opticscompanion;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class RpmFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rpm, container, false);

          final EditText diameterText = view.findViewById(R.id.operandValue1);
          final EditText speedText = view.findViewById(R.id.operandValue2);
          final Button calculateButton = view.findViewById(R.id.calculateButton);
          final TextView result = view.findViewById(R.id.resultText);

        calculateButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                double diameter, speed, rpm;

                diameter = Double.parseDouble(diameterText.getText().toString());
                speed = Double.parseDouble(speedText.getText().toString());

                rpm = ProcessFunctions.calculateRPM(diameter, speed);
                result.setText(String.format("%,.3f", rpm));

            }
        });
        // Inflate the layout for this fragment
        return view;
    }

}
