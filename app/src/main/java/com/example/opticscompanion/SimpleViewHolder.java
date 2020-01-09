package com.example.opticscompanion;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SimpleViewHolder extends RecyclerView.ViewHolder {
    private TextView largeGrayText, smallGrayText, coverage;

    public SimpleViewHolder(@NonNull View itemView) {
        super(itemView);
        largeGrayText = itemView.findViewById(R.id.largeGrayText);
        smallGrayText =itemView.findViewById(R.id.smallGrayText);
        coverage = itemView.findViewById(R.id.coloredBox);
    }

    public void bindData(SphereViewModel viewModel){
        String coverageAmount;

        if(viewModel.getCoverage().length() > 4){
            coverageAmount = viewModel.getCoverage().substring(0,4);
        } else {
            coverageAmount = viewModel.getCoverage();
        }
        //coverage.setText(coverageAmount);
        if (viewModel.getCoverage() == "0"){
            coverage.setBackgroundResource(R.color.card_red);
            coverage.setTextColor(Color.parseColor("#FFD500"));
        } else if ((Double.valueOf(viewModel.getCoverage()) < 1.0) && (Double.valueOf(viewModel.getCoverage()) > 0.0)){
            coverage.setBackgroundResource(R.color.card_yellow);
        } else {
            coverage.setBackgroundResource(R.color.card_green);
        }

        double coveragePercent = Double.valueOf(coverageAmount) * 100.0;

        smallGrayText.setText(Double.toString(coveragePercent) + "% coverage");
        largeGrayText.setText(viewModel.getSphereName());
    }
}
