package com.limitless.haulified.Haulifier.model;

import java.io.Serializable;

/**
 * Created by Rashami on 23-Jun-17.
 */

public class SignInModel implements Serializable {
    private String msg;
    private Boolean success;
    private int code;
    private  String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
}
