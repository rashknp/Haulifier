package com.limitless.haulified.Haulifier.model;

import java.io.Serializable;

/**
 * Created by Rashmi on 14-Jul-17.
 */

public class Offer1ItemsModel implements Serializable {
    private String itemName;
    private String itemType;
    private String quantity;
    private String _id;
    private Offer1SizeDimensionModel sizeDimension;
    private Offer1WtDimensionModel wtDimension;

    public Offer1SizeDimensionModel getSizeDimension() {
        return sizeDimension;
    }

    public void setSizeDimension(Offer1SizeDimensionModel sizeDimension) {
        this.sizeDimension = sizeDimension;
    }

    public Offer1WtDimensionModel getWtDimension() {
        return wtDimension;
    }

    public void setWtDimension(Offer1WtDimensionModel wtDimension) {
        this.wtDimension = wtDimension;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }


}

