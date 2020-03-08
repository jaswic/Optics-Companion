package com.example.opticscompanion.Utils;

public class Lens {

    public static double calculateSag(double roc, double diameter){

        if(roc<0){
            roc = -1*roc;
            return -1*(roc - Math.sqrt(Math.pow(roc,2)-Math.pow((diameter/2),2)));
        } else {
            return roc - Math.sqrt(Math.pow(roc,2)-Math.pow((diameter/2),2));
        }
    }

    public static double calculateDiameter(double roc, double sag){

        double absROC;
        absROC = Math.abs(roc);
        return Math.sqrt((2*absROC*sag - Math.pow(sag,2))*4);
    }

    public static double calculateRadius (double sag, double diameter) {

        return (Math.pow(sag,2)+Math.pow((diameter/2),2))/(2*sag);
    }
}
