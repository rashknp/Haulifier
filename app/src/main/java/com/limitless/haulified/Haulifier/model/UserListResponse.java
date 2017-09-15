package com.limitless.haulified.Haulifier.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Rashmi on 03-Aug-17.
 */

public class UserListResponse implements Serializable {

   private Boolean success;
    private int code;
     private String msg;
    private int total;
    private int skip;
    private int limit;
    private ArrayList<UserListResponse1>data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
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

    public ArrayList<UserListResponse1> getData() {
        return data;
    }

    public void setData(ArrayList<UserListResponse1> data) {
        this.data = data;
    }
}

