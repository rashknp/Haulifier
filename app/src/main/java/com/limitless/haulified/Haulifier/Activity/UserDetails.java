package com.limitless.haulified.Haulifier.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.limitless.haulified.Haulifier.R;
import com.limitless.haulified.Haulifier.common.AppConstants;
import com.limitless.haulified.Haulifier.common.Preference;
import com.limitless.haulified.Haulifier.common.Test;
import com.limitless.haulified.Haulifier.interfaces.ApiInterface;
import com.limitless.haulified.Haulifier.model.TruckListModel;
import com.limitless.haulified.Haulifier.model.TruckerListResponse;
import com.limitless.haulified.Haulifier.model.UserDetailsModel;
import com.limitless.haulified.Haulifier.model.UserListResponse;
import com.limitless.haulified.Haulifier.model.UserListResponse1;
import com.limitless.haulified.Haulifier.utils.NetworkUtility;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Rashami on 26-Jun-17.
 */

public class UserDetails extends BaseActivity implements View.OnClickListener {
    private Menu mMenu;
    private TextView cancel,adduser,save;
    private EditText user,et_email,et_pwd,et_confirm;
    private Spinner role;
    private LinearLayout rlProductDetailActivity;
    private String strErrorMessage = "",st_role="",mode="";
    int userId;
    int id_role;
    Preference prefe;
    LinearLayout pwd_lay;

    @Override
    public void initialize() {
        rlProductDetailActivity = (LinearLayout) inflater.inflate(R.layout.user_details, null);
        llBody.addView(rlProductDetailActivity);
        initialiseView();
        userId = getIntent().getIntExtra("id",0);
        mode = getIntent().getStringExtra("mode");
        if(mode.equalsIgnoreCase("Adduser")) {
            setMenuTitle("Add Users");
        }
        else if(mode.equalsIgnoreCase("Edit_user")){
            setMenuTitle("Edit User");
            user.setText(getIntent().getStringExtra("name"));
            et_email.setText(getIntent().getStringExtra("email"));
            pwd_lay.setVisibility(View.GONE);
        }

        prefe=new Preference(UserDetails.this);
         /* hide keyboard in startup */
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }


