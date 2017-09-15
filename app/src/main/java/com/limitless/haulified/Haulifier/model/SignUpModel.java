package com.limitless.haulified.Haulifier.model;



import java.io.Serializable;

/**
 * Created by Rashmi on 17-Jul-17.
 */

public class SignUpModel implements Serializable {
    private UserInfoModel userInfo;
    private CompanyInfoModel accountInfo;

    public UserInfoModel getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoModel userInfo) {
        this.userInfo = userInfo;
    }

    public CompanyInfoModel getCompanyInfo() {
        return accountInfo;
    }

    public void setCompanyInfo(CompanyInfoModel companyInfo) {
        this.accountInfo = companyInfo;
    }
}


