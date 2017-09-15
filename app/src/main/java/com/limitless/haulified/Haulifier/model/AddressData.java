package com.limitless.haulified.Haulifier.model;

import java.io.Serializable;

/**
 * Created by apta on 7/24/2017.
 */

public class AddressData implements Serializable {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    private String placeId;
}