    private void initialiseView() {
        role=(Spinner)findViewById(R.id.role);
        save=(TextView)findViewById(R.id.save_user);
        cancel=(TextView)findViewById(R.id.cancel_user);
        user=(EditText)findViewById(R.id.user_name);
        et_email=(EditText)findViewById(R.id.email_id);
        et_pwd=(EditText)findViewById(R.id.pwd);
        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
        pwd_lay=(LinearLayout)findViewById(R.id.pwd_lay);
    }
    @Override
    protected void onResume() {
        super.onResume();
        hideLoader();
        if(mode.equalsIgnoreCase("Adduser")) {
            setMenuTitle("Add Users");
        }
        else if(mode.equalsIgnoreCase("Edit_user")){
            setMenuTitle("Edit User");
        }

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.save_user){

            try  {
                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            } catch (Exception e) {

            }
            if (NetworkUtility.isNetworkConnectionAvailable(UserDetails.this)) {
                if (validateFields()) {
if(mode.equalsIgnoreCase("Adduser")) {
    saveUser(user.getText().toString(), et_email.getText().toString(), et_pwd.getText().toString(), "sss", "aa", 2);

}
                else if(mode.equalsIgnoreCase("Edit_user")) {
    saveUser(user.getText().toString(), et_email.getText().toString(),"uu", "sss", "aa", 2);

}
                } else {
                    Snackbar snackbar = Snackbar.make(rlProductDetailActivity, strErrorMessage, Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            } else {
                Snackbar snackbar = Snackbar.make(rlProductDetailActivity,getString(R.string.no_internet), Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }



    }

    private void saveUser(final String name, final String email, String pwd, String c_pwd, String st_role, int id_role) {
        final ProgressDialog loading = ProgressDialog.show(this, "", "Please wait...", false, false);

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
        TruckerListResponse truckerListResponse =new TruckerListResponse();
        TruckListModel truckListModel=new TruckListModel();
        //Creating an object of our api interface
        ApiInterface api = adapter.create(ApiInterface.class);

        //preparing request

        //Defining the method
        if(mode.equalsIgnoreCase("Adduser")){
            final UserDetailsModel userDetailsModel=new UserDetailsModel();
               userDetailsModel.setFullName(name);
                userDetailsModel.setEmail(email);
                userDetailsModel.setPassword(pwd);
                userDetailsModel.setPhone("1234567899");


        /*for converting modl to json object*/
            Gson gson = new Gson();
            String jsonString= gson.toJson(userDetailsModel);
            Test request = new Test(jsonString);
            api.addUser(request, new Callback<UserListResponse>() {
                @Override
                public void success(UserListResponse userListResponse, Response response) {
                    if(response.getStatus()==200) {
                        loading.dismiss();
                        if(userListResponse.getCode()==419){
                            Snackbar snackbar = Snackbar.make(rlProductDetailActivity, AppConstants.tokenExpMsg, Snackbar.LENGTH_LONG);
                            snackbar.show();
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                            Intent i=new Intent(UserDetails.this, com.limitless.haulified.Haulifier.Activity.SignIn.class);
                            startActivity(i);
                            finish();
                        }else {
                            if(userListResponse.getCode()==200) {
                                Toast.makeText(UserDetails.this,userListResponse.getMsg(),Toast.LENGTH_SHORT).show();
                               /* Snackbar snackbar = Snackbar.make(rlProductDetailActivity, userListResponse.getMsg(), Snackbar.LENGTH_LONG);
                                snackbar.show();*/
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                                finish();
                                user.setText("");
                                et_email.setText("");
                                et_pwd.setText("");
                            }
                            else {
                                Snackbar snackbar = Snackbar.make(rlProductDetailActivity, userListResponse.getMsg(), Snackbar.LENGTH_LONG);
                                snackbar.show();
                            }
                        }
                    }

                }

                @Override
                public void failure(RetrofitError error) {
                    loading.dismiss();
                }
            });
        }
        else if(mode.equalsIgnoreCase("Edit_user")){

                UserListResponse1 userDetailsModel=new UserListResponse1();
                userDetailsModel.setFullName(name);
                userDetailsModel.setEmail(email);
                userDetailsModel.setUserId(userId);

        /*for converting modl to json object*/
            Gson gson = new Gson();
            gson.toJson(truckListModel);

            Test request = new Test(gson.toJson(userDetailsModel));

            api.editUser(request, new Callback<UserListResponse>() {
                @Override
                public void success(UserListResponse dashboardBean, Response response) {
                    loading.dismiss();
                    Snackbar snackbar = Snackbar.make(rlProductDetailActivity, "Update Successfully", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                    finish();
                    user.setText("");
                    et_email.setText("");
                    et_pwd.setText("");
                }

                @Override
                public void failure(RetrofitError error) {
                    loading.dismiss();
                }
            });
        }

    }

    private boolean validateFields() {
        if (user.getText().toString().equals("")) {
            strErrorMessage = "Please enter User Name";
            return false;
        }  else if (et_email.getText().toString().equals("")) {
            strErrorMessage = "Please enter Email id";
            return false;
        }
       else if(mode.equalsIgnoreCase("Adduser")) {
      if (et_pwd.getText().toString().equals("")) {
                strErrorMessage = "Please enter Password";
                return false;
            }
        }
        /*else if (et_confirm.getText().toString().equals("")) {
            strErrorMessage = "Please enter Confirm Password";
            return false;
        }*/
       /* else if (!((et_confirm.getText().toString()).equals(et_pwd.getText().toString()))) {
            strErrorMessage = "Please enter correct Confirm Password";
            return false;
        }*/
       /* else if (st_role.equalsIgnoreCase(AppConstants.Trucker().get(0))) {
            strErrorMessage = "Please select role";
            return false;
        }*/

        return true;
    }
}
