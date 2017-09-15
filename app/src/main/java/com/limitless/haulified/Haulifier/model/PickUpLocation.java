package com.limitless.haulified.Haulifier.model;

import java.io.Serializable;

/**
 * Created by Rashmi on 21-Aug-17.
 */

public class PickUpLocation implements Serializable {
    private String type;
    private double coordinates[];

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
