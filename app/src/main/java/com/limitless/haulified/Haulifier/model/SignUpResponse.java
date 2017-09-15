package com.limitless.haulified.Haulifier.model;

import java.io.Serializable;

/**
 * Created by Rashmi on 29-Jul-17.
 */

public class SignUpResponse implements Serializable{
   private String success;
    private int code;
    private String msg;
    private int accountId;
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
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
}
