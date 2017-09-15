package com.limitless.haulified.Haulifier.model;

import java.io.Serializable;

/**
 * Created by Rashmi on 14-Jul-17.
 */

public class Offer1DestinationModel implements Serializable {

        private String address;

    private String city;
    private String country;
    private String  pin;
    private String  state;

    public String getAddress1() {
        return address;
    }

    public void setAddress1(String address1) {
        this.address = address1;
    }



    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }



}
