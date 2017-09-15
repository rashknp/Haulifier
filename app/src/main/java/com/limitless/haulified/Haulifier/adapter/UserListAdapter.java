package com.limitless.haulified.Haulifier.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.limitless.haulified.Haulifier.Activity.UserDetails;
import com.limitless.haulified.Haulifier.R;
import com.limitless.haulified.Haulifier.model.UserListResponse;

/**
 * Created by Rashami on 27-Jun-17.
 */

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
    UserListResponse userListResponse;
    Context cnt;
    int pos;

    public UserListAdapter(UserListResponse userListResponse, Context cnt) {
        this.userListResponse = userListResponse;
        this.cnt=cnt;
    }
    public UserListAdapter(int pos, Context cnt) {
        this.pos = pos;
        this.cnt=cnt;
    }
    public UserListAdapter( Context cnt) {
        this.cnt=cnt;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_user_list, viewGroup, false);
        return new ViewHolder(view);
    }
    public void refresh(UserListResponse userListResponse) {
        this.userListResponse = userListResponse;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {

       viewHolder.name.setText(userListResponse.getData().get(i).getFullName());
        viewHolder.email.setText(userListResponse.getData().get(i).getEmail());
       // viewHolder.role.setText(userListResponse.getData().get(i).getRole());
       // viewHolder.created_by.setText(userListResponse.getData().get(i).getCreatedBy());
        viewHolder.frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(cnt, UserDetails.class);
                i1.putExtra("mode", "Edit_user");
                i1.putExtra("id",userListResponse.getData().get(i).getUserId());
                i1.putExtra("name",userListResponse.getData().get(i).getFullName()+"");
                i1.putExtra("email",userListResponse.getData().get(i).getEmail()+"");
                cnt.startActivity(i1);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(userListResponse.getData() != null ){
            return userListResponse.getData().size();
        }

        else {
           // Toast.makeText(cnt,"No data",Toast.LENGTH_SHORT).show();
            return 0;

        }
         }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,email,role,created_by;
        CardView frame;
        public ViewHolder(View view) {
            super(view);
            name = (TextView)view.findViewById(R.id.et_username);
            email= (TextView)view.findViewById(R.id.et_email);
            role= (TextView)view.findViewById(R.id.role);
            created_by= (TextView)view.findViewById(R.id.created_by);
           frame=(CardView)view.findViewById(R.id.lyt_container);
        }
    }

}