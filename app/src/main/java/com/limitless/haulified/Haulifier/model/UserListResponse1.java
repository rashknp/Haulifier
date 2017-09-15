package com.limitless.haulified.Haulifier.model;

import java.io.Serializable;

/**
 * Created by Rashmi on 03-Aug-17.
 */

public class UserListResponse1 implements Serializable {

    private int  userId;
    private String  fullName;
    private String  email;
    private String role;
    private String  createdBy;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}


