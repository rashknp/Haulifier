package com.limitless.haulified.Haulifier.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Rashmi on 18-Jul-17.
 */

public class MyDeliveryResponse implements Serializable {
    private int code;
    private String msg;

    private ArrayList<com.limitless.haulified.Haulifier.model.DeliveryListModel> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<com.limitless.haulified.Haulifier.model.DeliveryListModel> getData() {
        return data;
    }

    public void setData(ArrayList<com.limitless.haulified.Haulifier.model.DeliveryListModel> data) {
        this.data = data;
    }
}
