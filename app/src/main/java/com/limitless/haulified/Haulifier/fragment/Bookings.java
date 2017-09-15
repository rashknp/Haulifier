package com.limitless.haulified.Haulifier.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.limitless.haulified.Haulifier.Activity.CommisionCalcutaor;
import com.limitless.haulified.Haulifier.Activity.OfferQuote1;
import com.limitless.haulified.Haulifier.R;
import com.limitless.haulified.Haulifier.adapter.MyDeliveryAdapter;
import com.limitless.haulified.Haulifier.adapter.OfferQuoteAdapter1;
import com.limitless.haulified.Haulifier.common.AppConstants;
import com.limitless.haulified.Haulifier.common.Preference;
import com.limitless.haulified.Haulifier.interfaces.ApiInterface;
import com.limitless.haulified.Haulifier.model.MyDeliveryResponse;
import com.limitless.haulified.Haulifier.model.OfferListResponse;
import com.limitless.haulified.Haulifier.utils.NetworkUtility;

import dmax.dialog.SpotsDialog;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Rashmi on 04-Sep-17.
 */

public class Bookings extends Fragment {
    RecyclerView mRecyclerview;
    Preference prefe;
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    int id_account,id_user;
    MenuItem mMenu;
    private TextView noData;
    Menu menu1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.deliveries_fragment, container, false);
        prefe=new Preference(getActivity());
        mRecyclerview = (RecyclerView)v.findViewById(R.id.mRecyclerView);
        noData=(TextView)v.findViewById(R.id.noData);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerview.setLayoutManager(layoutManager);
        setHasOptionsMenu(true);
        if(getArguments()!=null) {
            id_account=getArguments().getInt("account_id");
            id_user=getArguments().getInt("user_id");
        }

        return v;
    }
    @Override
    public void onCreateOptionsMenu( Menu menu,MenuInflater inflater) {
        // Do something that differs the Activity's menu here
       menu1=menu;
        if (NetworkUtility.isNetworkConnectionAvailable(getActivity())) {
            getBookingList();
        }
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public void onResume() {
        super.onResume();
        if (NetworkUtility.isNetworkConnectionAvailable(getActivity())) {
            getBookingList();
        } else {
           /* Snackbar snackbar = Snackbar.make(rlProductDetailActivity, getString(R.string.no_internet), Snackbar.LENGTH_LONG);
            snackbar.show();*/
        }

    }

    private void getBookingList() {
        final AlertDialog dialog = new SpotsDialog(getActivity());
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

        api.getBookingsList(id_account,id_user,new Callback<OfferListResponse>() {
            @Override
            public void success(OfferListResponse offerListResponse, Response response) {
                dialog.dismiss();

                if (response.getStatus() == 200) {
                    if (offerListResponse.getData().size()>0) {
                        // overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                        OfferQuoteAdapter1 adapter = new OfferQuoteAdapter1(offerListResponse, getActivity(),id_user,menu1);
                        mRecyclerview.setAdapter(adapter);
                         noData.setVisibility(View.GONE);
                                            }
                    else {
                        if(menu1!=null) {
                            menu1.getItem(2).setVisible(false);
                        }
                       noData.setText("No booking request(s) available for this trucker");
                         noData.setVisibility(View.VISIBLE);
                    }
                }


                else{
                    Toast.makeText(getActivity(), "Server Error, Please Try Again Later..", Toast.LENGTH_SHORT).show();
                }}
            @Override
            public void failure(RetrofitError error) {

                dialog.dismiss();
                /*Snackbar snackbar = Snackbar.make(rlProductDetailActivity, "Server Error", Snackbar.LENGTH_LONG);
                snackbar.show();*/
            }
        });
    }
}
