package com.limitless.haulified.Haulifier.model;

import java.io.Serializable;

/**
 * Created by Rashmi on 14-Jul-17.
 */

public class Offer1SizeDimensionModel implements Serializable {

    private double breadth;
    private double height;
    private double length;
    private String unit;

    public double getBreadth() {
        return breadth;
    }

    public void setBreadth(int breadth) {
        this.breadth = breadth;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
