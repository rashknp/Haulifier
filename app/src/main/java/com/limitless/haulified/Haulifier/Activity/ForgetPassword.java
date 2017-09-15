package com.limitless.haulified.Haulifier.Activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

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
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

/**
 * Created by Rashmi on 17-Aug-17.
 */

public class ForgetPassword extends BaseActivity {
    private LinearLayout rlProductDetailActivity;
    Preference prefe;
    private EditText email;
    private Button submit;
    private String strErrorMessage = "";
    @Override
    public void initialize() {
        rlProductDetailActivity = (LinearLayout) inflater.inflate(R.layout.forget_password, null);
        llBody.addView(rlProductDetailActivity);
        prefe =new Preference(ForgetPassword.this);
        initialiseView();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    animFadein = AnimationUtils.loadAnimation(ForgetPassword.this, R.anim.fade_in);
                submit.startAnimation(animFadein);
                    if (NetworkUtility.isNetworkConnectionAvailable(ForgetPassword.this)) {
                        if (validateFields()) {
                            showLoader("Please wait...");
                            emailVarification(email.getText().toString());
                        } else {
                            Snackbar snackbar = Snackbar.make(rlProductDetailActivity, strErrorMessage, Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
                    } else {
                        Snackbar snackbar = Snackbar.make(rlProductDetailActivity, R.string.no_internet, Snackbar.LENGTH_LONG);
                        snackbar.show();

                }
            }
        });


    }
    @Override
    protected void onResume() {
        super.onResume();
        setMenuTitle("Forgot Password");
        hideLoader();
    }
    private void emailVarification(final String email1) {
        showLoader("Please wait...");
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("authorization", "JWT "+prefe.getStringFromPreference(Preference.token, ""));
            }
        };
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(AppConstants.BASE_URL)
                .setRequestInterceptor(requestInterceptor)
                .build();

        //Creating an object of our api interface
        ApiInterface api = adapter.create(ApiInterface.class);
        JSONObject _obj = new JSONObject();
        //preparing request
        try {
            _obj.put("email", email1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Test request = new Test(_obj.toString());
        api.signForgetEmail(request, new Callback<SignInModel>() {
            @Override
            public void success(SignInModel signInModel, retrofit.client.Response response) {
                if(response.getStatus()==200) {
                    hideLoader();
                    if(signInModel.getCode()==419){
                        Snackbar snackbar = Snackbar.make(rlProductDetailActivity, AppConstants.tokenExpMsg, Snackbar.LENGTH_LONG);
                        snackbar.show();
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                        Intent i=new Intent(ForgetPassword.this, SignIn.class);
                        startActivity(i);
                        finish();
                        AppConstants.token="";
                    }else {
                        if(signInModel.getCode()==200) {
                            Snackbar snackbar = Snackbar.make(rlProductDetailActivity, signInModel.getMsg(), Snackbar.LENGTH_LONG);
                            snackbar.show();
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                            Intent i = new Intent(ForgetPassword.this, ForgetPasswordFinal.class);
                            i.putExtra("email",email1);
                            startActivity(i);
                            finish();
                            email.setText("");
                        }
                        else {
                            Snackbar snackbar = Snackbar.make(rlProductDetailActivity, signInModel.getMsg(), Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }


                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                hideLoader();
                Snackbar snackbar = Snackbar.make(rlProductDetailActivity,  "Server error", Snackbar.LENGTH_LONG);
                snackbar.show();

            }
        });

    }

    private void initialiseView() {
        email=(EditText)findViewById(R.id.email);
        submit=(Button) findViewById(R.id.submit);


    }
    private boolean validateFields() {
        if (email.getText().toString().equals("")) {
            strErrorMessage = "Please Enter Email";
            return false;
        } else if (!isEmailValid(email.getText().toString())) {
            strErrorMessage = "Please Enter Valid Email Address";
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
