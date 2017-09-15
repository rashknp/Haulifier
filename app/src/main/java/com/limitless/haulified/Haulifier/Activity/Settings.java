package com.limitless.haulified.Haulifier.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.auth0.android.jwt.JWT;
import com.limitless.haulified.Haulifier.R;
import com.limitless.haulified.Haulifier.adapter.TruckerListAdapter;
import com.limitless.haulified.Haulifier.common.AppConstants;
import com.limitless.haulified.Haulifier.common.Preference;
import com.limitless.haulified.Haulifier.common.Test;
import com.limitless.haulified.Haulifier.interfaces.ApiInterface;
import com.limitless.haulified.Haulifier.model.SettingsResponse;
import com.limitless.haulified.Haulifier.model.SignInModel;
import com.limitless.haulified.Haulifier.model.TruckerListResponse;
import com.limitless.haulified.Haulifier.utils.NetworkUtility;

import org.json.JSONException;
import org.json.JSONObject;

import dmax.dialog.SpotsDialog;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by Rashmi on 04-Sep-17.
 */

public class Settings extends BaseActivity {
    private LinearLayout rlProductDetailActivity;
    private RadioButton auto,manual;
    private Button save;
    int check_id=0;
    Preference prefe;
    private EditText current_commission,new_commission;
    SettingsResponse settingResponsefinal = new SettingsResponse();
    int newCom;
    @Override
    public void initialize() {
        rlProductDetailActivity = (LinearLayout) inflater.inflate(R.layout.settings, null);
        llBody.addView(rlProductDetailActivity);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        initialiseView();
        prefe = new Preference(Settings.this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        auto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    current_commission.setText(newCom+"");
                    new_commission.setText("");
                    check_id=0;
                    /*if (NetworkUtility.isNetworkConnectionAvailable(Settings.this)) {
                        GetSettings();
                    } else {
                        Snackbar snackbar = Snackbar.make(rlProductDetailActivity, getString(R.string.no_internet), Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }*/

                }
            }
        });
        manual.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    current_commission.setText("");
                    new_commission.setText("");
                    check_id=1;
                }
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animFadein = AnimationUtils.loadAnimation(Settings.this, R.anim.fade_in);
                save.startAnimation(animFadein);
                if (NetworkUtility.isNetworkConnectionAvailable(Settings.this)) {
                        showLoader("Please wait...");
                    if(check_id==0){
                       if(!new_commission.getText().toString().equalsIgnoreCase("")){
                           changeState(check_id);
                       }
                       else {
                           Snackbar snackbar = Snackbar.make(rlProductDetailActivity, "Please Enter New Commission", Snackbar.LENGTH_LONG);
                           snackbar.show();
                       }
                    }
                    else if(check_id==1){
                        changeState(check_id);
                    }

                } else {
                    Snackbar snackbar = Snackbar.make(rlProductDetailActivity, R.string.no_internet, Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                hideLoader();
            }
        });
    }

    private void changeState(int check_id) {
        showLoader("Please wait...");
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("authorization", "JWT " + prefe.getStringFromPreference(Preference.token, ""));
            }
        };
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(AppConstants.BASE_URL)
                .setRequestInterceptor(requestInterceptor)
                .build();

        //Creating an object of our api interface
        ApiInterface api = adapter.create(ApiInterface.class);
        JSONObject _obj = new JSONObject();
        int newComm=0;
        //preparing request
        if(check_id==1){
            newComm=0;
        }
        else  if(check_id==0){
            newComm=Integer.parseInt(new_commission.getText().toString());
        }

        try {
            _obj.put("settings", check_id);
            _obj.put("percent", newComm);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Test request = new Test(_obj.toString());
        api.settings(request, new Callback<SignInModel>() {
            @Override
            public void success(SignInModel signInModel, retrofit.client.Response response) {
                hideLoader();

                if (signInModel.getCode() == 201) {
                    if (signInModel != null) {
                        Snackbar snackbar1 = Snackbar.make(rlProductDetailActivity, signInModel.getMsg(), Snackbar.LENGTH_LONG);
                        snackbar1.show();
                        if (NetworkUtility.isNetworkConnectionAvailable(Settings.this)) {
                            GetSettings();
                        } else {
                            Snackbar snackbar = Snackbar.make(rlProductDetailActivity, getString(R.string.no_internet), Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
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

    private void initialiseView() {
        auto=(RadioButton)findViewById(R.id.auto);
        manual=(RadioButton)findViewById(R.id.manual);
        save=(Button)findViewById(R.id.save);
        current_commission=(EditText)findViewById(R.id.commission);
        new_commission=(EditText)findViewById(R.id.new_commission);
    }
    @Override
    protected void onResume() {
        super.onResume();
        setMenuTitle("Settings");
        hideLoader();
        if (NetworkUtility.isNetworkConnectionAvailable(Settings.this)) {
            GetSettings();
        } else {
            Snackbar snackbar = Snackbar.make(rlProductDetailActivity, getString(R.string.no_internet), Snackbar.LENGTH_LONG);
            snackbar.show();
        }

    }

    private void GetSettings() {
        // final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);
        final AlertDialog dialog = new SpotsDialog(Settings.this);
        dialog.show();
        //Creating a rest adapter
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("authorization", "JWT " + prefe.getStringFromPreference(Preference.token, ""));
            }
        };
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(AppConstants.BASE_URL)
                .setRequestInterceptor(requestInterceptor)
                .build();

        //Creating an object of our api interface
        ApiInterface api = adapter.create(ApiInterface.class);
        //Defining the method

        api.getsettings(new Callback<SettingsResponse>() {
            @Override
            public void success(SettingsResponse settingResponse, Response response) {
                dialog.dismiss();
                settingResponsefinal = settingResponse;
                if (response.getStatus() == 200) {
                    if (settingResponse.getCode() == 419) {
                        Snackbar snackbar = Snackbar.make(rlProductDetailActivity, AppConstants.tokenExpMsg, Snackbar.LENGTH_LONG);
                        snackbar.show();
                        Intent i = new Intent(Settings.this, com.limitless.haulified.Haulifier.Activity.SignIn.class);
                        startActivity(i);
                        finish();
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                        AppConstants.token = "";
                    } else {
                        if (settingResponse.getData() != null) {
                           if(settingResponse.getData().get(0).getType().equalsIgnoreCase("manual")){
                               manual.setChecked(true);


                           }
                           else if(settingResponse.getData().get(0).getType().equalsIgnoreCase("auto")){
                               auto.setChecked(true);
                               newCom=settingResponse.getData().get(0).getPercent();
                               current_commission.setText(settingResponse.getData().get(0).getPercent()+"");
                               new_commission.setText("");
                           }


                        } else {
                            Snackbar snackbar = Snackbar.make(rlProductDetailActivity, "Server Error", Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
                    }
                } else {
                    Snackbar snackbar = Snackbar.make(rlProductDetailActivity, "Server Error", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }

            @Override
            public void failure(RetrofitError error) {

                dialog.dismiss();
                Snackbar snackbar = Snackbar.make(rlProductDetailActivity, "Server Error", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
    }
}
