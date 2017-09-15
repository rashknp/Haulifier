package com.limitless.haulified.Haulifier.model;

import java.io.Serializable;

/**
 * Created by Rashmi on 18-Jul-17.
 */

public class DeliveryItemsModel implements Serializable {

    private String itemName;
    private String  itemType;
    private String  _id;
    private  CapacityDeliveryModel sizeDimension;

    public CapacityDeliveryModel getSizeDimension() {
        return sizeDimension;
    }

    public void setSizeDimension(CapacityDeliveryModel sizeDimension) {
        this.sizeDimension = sizeDimension;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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
}
