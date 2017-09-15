package com.limitless.haulified.Haulifier.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Rashami on 28-Jun-17.
 */

public class TruckerListResponse implements Serializable {
    private int total;
    private int limit;
    private int skip;
    private int code;
    private ArrayList<com.limitless.haulified.Haulifier.model.TruckListModel> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getSelectedIds() {
        return selectedIds;
    }

    public void setSelectedIds(int selectedIds) {
        this.selectedIds = selectedIds;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public ArrayList<com.limitless.haulified.Haulifier.model.TruckListModel> getData() {
        return data;
    }

    public void setData(ArrayList<com.limitless.haulified.Haulifier.model.TruckListModel> data) {
        this.data = data;
    }



    public int selectedIds = 0;
}
