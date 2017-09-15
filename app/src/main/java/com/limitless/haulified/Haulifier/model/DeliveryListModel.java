package com.limitless.haulified.Haulifier.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Rashmi on 18-Jul-17.
 */

public class DeliveryListModel implements Serializable {
     private String _id;
     private String orderId;
     private String bookingId;
     private ArrayList<DeliveryItemsModel> items;
     private double quotePrice;
     private String pickupDate;
     private String deliveryDate;
     private String text;
     private int  __v;
     private String updatedAt;
     private String createdAt;
     private PickupModel pickupLoc;
     private DeliveryModel deliveryLoc;
     private String itemCount;

     public String getBookingId() {
          return bookingId;
     }

     public void setBookingId(String bookingId) {
          this.bookingId = bookingId;
     }

     public PickupModel getPickupLoc() {
          return pickupLoc;
     }

     public void setPickupLoc(PickupModel pickupLoc) {
          this.pickupLoc = pickupLoc;
     }

     public DeliveryModel getDeliveryLoc() {
          return deliveryLoc;
     }

     public void setDeliveryLoc(DeliveryModel deliveryLoc) {
          this.deliveryLoc = deliveryLoc;
     }

     public double getQuotePrice() {
          return quotePrice;
     }

     public void setQuotePrice(double quotePrice) {
          this.quotePrice = quotePrice;
     }

     private String status;

     public String getItemCount() {
          return itemCount;
     }

     public void setItemCount(String itemCount) {
          this.itemCount = itemCount;
     }

     public String getStatus() {
          return status;
     }

     public void setStatus(String status) {
          this.status = status;
     }

     public ArrayList<DeliveryItemsModel> getItems() {
          return items;
     }

     public void setItems(ArrayList<DeliveryItemsModel> items) {
          this.items = items;
     }


     public String get_id() {
          return _id;
     }

     public void set_id(String _id) {
          this._id = _id;
     }

     public String getOrderId() {
          return orderId;
     }

     public void setOrderId(String orderId) {
          this.orderId = orderId;
     }



     public String getPickupDate() {
          return pickupDate;
     }

     public void setPickupDate(String pickupDate) {
          this.pickupDate = pickupDate;
     }

     public String getDeliveryDate() {
          return deliveryDate;
     }

     public void setDeliveryDate(String deliveryDate) {
          this.deliveryDate = deliveryDate;
     }

     public String getText() {
          return text;
     }

     public void setText(String text) {
          this.text = text;
     }

     public int get__v() {
          return __v;
     }

     public void set__v(int __v) {
          this.__v = __v;
     }

     public String getUpdatedAt() {
          return updatedAt;
     }

     public void setUpdatedAt(String updatedAt) {
          this.updatedAt = updatedAt;
     }

     public String getCreatedAt() {
          return createdAt;
     }

     public void setCreatedAt(String createdAt) {
          this.createdAt = createdAt;
     }


}
