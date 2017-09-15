package com.limitless.haulified.Haulifier.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Rashmi on 14-Jul-17.
 */

public class OfferListResponse implements Serializable {

    private Boolean success;
    private int  code;
    private String msg;
    private int  count;

    private ArrayList<com.limitless.haulified.Haulifier.model.OfferQuoteModel1> data;

    public int getTotal() {
        return count;
    }

    public void setTotal(int total) {
        this.count = total;
    }


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

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

    public ArrayList<com.limitless.haulified.Haulifier.model.OfferQuoteModel1> getData() {
        return data;
    }

    public void setData(ArrayList<com.limitless.haulified.Haulifier.model.OfferQuoteModel1> data) {
        this.data = data;
    }


}
