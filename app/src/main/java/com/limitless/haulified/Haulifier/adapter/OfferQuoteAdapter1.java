package com.limitless.haulified.Haulifier.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.limitless.haulified.Haulifier.Activity.CommisionCalcutaor;
import com.limitless.haulified.Haulifier.R;
import com.limitless.haulified.Haulifier.common.AppConstants;
import com.limitless.haulified.Haulifier.model.Offer1ItemsModel;
import com.limitless.haulified.Haulifier.model.OfferListResponse;
import com.limitless.haulified.Haulifier.model.OfferQuoteModel1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Rashami on 27-Jun-17.
 */

public class OfferQuoteAdapter1 extends RecyclerView.Adapter<OfferQuoteAdapter1.MyViewHolder>{

    OfferListResponse offerListResponse;
    View view;
    Context cnt;
    Menu menu;
    ArrayList<Offer1ItemsModel> offer1ItemsModel=new ArrayList<Offer1ItemsModel>();
    int user_id;
    public OfferQuoteAdapter1(OfferListResponse offerListResponse, Context cnt) {
        this.offerListResponse = offerListResponse;
        this.cnt=cnt;
    }
    public OfferQuoteAdapter1(OfferListResponse offerListResponse, Context cnt, int user_id, Menu menu) {
        this.offerListResponse = offerListResponse;
        this.cnt=cnt;
        this.user_id=user_id;
        this.menu=menu;
    }
    private ArrayList<OfferQuoteModel1> offerQuoteModel1=null;

  Context mcontext;
    View itemView;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView booking_id,item_count,vehicle_avail,pickup_loc,delivery_loc,pickup_date,delivery_date,quotes,quotePrice;
        public MyViewHolder(View view) {
            super(view);
            quotes = (TextView)view.findViewById(R.id.quotes);
            booking_id= (TextView)view.findViewById(R.id.booking_id);
            item_count= (TextView)view.findViewById(R.id.item_count);
            vehicle_avail= (TextView)view.findViewById(R.id.vehicle_avail);
            pickup_loc= (TextView)view.findViewById(R.id.pickup_loc);
            delivery_loc= (TextView)view.findViewById(R.id.delivery_loc);
            pickup_date= (TextView)view.findViewById(R.id.pickup_date);
            delivery_date= (TextView)view.findViewById(R.id.destination_date);
            quotePrice= (TextView)view.findViewById(R.id.quote_price);
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.mcontext=parent.getContext();
         itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_quote_list1, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
            holder.quotes.setText("Commission");
           holder.quotes.setTextColor(Color.parseColor("#FF28CF3A"));
           holder.vehicle_avail.setTextColor(Color.parseColor("#FF28CF3A"));
           holder.vehicle_avail.setText(offerListResponse.getData().get(position).getCommission()+"%");

       if(offerListResponse.getData().get(position).getQuotePrice()==0.0){
           holder.quotePrice.setText(0+"");
       }
       else {
           holder.quotePrice.setText(offerListResponse.getData().get(position).getQuotePrice() + "");
       }
            holder.booking_id.setText("Booking id- "+offerListResponse.getData().get(position).getBookingId()+"");
        if(offerListResponse.getData().get(position).getStatus().equalsIgnoreCase("init")){
            holder.item_count.setTextColor(Color.parseColor("#ffffff"));
            holder.item_count.setBackgroundResource(R.color.colorPrimary);
            holder.item_count.setText("Publish");
            if(menu!=null) {
                menu.getItem(2).setVisible(true);
                menu.getItem(2).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent i = new Intent(cnt, CommisionCalcutaor.class);
                        i.putExtra("user_id", user_id);
                        cnt.startActivity(i);
                        return false;
                    }
                });
            }

        }
        else{
            holder.item_count.setTextColor(Color.parseColor("#38C08E"));
            holder.item_count.setText(offerListResponse.getData().get(position).getStatus()+"");
        }

        final String pic_city=offerListResponse.getData().get(position).getPickupAddress();
        final String del_city=offerListResponse.getData().get(position).getDestinationAddress();
        holder.pickup_loc.setText((pic_city));
        holder.delivery_loc.setText(del_city);
           int p=offerListResponse.getData().get(position).getExpPickupDate().indexOf('T');
      String newDateString_pic="",newDateString_del="";
        try {
            String date_pic=offerListResponse.getData().get(position).getExpPickupDate();
            String date_del=offerListResponse.getData().get(position).getExpDestinationDate();
            SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate_pic = newDateFormat.parse(date_pic);
            Date myDate_del = newDateFormat.parse(date_del);
            newDateFormat.applyPattern("dd-MM-yyyy");
            newDateString_pic = newDateFormat.format(myDate_pic);
            newDateString_del = newDateFormat.format(myDate_del);
        }
        catch (Exception e){
            e.getMessage();
        }
            final String pick_date=offerListResponse.getData().get(position).getExpPickupDate().substring(0,p);
            int d=offerListResponse.getData().get(position).getExpDestinationDate().indexOf('T');
            final String del_date=offerListResponse.getData().get(position).getExpDestinationDate().substring(0,p);
            holder.pickup_date.setText(newDateString_pic);
            holder.delivery_date.setText(newDateString_del);

      //  }
        if( holder.item_count.getText().toString().equalsIgnoreCase("Publish")){
            holder.item_count.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1=new Intent(cnt, CommisionCalcutaor.class);
                    i1.putExtra("quote_id",offerListResponse.getData().get(position).getQuoteId());
                    i1.putExtra("quote_price",offerListResponse.getData().get(position).getQuotePrice());
                    i1.putExtra("booking_id",offerListResponse.getData().get(position).getBookingId());
                    i1.putExtra("user_id",user_id);
                    cnt.startActivity(i1);
                }
            });



        }
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.quotes.getText().toString().equalsIgnoreCase(AppConstants.Available_Vehicle)){
                             }
                else if(holder.quotes.getText().toString().equalsIgnoreCase(AppConstants.Assigned_Vehicle)) {


                }
            }
        });

    }

    @Override
    public int getItemCount() {
       if (offerListResponse.getData().size()>0) {
            return offerListResponse.getData().size();

        }
        else {
           Toast.makeText(cnt,"No data",Toast.LENGTH_SHORT).show();
            return 0;
        }
    }



}