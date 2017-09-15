package com.limitless.haulified.Haulifier.Activity;

import android.content.Intent;
import android.graphics.Paint;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.auth0.android.jwt.JWT;
import com.limitless.haulified.Haulifier.R;
import com.limitless.haulified.Haulifier.common.AppConstants;
import com.limitless.haulified.Haulifier.common.Preference;
import com.limitless.haulified.Haulifier.common.Test;
import com.limitless.haulified.Haulifier.interfaces.ApiInterface;
import com.limitless.haulified.Haulifier.model.SignInModel;
import com.limitless.haulified.Haulifier.utils.NetworkUtility;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

/**
 * Created by Rashami on 22-Jun-17.
 */

public class SignIn extends BaseActivity implements View.OnClickListener{
    private RelativeLayout rlProductDetailActivity;
    private EditText password,email;
    private TextView signup,forget;
    private Button signIn;
    private String strErrorMessage = "";
    private ImageView viewPass;
    private  Boolean clicked=false;
    private FrameLayout email_validate;
    ApiInterface api;
    @Override
    public void initialize() {
        rlProductDetailActivity = (RelativeLayout) inflater.inflate(R.layout.sign_in, null);
        llBody.addView(rlProductDetailActivity);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        initialiseView();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
       /* email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!isEmailValid(s.toString())&&!(s.toString().equalsIgnoreCase(""))){
                    email_validate.setVisibility(View.VISIBLE);
                     }
                else if(s.toString().equalsIgnoreCase("")){
                    email_validate.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }*/

    private void initialiseView() {
        email=(EditText) findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        signIn=(Button)findViewById(R.id.signin);
        forget=(TextView) findViewById(R.id.forget);
        viewPass=(ImageView)findViewById(R.id.viewPass);
        signIn.setOnClickListener(this);
        viewPass.setOnClickListener(this);
        forget.setOnClickListener(this);
        forget.setPaintFlags(forget.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.forget){
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            Intent i=new Intent(this,ForgetPassword.class);
            startActivity(i);
        }

        else if(v.getId()==R.id.signin){
            animFadein = AnimationUtils.loadAnimation(SignIn.this, R.anim.fade_in);
            signIn.startAnimation(animFadein);
            if (NetworkUtility.isNetworkConnectionAvailable(SignIn.this)) {
                if (validateFields()) {
                    showLoader("Please wait...");
                    String mail=(email.getText().toString()).trim();
                    signIN((email.getText().toString()).trim(), password.getText().toString());
                } else {
                    Snackbar snackbar = Snackbar.make(rlProductDetailActivity, strErrorMessage, Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            } else {
                Snackbar snackbar = Snackbar.make(rlProductDetailActivity, R.string.no_internet, Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
        else if(v.getId()==R.id.viewPass){
            if(clicked)
            {
                clicked=false;
                viewPass.setImageResource(R.drawable.eye_close);
               // viewPass.setBackgroundResource(R.drawable.visible);
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }else
            {
                clicked=true;
                viewPass.setImageResource(R.drawable.eye_open);
              //  viewPass.setBackgroundResource(R.drawable.invisible);
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        }
    }

    private void signIN(String email, String password) {
        showLoader("Please wait...");
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(AppConstants.BASE_URL)
                .build();

        //Creating an object of our api interface
        ApiInterface api = adapter.create(ApiInterface.class);
        JSONObject _obj = new JSONObject();
         //preparing request
        try {
            _obj.put("email", email);
            _obj.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Test request = new Test(_obj.toString());
        api.signIn(request, new Callback<SignInModel>() {
            @Override
            public void success(SignInModel signInModel, retrofit.client.Response response) {
                hideLoader();

                if (signInModel.getCode() == 200) {
                    if (signInModel != null) {
                        Toast.makeText(SignIn.this, "Successfully signIn", Toast.LENGTH_SHORT).show();
                        saveToken(signInModel);
                        Preference prefe=new Preference(SignIn.this);
                        final String token=prefe.getStringFromPreference(Preference.token, "");
                        JWT jwt = new JWT(token);
                        String account_name=jwt.getSignature();
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                        Intent i = new Intent(SignIn.this, TruckerList.class);
                        startActivity(i);
                        finish();
                    }
                }
                else{
                    Snackbar snackbar = Snackbar.make(rlProductDetailActivity,  signInModel.getMsg(), Snackbar.LENGTH_LONG);
                    snackbar.show();
                }}

            @Override
            public void failure(RetrofitError error) {
                hideLoader();
                Snackbar snackbar = Snackbar.make(rlProductDetailActivity,  "Server error", Snackbar.LENGTH_LONG);
                snackbar.show();

            }
        });

    }



    private boolean validateFields() {
        if (email.getText().toString().trim().equals("")) {
            strErrorMessage = "Please enter Email";
            return false;
        } else if (!isEmailValid(email.getText().toString().trim())) {
            strErrorMessage = "Please Enter Valid Email Address";
            return false;
        } else if (password.getText().toString().equals("")) {
            strErrorMessage = "Please enter password";
            return false;
        }
        return true;
    }
    private boolean isEmailValid(String email) {
        // String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
