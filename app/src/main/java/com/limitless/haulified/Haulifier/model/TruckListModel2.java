package com.limitless.haulified.Haulifier.model;

import java.io.Serializable;

/**
 * Created by Rashami on 28-Jun-17.
 */

public class TruckListModel2 implements Serializable{
    private Double weight;
    private String unit;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
