package com.limitless.haulified.Haulifier.model;

import java.io.Serializable;

/**
 * Created by Rashmi on 14-Jul-17.
 */

public class Offer1DeliveryModel implements Serializable {

    private String expDestinationDate;
    private String expPickupDate;
    private Offer1DestinationModel destinationAddress;
    private com.limitless.haulified.Haulifier.model.Offer1PickUpModel pickupAddress;

    public Offer1DestinationModel getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(Offer1DestinationModel destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public com.limitless.haulified.Haulifier.model.Offer1PickUpModel getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(com.limitless.haulified.Haulifier.model.Offer1PickUpModel pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getExpDestinationDate() {
        return expDestinationDate;
    }

    public void setExpDestinationDate(String expDestinationDate) {
        this.expDestinationDate = expDestinationDate;
    }

    public String getExpPickupDate() {
        return expPickupDate;
    }

    public void setExpPickupDate(String expPickupDate) {
        this.expPickupDate = expPickupDate;
    }





}
