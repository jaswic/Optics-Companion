package com.example.opticscompanion;



public class Center {
    private static final double MU=0.13;

    private double diameterUpperBell;
    private double diameterLowerBell;
    private double radiusTop;
    private double radiusBottom;
    private int shape;
    private double centerThickness;


    public Center(double diameterUpperBell, double diameterLowerBell, double radiusTop, double radiusBottom, double centerThickness, int shape) {
        this.diameterUpperBell = diameterUpperBell;
        this.diameterLowerBell = diameterLowerBell;
        this.radiusTop = radiusTop;
        this.radiusBottom = radiusBottom;
        this.centerThickness = centerThickness;
        this.shape = shape;

    }

    public void setDiameterUpperBell(double diameterUpperBell){
       this.diameterUpperBell = diameterUpperBell;
    }

    public void setDiameterLowerBell(double diameterLowerBell){
       this.diameterLowerBell = diameterLowerBell;
    }

    public void setRadiusTop(double radiusTop) {
        this.radiusTop = radiusTop;
    }

    public void setRadiusBottom(double radiusBottom) {
        this.radiusBottom = radiusBottom;
    }

    public void setShape(int shape) {
        this.shape = shape;
    }

    public double getDiameterUpperBell() {
        return diameterUpperBell;
    }

    public double getDiameterLowerBell() {
        return diameterLowerBell;
    }

    public double getRadiusTop() {
        return radiusTop;
    }

    public double getRadiusBottom() {
        return radiusBottom;
    }

    public int getShape() {
        return shape;
    }

    public double calculateCentering(){
        double centering = 0.0;
        double upperAngleRadians;
        double lowerAngleRadians;

        //calculate acceptance angle of bell
        upperAngleRadians = Math.asin((diameterUpperBell / 2) / radiusTop); //in radians
        lowerAngleRadians = Math.asin((diameterLowerBell / 2) / radiusBottom);

        switch (shape) {
            case 0: //convex - plano
            case 2: //concave - plano
                centering = (Math.tan(upperAngleRadians) / (2 * MU));
                break;
            case 1: //plano - convex
                centering = (diameterUpperBell / (2 * MU * (radiusBottom / Math.cos(lowerAngleRadians) + radiusBottom - centerThickness)));
                break;
            case 3: //plano - concave
                centering = (diameterUpperBell / (2 * MU * (radiusBottom / Math.cos(lowerAngleRadians) + radiusBottom + centerThickness)));
                break;
            case 4: //biconvex:
                centering = (Math.tan(upperAngleRadians) * (radiusTop + radiusBottom - centerThickness)) / (MU * ((radiusBottom / Math.cos(lowerAngleRadians)) + radiusTop + radiusBottom - centerThickness - (radiusTop / Math.cos(upperAngleRadians))));
                break;
            case 5: //biconcave
                centering = (Math.tan(upperAngleRadians) * (radiusTop + radiusBottom + centerThickness)) / (MU * ((radiusBottom / Math.cos(lowerAngleRadians)) + radiusTop + radiusBottom + centerThickness - (radiusTop / Math.cos(upperAngleRadians))));
                break;
            case 6: //meniscus with CC up
                centering = (Math.tan(upperAngleRadians) * (radiusTop + centerThickness - radiusBottom)) / (MU * ((radiusBottom / Math.cos(lowerAngleRadians)) + (radiusTop / Math.cos(upperAngleRadians)) + radiusBottom - radiusTop - centerThickness));
                break;
            case 7: //meniscus with CX up
                centering = (Math.tan(upperAngleRadians) * (radiusBottom + centerThickness - radiusTop)) / (MU * ((radiusBottom / Math.cos(lowerAngleRadians)) + (radiusTop / Math.cos(upperAngleRadians)) + radiusBottom - radiusTop + centerThickness));
                break;
            default:
                return centering = 0.0;
        }

        return centering;
    }
}
