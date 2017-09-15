package com.limitless.haulified.Haulifier.Activity;

import android.content.Intent;
import android.widget.LinearLayout;

import com.limitless.haulified.Haulifier.R;
import com.limitless.haulified.Haulifier.common.CustomDialog;
import com.limitless.haulified.Haulifier.common.Preference;

/**
 * Created by Rashami on 21-Jun-17.
 */

public class SplassActivity extends BaseActivity {
    public CustomDialog customDialog;
    public LinearLayout rlMainBody;
    String  latestVersion="";
    String token="";
    Preference prefe;
   /* @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
    }*/

    @Override
    public void initialize() {
        rlMainBody = (LinearLayout) inflater.inflate(R.layout.splash_screen, null);
        llBody.addView(rlMainBody);
        prefe =new Preference(SplassActivity.this);
        try {
           token=prefe.getStringFromPreference(Preference.token, "");
            //token = prefs.getString("token", null);
            if (token==null){
                token="";
            }
        }catch (Exception e){
            e.getMessage();
            token="";
        }
        if(token.equalsIgnoreCase("")||token.equals(null)){
            Intent i=new Intent(SplassActivity.this,SignIn.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            finish();
        }
        else {
            Intent i=new Intent(SplassActivity.this,TruckerList.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            finish();

        }

    }


}
