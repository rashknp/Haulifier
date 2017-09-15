package com.limitless.haulified.Haulifier.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;

import com.limitless.haulified.Haulifier.R;
import com.limitless.haulified.Haulifier.adapter.UserListAdapter;
import com.limitless.haulified.Haulifier.common.AppConstants;
import com.limitless.haulified.Haulifier.common.Preference;
import com.limitless.haulified.Haulifier.common.Test;
import com.limitless.haulified.Haulifier.interfaces.ApiInterface;
import com.limitless.haulified.Haulifier.model.SignUpResponse;
import com.limitless.haulified.Haulifier.model.UserListResponse;
import com.limitless.haulified.Haulifier.utils.NetworkUtility;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Rashami on 26-Jun-17.
 */

public class UserList extends BaseActivity implements View.OnClickListener {
    private CoordinatorLayout rlProductDetailActivity;
    private RecyclerView recyclerView;
    private UserListAdapter adapter;
    private ArrayList<String> countries =  new ArrayList<>();
    private View view;
    private boolean add = false;
    private Paint p = new Paint();
    Preference prefe;
    UserListResponse userListResponseFinal;
    TextView noData;
    AlertDialog alertDialog1;
    UserListAdapter adapter1;

    @Override
    public void initialize() {
        rlProductDetailActivity = (CoordinatorLayout) inflater.inflate(R.layout.user_list, null);
        llBody.addView(rlProductDetailActivity);
        initilizeView();
        prefe=new Preference(UserList.this);
        if (NetworkUtility.isNetworkConnectionAvailable(UserList.this)) {
            GetUserList();
        } else {
            Snackbar snackbar = Snackbar.make(rlProductDetailActivity, getString(R.string.no_internet), Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

   private void GetUserList() {
        // final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);
        final AlertDialog dialog = new SpotsDialog(UserList.this);
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

        api.getUserList(new Callback<UserListResponse>() {
            @Override
            public void success(UserListResponse userListResponse, Response response) {
                dialog.dismiss();
                userListResponseFinal=userListResponse;
                if (response.getStatus() == 200) {
                    if(userListResponse.getCode()==419){
                        Snackbar snackbar = Snackbar.make(rlProductDetailActivity, AppConstants.tokenExpMsg, Snackbar.LENGTH_LONG);
                        snackbar.show();
                        Intent i=new Intent(UserList.this, com.limitless.haulified.Haulifier.Activity.SignIn.class);
                        startActivity(i);
                        finish();
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                    }else {
                        if (userListResponse.getData() != null) {
                            adapter1 = new UserListAdapter(userListResponse, UserList.this);
                            recyclerView.setAdapter(adapter1);
                            noData.setVisibility(View.GONE);
                        }
                        else {
                            adapter1.refresh(new UserListResponse());
                            noData.setVisibility(View.VISIBLE);
                        }
                    }
                }
                else{
                    Snackbar snackbar = Snackbar.make(rlProductDetailActivity, "Server Error", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }}
            @Override
            public void failure(RetrofitError error) {

                dialog.dismiss();
                Snackbar snackbar = Snackbar.make(rlProductDetailActivity, "Server Error", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
    }
    private void DeleteUserList(int pos) {
        final AlertDialog dialog = new SpotsDialog(UserList.this);
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
        try {
            _obj.put("userId", userListResponseFinal.getData().get(pos).getUserId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Test request = new Test(_obj.toString());
        api.deletUserList(request,new Callback<SignUpResponse>() {
            @Override
            public void success(SignUpResponse userListResponse, Response response) {
                dialog.dismiss();
                if (response.getStatus() == 200) {
                    if(userListResponse.getCode()==419){
                        Snackbar snackbar = Snackbar.make(rlProductDetailActivity, AppConstants.tokenExpMsg, Snackbar.LENGTH_LONG);
                        snackbar.show();
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                        Intent i=new Intent(UserList.this, com.limitless.haulified.Haulifier.Activity.SignIn.class);
                        startActivity(i);
                        finish();
                    }else {
                        if (userListResponse != null) {
                            GetUserList();
                        }
                        else {
                            adapter1.refresh(new UserListResponse());
                            noData.setVisibility(View.VISIBLE);
                        }
                    }
                }
                else{
                    Snackbar snackbar = Snackbar.make(rlProductDetailActivity, "Server Error", Snackbar.LENGTH_LONG);
                    snackbar.show();
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
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundTintList(ColorStateList.valueOf(Color
                .parseColor("#4e47a7")));
        fab.setOnClickListener(this);
        recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        initSwipe();
        noData=(TextView)findViewById(R.id.noData);
        recyclerView.setAdapter(adapter1 = new UserListAdapter(new UserListResponse(), UserList.this));

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.fab){
            Intent i=new Intent(this,UserDetails.class);
            i.putExtra("mode","Adduser");
            startActivity(i);
        }

    }

    private void initSwipe(){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
               final int position = viewHolder.getAdapterPosition();

                if (direction == ItemTouchHelper.LEFT){
                    dialogBox(position);
                }
            }
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(0, ItemTouchHelper.LEFT);
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                Bitmap icon;
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if(dX > 0){} else {
                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.del);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setMenuTitle("Users List");
        hideLoader();
        if (NetworkUtility.isNetworkConnectionAvailable(UserList.this)) {
            GetUserList();
        } else {
            Snackbar snackbar = Snackbar.make(rlProductDetailActivity, getString(R.string.no_internet), Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }
    public void dialogBox(final int pos) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setMessage("Do you want to delete this user?");
        alertDialogBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        DeleteUserList(pos);
                    }
                });

        alertDialogBuilder.setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        alertDialog1.dismiss();
                        adapter1.notifyDataSetChanged();
                    }
                });
        alertDialog1 = alertDialogBuilder.create();
        alertDialog1.show();
    }
}
