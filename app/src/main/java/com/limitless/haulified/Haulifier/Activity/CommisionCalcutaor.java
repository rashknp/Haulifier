package com.limitless.haulified.Haulifier.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.limitless.haulified.Haulifier.R;
import com.limitless.haulified.Haulifier.adapter.OfferQuoteAdapter1;
import com.limitless.haulified.Haulifier.common.AppConstants;
import com.limitless.haulified.Haulifier.common.Preference;
import com.limitless.haulified.Haulifier.common.Test;
import com.limitless.haulified.Haulifier.interfaces.ApiInterface;
import com.limitless.haulified.Haulifier.model.OfferListResponse;
import com.limitless.haulified.Haulifier.model.SignInModel;

import org.json.JSONException;
import org.json.JSONObject;

import dmax.dialog.SpotsDialog;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Rashmi on 06-Sep-17.
 */

public class CommisionCalcutaor extends BaseActivity {

    private LinearLayout rlProductDetailActivity;
    Preference prefe;
    private TextView booking_id,booking_price,quote_price,commission_price;
    private  LinearLayout bookText;
    private EditText commission;
    private TextView publish,calculate,pickup_location;
     double quote;
    int booking;
    @Override
    public void initialize() {
        rlProductDetailActivity = (LinearLayout) inflater.inflate(R.layout.commision_calculator, null);
        llBody.addView(rlProductDetailActivity);
        prefe = new Preference(CommisionCalcutaor.this);
        initializeview();

       booking=getIntent().getIntExtra("booking_id",0);
        if(booking!=0) {
            quote = getIntent().getDoubleExtra("quote_price", 0);
            booking_id.setText("Booking Id- " + booking);
            quote_price.setText(quote + "");
            booking_price.setText(quote + "");
        }
        else {
            pickup_location.setVisibility(View.GONE);
            bookText.setVisibility(View.GONE);
            commission_price.setVisibility(View.GONE);
            quote_price.setVisibility(View.GONE);
            booking_id.setVisibility(View.GONE);
            calculate.setVisibility(View.GONE);
            booking_price.setVisibility(View.GONE);

        }
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    double commission_text = Double.parseDouble(commission.getText().toString());

                    double commission_value = (quote / 100) * commission_text;
                    commission_price.setText(commission_value + "");
                    double booking_value = quote + commission_value;
                    booking_price.setText(booking_value + "");
            }
        });
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(booking!=0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CommisionCalcutaor.this);
                    builder.setMessage("Do you want to continue?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    commissionSubmit();
                                }

                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                  AlertDialog alert = builder.create();
                    alert.setTitle("No Commission is applied to this booking ");
                    alert.show();
                }
            else{
                AlertDialog.Builder builder = new AlertDialog.Builder(CommisionCalcutaor.this);
                builder.setMessage("Do you want to continue?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                commissionSubmitAll();
                            }

                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("No Commission is applied to this booking ");
                alert.show();
            }
            }
        });

    }

    private void commissionSubmitAll() {
        final AlertDialog dialog = new SpotsDialog(CommisionCalcutaor.this);
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
        JSONObject _obj = new JSONObject();
        //preparing request
        int user_id=getIntent().getIntExtra("user_id",0);
        double comm=Double.parseDouble(commission.getText().toString());
        try {
            _obj.put("userId", user_id);
            _obj.put("percent", comm);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Test request = new Test(_obj.toString());

        api.commissioncalculatorpublishAll(request,new Callback<SignInModel>() {
            @Override
            public void success(SignInModel offerlistresponse, Response response) {
                dialog.dismiss();

                if (response.getStatus() == 200) {
                    if (offerlistresponse.getCode() == 419) {
                        Snackbar snackbar = Snackbar.make(rlProductDetailActivity, AppConstants.tokenExpMsg, Snackbar.LENGTH_LONG);
                        snackbar.show();
                        Intent i = new Intent(CommisionCalcutaor.this, com.limitless.haulified.Haulifier.Activity.SignIn.class);
                        startActivity(i);
                        finish();
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                        AppConstants.token = "";
                    } else {
                        finish();
                    }
                }
                else{
                    Toast.makeText(CommisionCalcutaor.this, "Server Error, Please Try Again Later..", Toast.LENGTH_SHORT).show();
                }}
            @Override
            public void failure(RetrofitError error) {

                dialog.dismiss();
                Snackbar snackbar = Snackbar.make(rlProductDetailActivity, "Server Error", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
    }

    private void commissionSubmit() {
        final AlertDialog dialog = new SpotsDialog(CommisionCalcutaor.this);
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
        JSONObject _obj = new JSONObject();
        //preparing request
        int quote_id=getIntent().getIntExtra("quote_id",0);
        int user_id=getIntent().getIntExtra("user_id",0);
        try {
            int per=Integer.parseInt(commission.getText().toString());
            _obj.put("quoteId", quote_id);
            _obj.put("userId", user_id);
            _obj.put("quotePrice", quote);
            _obj.put("percent", per);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Test request = new Test(_obj.toString());

        api.commissioncalculator(request,new Callback<SignInModel>() {
            @Override
            public void success(SignInModel offerlistresponse, Response response) {
                dialog.dismiss();

                if (response.getStatus() == 200) {
                    if (offerlistresponse.getCode() == 419) {
                        Snackbar snackbar = Snackbar.make(rlProductDetailActivity, AppConstants.tokenExpMsg, Snackbar.LENGTH_LONG);
                        snackbar.show();
                        Intent i = new Intent(CommisionCalcutaor.this, com.limitless.haulified.Haulifier.Activity.SignIn.class);
                        startActivity(i);
                        finish();
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                        AppConstants.token = "";
                    } else {
                        if(offerlistresponse.getSuccess()) {
                            finish();
                        }

                        else {
                            Snackbar snackbar = Snackbar.make(rlProductDetailActivity, "Server Error, Please Try Again Later..", Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
                    }
                }
                else{
                    Toast.makeText(CommisionCalcutaor.this, "Server Error, Please Try Again Later..", Toast.LENGTH_SHORT).show();
                }}
            @Override
            public void failure(RetrofitError error) {

                dialog.dismiss();
                Snackbar snackbar = Snackbar.make(rlProductDetailActivity, "Server Error", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
    }

    private void initializeview() {
        booking_id=(TextView)findViewById(R.id.booking_id);
        booking_price=(TextView)findViewById(R.id.booking_price);
        quote_price=(TextView)findViewById(R.id.quote_value);
        commission_price=(TextView)findViewById(R.id.commision_price);
        commission=(EditText)findViewById(R.id.commission);
        publish=(TextView)findViewById(R.id.publish);
        calculate=(TextView)findViewById(R.id.calculate);
        bookText=(LinearLayout)findViewById(R.id.bookText);
        pickup_location=(TextView)findViewById(R.id.pickup_location);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setMenuTitle("Commission Calculator");
        hideLoader();
    }
}
