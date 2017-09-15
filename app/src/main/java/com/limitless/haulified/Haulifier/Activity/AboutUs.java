package com.limitless.haulified.Haulifier.Activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.webkit.WebView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.limitless.haulified.Haulifier.R;
import com.limitless.haulified.Haulifier.utils.NetworkUtility;


public class AboutUs extends BaseActivity {
    private ScrollView llUserProfile;
    private TextView one, two, three, four;
    private WebView webview;
    private String version;

    @Override
    public void initialize() {
        llUserProfile = (ScrollView) inflater.inflate(R.layout.activity_aboutus, null);
        llBody.addView(llUserProfile);
        initilizeView();
        getVersion();
        one.setText("Consignment & Demo Policy");
        two.setText("Version:"+version);
        three.setText("https:www.starkey.com");
        four.setText("@Copyright 2017 Starkey.com All rights reserved");
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://139.59.1.185:8080/aboutus"));
                startActivity(browserIntent);
            }
        });

        webview.getSettings().setJavaScriptEnabled(true);
        if (NetworkUtility.isNetworkConnectionAvailable(AboutUs.this)) {

            webview.loadUrl("http://139.59.1.185:8080/aboutus");
        }else
        {
            Toast.makeText(AboutUs.this, "No Internet !", Toast.LENGTH_SHORT).show();
        }


    }
   private void getVersion()
    {
        try {
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_META_DATA);
            version =pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void initilizeView() {
        one = (TextView) llUserProfile.findViewById(R.id.one);
        two = (TextView) llUserProfile.findViewById(R.id.two);
        three = (TextView) llUserProfile.findViewById(R.id.three);
        four = (TextView) llUserProfile.findViewById(R.id.four);
        webview = (WebView) llUserProfile.findViewById(R.id.web);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setMenuTitle("About Us");
        hideLoader();
    }
}
