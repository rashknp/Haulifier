package com.limitless.haulified.Haulifier.Activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class ForgetPasswordFinal extends BaseActivity {
    private LinearLayout rlProductDetailActivity;
    Preference prefe;
    private EditText email,code,newPass;
    private Button submit;
    private String strErrorMessage = "";
    private ImageView viewPass;
    private  Boolean clicked=false;
    @Override
    public void initialize() {
        rlProductDetailActivity = (LinearLayout) inflater.inflate(R.layout.forget_password_final, null);
        llBody.addView(rlProductDetailActivity);
       prefe =new Preference(ForgetPasswordFinal.this);
        initialiseView();
        email.setText(getIntent().getStringExtra("email"));
        email.setFocusable(false);
        email.setClickable(false);
        viewPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clicked)
                {
                    clicked=false;
                    viewPass.setImageResource(R.drawable.invisible);
                    // viewPass.setBackgroundResource(R.drawable.visible);
                    newPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else
                {
                    clicked=true;
                    viewPass.setImageResource(R.drawable.eye_open);
                    //  viewPass.setBackgroundResource(R.drawable.invisible);
                    newPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animFadein = AnimationUtils.loadAnimation(ForgetPasswordFinal.this, R.anim.fade_in);
                submit.startAnimation(animFadein);
                if (NetworkUtility.isNetworkConnectionAvailable(ForgetPasswordFinal.this)) {
                    if (validateFields()) {
                        showLoader("Please wait...");
                        ForgetPassword(email.getText().toString(),code.getText().toString(),newPass.getText().toString());
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

    private void ForgetPassword(String email1,String code1,String newPass1) {
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
            _obj.put("code", code1);
            _obj.put("npass", newPass1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Test request = new Test(_obj.toString());
        api.signForget(request, new Callback<SignInModel>() {
            @Override
            public void success(SignInModel signInModel, retrofit.client.Response response) {
                if(response.getStatus()==200) {
                    hideLoader();
                    if(signInModel.getCode()==419){
                        Snackbar snackbar = Snackbar.make(rlProductDetailActivity, AppConstants.tokenExpMsg, Snackbar.LENGTH_LONG);
                        snackbar.show();
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                        Intent i=new Intent(ForgetPasswordFinal.this, SignIn.class);
                        startActivity(i);
                        finish();
                        AppConstants.token="";
                    }else {
                        if(signInModel.getCode()==200) {
                            Snackbar snackbar = Snackbar.make(rlProductDetailActivity, signInModel.getMsg(), Snackbar.LENGTH_LONG);
                            snackbar.show();
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                            Intent i = new Intent(ForgetPasswordFinal.this, SignIn.class);
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
    @Override
    protected void onResume() {
        super.onResume();
        setMenuTitle("Forgot Password");
        hideLoader();
    }
    private void initialiseView() {
        email=(EditText)findViewById(R.id.email);
        code=(EditText)findViewById(R.id.code);
        newPass=(EditText)findViewById(R.id.newPass);
        submit=(Button) findViewById(R.id.submit);
        viewPass=(ImageView)findViewById(R.id.viewPass);
    }
    private boolean validateFields() {
        if (code.getText().toString().equals("")) {
            strErrorMessage = "Please Enter Code";
            return false;
        }
        else if (newPass.getText().toString().equals("")) {
            strErrorMessage = "Please Enter New Password";
            return false;
        }
        return true;
    }
}
