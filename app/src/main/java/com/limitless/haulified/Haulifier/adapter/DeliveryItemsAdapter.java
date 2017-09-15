package com.limitless.haulified.Haulifier.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.limitless.haulified.Haulifier.R;
import com.limitless.haulified.Haulifier.model.DeliveryItemsModel;

import java.util.ArrayList;

/**
 * Created by Rashmi on 18-Jul-17.
 */

public class DeliveryItemsAdapter extends RecyclerView.Adapter<DeliveryItemsAdapter.ViewHolder> {
    Context cnt;
    ArrayList<DeliveryItemsModel> object;

public DeliveryItemsAdapter(ArrayList<DeliveryItemsModel> object, Context cnt) {
        this.cnt = cnt;
        this.object=object;
        }

@Override
public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_delivery, viewGroup, false);
        return new ViewHolder(view);
        }

@Override
public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.tv_name.setText("Name: "+object.get(i).getItemName());
        viewHolder.tv_type.setText("Type: "+object.get(i).getItemType());
       viewHolder.tv_capacity.setText("Capacity: "+((object.get(i).getSizeDimension().getLength())*(object.get(i).getSizeDimension().getHeight())*(object.get(i).getSizeDimension().getBreadth()))+" "+object.get(i).getSizeDimension().getUnit());
         }

@Override
public int getItemCount() {
    if (object!=null){
        return object.size();
    }
        else {
        return 0;
    }
        }

/*public void addItem(String country) {
    countries.add(country);
    notifyItemInserted(countries.size());
}

public void removeItem(int position) {
    countries.remove(position);
    notifyItemRemoved(position);
    notifyItemRangeChanged(position, countries.size());
}*/
public class ViewHolder extends RecyclerView.ViewHolder{
    TextView tv_name,tv_type,tv_capacity;
    public ViewHolder(View view) {
        super(view);

        tv_name= (TextView)view.findViewById(R.id.item_name);
        tv_type= (TextView)view.findViewById(R.id.item_type);
        tv_capacity= (TextView)view.findViewById(R.id.capacity);
    }
}

}
