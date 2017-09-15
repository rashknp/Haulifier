package com.limitless.haulified.Haulifier.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.limitless.haulified.Haulifier.R;
import com.limitless.haulified.Haulifier.adapter.OfferQuoteAdapter1;
import com.limitless.haulified.Haulifier.common.AppConstants;
import com.limitless.haulified.Haulifier.common.Preference;
import com.limitless.haulified.Haulifier.interfaces.ApiInterface;
import com.limitless.haulified.Haulifier.model.OfferListResponse;
import com.limitless.haulified.Haulifier.utils.NetworkUtility;

import dmax.dialog.SpotsDialog;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Rashami on 27-Jun-17.
 */

public class OfferQuote1 extends BaseActivity implements View.OnClickListener {

    private CoordinatorLayout rlProductDetailActivity;
    private  RecyclerView mRecyclerview;
    OfferQuoteAdapter1 offerquoteadaptaer1;
    Preference prefe;
    TextView noData;
    @Override
    public void initialize() {
        rlProductDetailActivity = (CoordinatorLayout) inflater.inflate(R.layout.offer_quto1, null);
        llBody.addView(rlProductDetailActivity);
        initilizeView();
        prefe=new Preference(OfferQuote1.this);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerview.setLayoutManager(layoutManager);
    }

    private void getOfferList() {
         final AlertDialog dialog = new SpotsDialog(OfferQuote1.this);
        dialog.show();
        //Creating a rest adapter

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
        //Defining the method

        api.getOfferList(new Callback<OfferListResponse>() {
            @Override
            public void success(OfferListResponse offerlistresponse, Response response) {
                dialog.dismiss();

                if (response.getStatus() == 200) {
                    if(offerlistresponse.getCode()==419){
                        Snackbar snackbar = Snackbar.make(rlProductDetailActivity, AppConstants.tokenExpMsg, Snackbar.LENGTH_LONG);
                        snackbar.show();
                        Intent i=new Intent(OfferQuote1.this, com.limitless.haulified.Haulifier.Activity.SignIn.class);
                        startActivity(i);
                        finish();
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                        AppConstants.token="";
                    }else {
                        if (offerlistresponse.getData() != null) {
                            OfferQuoteAdapter1 adapter = new OfferQuoteAdapter1(offerlistresponse, OfferQuote1.this);
                            mRecyclerview.setAdapter(adapter);
                            noData.setVisibility(View.GONE);
                        }
                        else{
                            noData.setVisibility(View.VISIBLE);     }
                    }
                }


                else{
                    Toast.makeText(OfferQuote1.this, "Server Error, Please Try Again Later..", Toast.LENGTH_SHORT).show();
                }}
            @Override
            public void failure(RetrofitError error) {

                dialog.dismiss();
                Snackbar snackbar = Snackbar.make(rlProductDetailActivity, "Server Error", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
    }

    private void initilizeView() {
        mRecyclerview=(RecyclerView)findViewById(R.id.mRecyclerView);
        noData=(TextView)findViewById(R.id.noData);
    }

    @Override
    public void onClick(View v) {

    }
    @Override
    protected void onResume() {
        super.onResume();
        setMenuTitle("Offer A Quote");
        hideLoader();
        if (NetworkUtility.isNetworkConnectionAvailable(OfferQuote1.this)) {
            getOfferList();
        } else {
            Snackbar snackbar = Snackbar.make(rlProductDetailActivity, getString(R.string.no_internet), Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }
}
