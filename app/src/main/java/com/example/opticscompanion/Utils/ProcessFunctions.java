package com.example.opticscompanion.Utils;

public class ProcessFunctions {

    public static double calculateRPM(double diameter, double cuttingSpeed){
        return (60000.0 * cuttingSpeed) / (Math.PI * diameter);
    }

    public static double calculateCuttingSpeed(double diameter, double rpm){
        return (Math.PI * diameter * rpm) / 60000;
    }
}
