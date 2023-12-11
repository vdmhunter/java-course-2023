package edu.project4.models;

public class Pixel {
    private int red;
    private int green;
    private int blue;
    private int hitCount;
    private double normal;

    public Pixel(int red, int green, int blue, int hitCount) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.hitCount = hitCount;
        this.normal = 0.0D;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getHitCount() {
        return hitCount;
    }

    public double getNormal() {
        return normal;
    }

    public void setNormal(double normal) {
        this.normal = normal;
    }

    public void incrementHitCount() {
        this.hitCount++;
    }
}
