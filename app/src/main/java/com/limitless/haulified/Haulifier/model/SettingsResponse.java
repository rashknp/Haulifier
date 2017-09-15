package com.limitless.haulified.Haulifier.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Rashmi on 04-Sep-17.
 */

public class SettingsResponse implements Serializable {
    private Boolean success;
             private int code;
    private ArrayList<SettingModel> data;

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

    public ArrayList<SettingModel> getData() {
        return data;
    }

    public void setData(ArrayList<SettingModel> data) {
        this.data = data;
    }
}
