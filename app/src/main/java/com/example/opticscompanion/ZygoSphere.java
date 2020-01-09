package com.example.opticscompanion;

import java.util.ArrayList;
import java.util.List;

public class ZygoSphere {
    private double fNumber;
    private double sphereRadius;
    private String name;

    public ZygoSphere(double fNumber, double sphereRadius, String name){
        this.fNumber = fNumber;
        this.sphereRadius = sphereRadius;
        this.name = name;
    }

    public double getfNumber(){
        return fNumber;
    }

    public double getSphereRadius() {
        return sphereRadius;
    }

    public String getName() {
        return name;
    }

    public List<String> evaluateLens(double lensRadius, double lensDiameter, boolean convex){
        List<String> attributes = new ArrayList<>();
        String coverage;
        double rNumber = lensRadius / lensDiameter;

        if (convex){
            if (lensRadius > this.sphereRadius){
                coverage = "0";
            } else {
                coverage = Double.toString(rNumber / this.fNumber);
            }
        } else {
            coverage = Double.toString(rNumber / this.fNumber);
        }

        attributes.add(this.getName());
        attributes.add(Double.toString(this.getfNumber()));
        attributes.add(coverage);

        return attributes;
    }
}
