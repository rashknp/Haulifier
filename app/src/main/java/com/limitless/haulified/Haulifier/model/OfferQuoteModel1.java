package com.limitless.haulified.Haulifier.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Rashami on 27-Jun-17.
 */

public class OfferQuoteModel1 implements Serializable {

    private int  bookingId;
    private Double quotePrice;
    private int  quoteId;
    private String status;
    private int  commission;

    private String expPickupDate;
    private String  expDestinationDate;
    private String   pickupAddress;
    private String   destinationAddress;

    public void setQuotePrice(Double quotePrice) {
        this.quotePrice = quotePrice;
    }

    public int getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(int quoteId) {
        this.quoteId = quoteId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCommission() {
        return commission;
    }

    public void setCommission(int commission) {
        this.commission = commission;
    }

    public String getExpPickupDate() {
        return expPickupDate;
    }

    public void setExpPickupDate(String expPickupDate) {
        this.expPickupDate = expPickupDate;
    }

    public String getExpDestinationDate() {
        return expDestinationDate;
    }

    public void setExpDestinationDate(String expDestinationDate) {
        this.expDestinationDate = expDestinationDate;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public double getQuotePrice() {
        return quotePrice;
    }

    public void setQuotePrice(double quotePrice) {
        this.quotePrice = quotePrice;
    }



}
