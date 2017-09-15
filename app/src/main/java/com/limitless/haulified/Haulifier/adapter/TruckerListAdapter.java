package com.limitless.haulified.Haulifier.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.limitless.haulified.Haulifier.Activity.DeliveriesActivity;
import com.limitless.haulified.Haulifier.DeliveryStatusDialog;
import com.limitless.haulified.Haulifier.R;
import com.limitless.haulified.Haulifier.model.TruckerListResponse;

/**
 * Created by Rashami on 27-Jun-17.
 */

public class TruckerListAdapter extends RecyclerView.Adapter<TruckerListAdapter.ViewHolder> {
    TruckerListResponse truckerListResponse;
    Context cnt;
    int pos;

    public TruckerListAdapter(TruckerListResponse truckListModel, Context cnt) {
        this.truckerListResponse = truckListModel;
        this.cnt=cnt;
    }
    public TruckerListAdapter( Context cnt) {
        this.cnt=cnt;
    }
    public void refresh(TruckerListResponse driverResponse) {
        this.truckerListResponse = driverResponse;
        notifyDataSetChanged();
    }
    public TruckerListAdapter() {
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
      View  view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout_truck_list, viewGroup, false);
        return new ViewHolder(view);
    }
    public void remove(int position) {
        truckerListResponse.getData().remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.tv_truck_type.setText(truckerListResponse.getData().get(i).getFullName());
        viewHolder.tv_emai.setText(truckerListResponse.getData().get(i).getEmail());
        viewHolder.tv_contact.setText(truckerListResponse.getData().get(i).getPhone());
        if(truckerListResponse.getData().get(i).getStatus().equalsIgnoreCase("ACTIVE")){
            viewHolder.tv_status.setBackgroundColor(Color.parseColor("#8ccb43"));
        }
        else  if(truckerListResponse.getData().get(i).getStatus().equalsIgnoreCase("INACTIVE")){
            viewHolder.tv_status.setBackgroundColor(Color.parseColor("#ff0000"));
        }
       else  if(truckerListResponse.getData().get(i).getStatus().equalsIgnoreCase("SUSPEND")){
            viewHolder.tv_status.setBackgroundColor(Color.parseColor("#3f6dbd"));
        }
        viewHolder.tv_status.setText(truckerListResponse.getData().get(i).getStatus());

viewHolder.container.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i1=new Intent(cnt, DeliveriesActivity.class);
        i1.putExtra("account_id",truckerListResponse.getData().get(i).getAccountId());
        i1.putExtra("user_id",truckerListResponse.getData().get(i).getUserId());
        cnt.startActivity(i1);


    }
});
        viewHolder.tv_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DeliveryStatusDialog(cnt,(truckerListResponse.getData().get(i).getUserId())).showItemPopupNew();

            }
        });

    }

    @Override
    public int getItemCount() {

        if(truckerListResponse.getData() != null ){
            return truckerListResponse.getData().size();
        }

        else {
           // Toast.makeText(cnt,"No data found",Toast.LENGTH_SHORT).show();
            return 0;

        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_truck_type,tv_emai,tv_contact,tv_status;
       FrameLayout container;
        public ViewHolder(View view) {
            super(view);

            tv_truck_type = (TextView)view.findViewById(R.id.truck_type);
            tv_emai = (TextView)view.findViewById(R.id.email);
            tv_contact = (TextView)view.findViewById(R.id.contact_number);
            tv_status = (TextView)view.findViewById(R.id.status);
            container=(FrameLayout)view.findViewById(R.id.lyt_container);
        }
    }
}