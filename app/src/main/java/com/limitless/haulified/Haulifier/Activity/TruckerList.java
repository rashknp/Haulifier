package com.limitless.haulified.Haulifier.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.limitless.haulified.Haulifier.R;
import com.limitless.haulified.Haulifier.adapter.TruckerListAdapter;
import com.limitless.haulified.Haulifier.common.AppConstants;
import com.limitless.haulified.Haulifier.common.Preference;
import com.limitless.haulified.Haulifier.interfaces.ApiInterface;
import com.limitless.haulified.Haulifier.model.TruckerListResponse;
import com.limitless.haulified.Haulifier.utils.NetworkUtility;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Rashami on 27-Jun-17.
 */

public class TruckerList extends BaseActivity  {
    private CoordinatorLayout rlProductDetailActivity;
    private RecyclerView recyclerView;
    private TruckerListAdapter adapter;
    private ArrayList<String> countries = new ArrayList<>();
    private View view;
    private boolean add = false;
    private Paint p = new Paint();
    private static final int TIME_TO_AUTOMATICALLY_DISMISS_ITEM = 6000;
    String token = "";
    Preference prefe;
    TruckerListResponse truckerListResponsefinal = new TruckerListResponse();
    TextView noData;
    AlertDialog alertDialog1;
    TruckerListAdapter adapter1;

    @Override
    public void initialize() {
        rlProductDetailActivity = (CoordinatorLayout) inflater.inflate(R.layout.trucker_list, null);
        llBody.addView(rlProductDetailActivity);
        prefe = new Preference(TruckerList.this);
        initilizeView();


    }

    private void initilizeView() {
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        noData = (TextView) findViewById(R.id.noData);
        recyclerView.setAdapter(adapter1 = new TruckerListAdapter(new TruckerListResponse(), TruckerList.this));
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setMenuTitle("Trucker List");
        hideLoader();
        if (NetworkUtility.isNetworkConnectionAvailable(TruckerList.this)) {
            GetTruckList();
        } else {
            Snackbar snackbar = Snackbar.make(rlProductDetailActivity, getString(R.string.no_internet), Snackbar.LENGTH_LONG);
            snackbar.show();
        }


    }

    private void GetTruckList() {
        // final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);
        final AlertDialog dialog = new SpotsDialog(TruckerList.this);
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

        api.getTruckerList(new Callback<TruckerListResponse>() {
            @Override
            public void success(TruckerListResponse truckerListResponse, Response response) {
                dialog.dismiss();
                truckerListResponsefinal = truckerListResponse;
                if (response.getStatus() == 200) {
                    if (truckerListResponse.getCode() == 419) {
                        Snackbar snackbar = Snackbar.make(rlProductDetailActivity, AppConstants.tokenExpMsg, Snackbar.LENGTH_LONG);
                        snackbar.show();
                        Intent i = new Intent(TruckerList.this, com.limitless.haulified.Haulifier.Activity.SignIn.class);
                        startActivity(i);
                        finish();
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                        AppConstants.token = "";
                    } else {
                        if (truckerListResponse.getData() != null) {
                            noData.setVisibility(View.GONE);
                            adapter1 = new TruckerListAdapter(truckerListResponse, TruckerList.this);
                            recyclerView.setAdapter(adapter1);

                        } else {
                            adapter1.refresh(new TruckerListResponse());
                            noData.setVisibility(View.VISIBLE);
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