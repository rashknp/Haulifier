package com.limitless.haulified.Haulifier.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by APTA on 5/27/2017.
 */

public class CalanderUtils {

    public static String getCurreDateTime_Dash() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy hh.mm aa");
        String formattedDate = dateFormat.format(new Date()).toString();
        String strArr[] = formattedDate.split(" ");
        return strArr[0] + " | " + strArr[1] + " " + strArr[2];
    }
}
