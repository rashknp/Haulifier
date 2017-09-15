package com.limitless.haulified.Haulifier.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by AshwaniKumar on 10/10/2016.
 */
public class PermissionControl {
    private Context mContext;

    final public static int REQUEST_CODE_ASK_PERMISSIONS = 123, ACCESS_COARSE_LOCATION = 11, WRITE_EXTERNAL_STORAGE = 12, READ_PHONE_STATE = 13, READ_CAMERA = 14, CALL_PHONE = 15, READ_EXTERNAL_STORAGE = 16;

    public PermissionControl(Context mContext) {
        this.mContext = mContext;
    }

    public void checkforPermissions(int type) {
        if (ACCESS_COARSE_LOCATION == type) {
            int permission1 = ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION);
            int permission4 = ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION);
            if (permission1 == PackageManager.PERMISSION_GRANTED) ;
            if (permission4 == PackageManager.PERMISSION_GRANTED) ;
            else
                requestAppPermission(permission1, 0, 0, permission4, 0, 0, 0);
        } else if (WRITE_EXTERNAL_STORAGE == type) {
            int permission2 = ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permission2 == PackageManager.PERMISSION_GRANTED) ;
            else
                requestAppPermission(0, permission2, 0, 0, 0, 0, 0);

        } else if (READ_PHONE_STATE == type) {
            int permission3 = ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_PHONE_STATE);
            if (permission3 == PackageManager.PERMISSION_GRANTED) ;
            else
                requestAppPermission(0, 0, permission3, 0, 0, 0, 0);
        } else if (READ_CAMERA == type) {
            int permission5 = ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA);
            if (permission5 == PackageManager.PERMISSION_GRANTED) ;
            else
                requestAppPermission(0, 0, 0, 0, permission5, 0, 0);
        } else if (CALL_PHONE == type) {
            int permission6 = ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA);
            if (permission6 == PackageManager.PERMISSION_GRANTED) ;
            else
                requestAppPermission(0, 0, 0, 0, 0, permission6, 0);
        } else if (READ_EXTERNAL_STORAGE == type) {
            int permission7 = ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE);
            if (permission7 == PackageManager.PERMISSION_GRANTED) ;
            else
                requestAppPermission(0, 0, 0, 0, 0, 0, permission7);

        }

       /* if(pre)

        if (permission1 == PackageManager.PERMISSION_GRANTED
                && permission2 == PackageManager.PERMISSION_GRANTED
                && permission3 == PackageManager.PERMISSION_GRANTED);
        else
            requestAppPermission(permission1, permission2, permission3);*/
    }

    public void requestAppPermission(int p1, int p2, int p3, int p4, int p5, int p6, int p7) {
        //     Toast.makeText(mContext, "Please allow all the permissions to continue.", Toast.LENGTH_SHORT).show();

        ArrayList<String> arrList = new ArrayList<>();

        if (p1 != PackageManager.PERMISSION_GRANTED)
            arrList.add(Manifest.permission.ACCESS_COARSE_LOCATION);

        if (p2 != PackageManager.PERMISSION_GRANTED)
            arrList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (p3 != PackageManager.PERMISSION_GRANTED)
            arrList.add(Manifest.permission.READ_PHONE_STATE);

        if (p4 != PackageManager.PERMISSION_GRANTED)
            arrList.add(Manifest.permission.ACCESS_FINE_LOCATION);

        if (p5 != PackageManager.PERMISSION_GRANTED)
            arrList.add(Manifest.permission.CAMERA);
        if (p6 != PackageManager.PERMISSION_GRANTED)
            arrList.add(Manifest.permission.CALL_PHONE);
        if (p7 != PackageManager.PERMISSION_GRANTED)
            arrList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        String[] permissons = new String[arrList.size()];

        for (int i = 0; i < arrList.size(); i++)
            permissons[i] = arrList.get(i);

        ((AppCompatActivity) mContext).requestPermissions(permissons, REQUEST_CODE_ASK_PERMISSIONS);
    }
}
