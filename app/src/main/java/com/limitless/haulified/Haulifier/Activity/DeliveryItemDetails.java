package com.limitless.haulified.Haulifier.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.limitless.haulified.Haulifier.R;
import com.limitless.haulified.Haulifier.adapter.DeliveryItemsAdapter;
import com.limitless.haulified.Haulifier.model.DeliveryItemsModel;

import java.util.ArrayList;

/**
 * Created by Rashmi on 18-Jul-17.
 */

public class DeliveryItemDetails extends BaseActivity {
    private  RecyclerView mRecyclerview;
    private TextView cancel,adduser,save,price;
    ArrayList<DeliveryItemsModel> object;
    private ScrollView rlProductDetailActivity;
   /* @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_delivery);
        }*/

    @Override
    public void initialize() {
        rlProductDetailActivity = (ScrollView) inflater.inflate(R.layout.item_delivery, null);
        llBody.addView(rlProductDetailActivity);
        double pricee=getIntent().getDoubleExtra("price",0);
        //hide keyboard in starting
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        object = (ArrayList<DeliveryItemsModel>)getIntent().getSerializableExtra("model");
        initilizeView();
        price.setText("Quote Price: "+pricee+"");
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerview.setLayoutManager(layoutManager);
        DeliveryItemsAdapter adapter=new  DeliveryItemsAdapter(object,DeliveryItemDetails.this);
        mRecyclerview.setAdapter(adapter);
        /*cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/

    }

    private void initilizeView() {
        mRecyclerview=(RecyclerView)findViewById(R.id.mRecyclerView);
       // save=(TextView)findViewById(R.id.save);
        price=(TextView)findViewById(R.id.price);
       // cancel=(TextView)findViewById(R.id.cancel);
       // adduser=(TextView)findViewById(R.id.addUser);
        //adduser.setText("Items Details");
       // save.setText("");
    }
    @Override
    protected void onResume() {
        super.onResume();
        setMenuTitle("Items Details");
        hideLoader();
    }
}
