package com.limitless.haulified.Haulifier.common;

import retrofit.mime.TypedString;

/**
 * Created by Rashami on 23-Jun-17.
 */

public class Test extends TypedString {
    public Test(String body) {
        super(body);
    }

    @Override
    public String mimeType() {
        return "application/json";
    }

}
