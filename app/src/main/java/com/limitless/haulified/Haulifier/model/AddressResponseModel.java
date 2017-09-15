package com.limitless.haulified.Haulifier.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by apta on 7/24/2017.
 */

public class AddressResponseModel implements Serializable {
    private boolean success;
    private int code;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ArrayList<com.limitless.haulified.Haulifier.model.AddressData> getData() {
        return data;
    }

    public void setData(ArrayList<com.limitless.haulified.Haulifier.model.AddressData> data) {
        this.data = data;
    }

    private ArrayList<com.limitless.haulified.Haulifier.model.AddressData> data;
}
