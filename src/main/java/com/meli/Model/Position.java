package com.meli.Model;

public class Position {
    double xPosition;
    double yPosition;

    public double getyPosition() {
        return yPosition;
    }

    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }


    public double getxPosition() {
        return xPosition;
    }

    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    public Position (double x, double y){
        xPosition = x;
        yPosition = y;


    }
}
