package com.limitless.haulified.Haulifier.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.limitless.haulified.Haulifier.Activity.DeliveryItemDetails;
import com.limitless.haulified.Haulifier.DeliveryStatusDialog;
import com.limitless.haulified.Haulifier.R;
import com.limitless.haulified.Haulifier.model.DeliveryItemsModel;
import com.limitless.haulified.Haulifier.model.MyDeliveryResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Rashmi on 17-Jul-17.
 */

public class MyDeliveryAdapter extends RecyclerView.Adapter<MyDeliveryAdapter.ViewHolder> {
    Context context;
    MyDeliveryResponse myDeliveryResponse;
    ArrayList<DeliveryItemsModel> deliveryItemsModels=new ArrayList<DeliveryItemsModel>();
    View itemView;
    public MyDeliveryAdapter(MyDeliveryResponse myDeliveryResponse, Context context) {
        this.myDeliveryResponse = myDeliveryResponse;
        this.context =context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adpater_deliverivery, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final  int i) {
        String newDateString_pic="",newDateString_del="";
        try {
            String date_pic=myDeliveryResponse.getData().get(i).getPickupDate();
            String date_del=myDeliveryResponse.getData().get(i).getDeliveryDate();
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
        viewHolder.tv_order_id.setText(""+myDeliveryResponse.getData().get(i).getBookingId());
        viewHolder.tv_items.setText("Items: "+myDeliveryResponse.getData().get(i).getItemCount());
        viewHolder.tv_del_date.setText(newDateString_del);
        viewHolder.tv_pick_date.setText(newDateString_pic);
        viewHolder.tv_pic_loc.setText(myDeliveryResponse.getData().get(i).getPickupLoc().getAddress());
        viewHolder.tv_del_loc.setText(myDeliveryResponse.getData().get(i).getDeliveryLoc().getAddress());
        if(myDeliveryResponse.getData().get(i).getStatus().equalsIgnoreCase("Confirmed")){
            viewHolder.tv_status.setText("#8ccb43");
        }
        else  if(myDeliveryResponse.getData().get(i).getStatus().equalsIgnoreCase("Picked Up")){
            viewHolder.tv_status.setText("#ff0000");
        }
        else  if(myDeliveryResponse.getData().get(i).getStatus().equalsIgnoreCase("In Delivery")){
            viewHolder.tv_status.setText("#3f6dbd");
        }
        else  if(myDeliveryResponse.getData().get(i).getStatus().equalsIgnoreCase("Delivered")){
            viewHolder.tv_status.setText("#3f6dbd");
        }
        viewHolder.tv_status.setText((myDeliveryResponse.getData().get(i).getStatus()).toUpperCase());

       viewHolder.tv_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new DeliveryStatusDialog(context,Integer.parseInt(myDeliveryResponse.getData().get(i).getOrderId())).showItemPopupNew();
            }
        });
      itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               double price=myDeliveryResponse.getData().get(i).getQuotePrice();
               Intent intent=new Intent(context, DeliveryItemDetails.class);
               deliveryItemsModels = myDeliveryResponse.getData().get(i).getItems();
               intent.putExtra("model",deliveryItemsModels);
               intent.putExtra("price",price);
               context.startActivity(intent);

           }
       });
    }

    @Override
    public int getItemCount() {
     //   if(myDeliveryResponse.getData()!=null)
        //{
            return myDeliveryResponse.getData().size();
       // }
        /*else {
            Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show();
            return 0;
        }
*/


    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_order_id,tv_items,tv_del_date,tv_pick_date,tv_pic_loc,tv_del_loc,tv_status;

        public ViewHolder(View view) {
            super(view);
               tv_items = (TextView) view.findViewById(R.id.tvItemsCount);
            tv_del_date = (TextView) view.findViewById(R.id.tvdestination_date);
            tv_pick_date = (TextView) view.findViewById(R.id.tvpickup_date);
            tv_pic_loc = (TextView) view.findViewById(R.id.pickup_location);
            tv_del_loc = (TextView) view.findViewById(R.id.delivery_location);
            tv_status = (TextView) view.findViewById(R.id.tvStatus);
            tv_order_id = (TextView) view.findViewById(R.id.order_id);

        }
    }
}