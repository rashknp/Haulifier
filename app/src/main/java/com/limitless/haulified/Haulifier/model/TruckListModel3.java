package com.limitless.haulified.Haulifier.model;

import java.io.Serializable;

/**
 * Created by Rashami on 28-Jun-17.
 */

public class TruckListModel3 implements Serializable {
    private Double length;
    private Double breadth;
    private Double height;

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getBreadth() {
        return breadth;
    }

    public void setBreadth(double breadth) {
        this.breadth = breadth;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


    private String unit;



}
