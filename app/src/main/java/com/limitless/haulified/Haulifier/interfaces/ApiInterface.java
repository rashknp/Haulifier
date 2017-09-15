package com.limitless.haulified.Haulifier.interfaces;



import com.limitless.haulified.Haulifier.model.AddressResponseModel;
import com.limitless.haulified.Haulifier.model.MyDeliveryResponse;
import com.limitless.haulified.Haulifier.model.OfferListResponse;
import com.limitless.haulified.Haulifier.model.SettingsResponse;
import com.limitless.haulified.Haulifier.model.SignInModel;
import com.limitless.haulified.Haulifier.model.SignUpResponse;
import com.limitless.haulified.Haulifier.model.TruckerListResponse;
import com.limitless.haulified.Haulifier.model.UserListResponse;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Query;
import retrofit.mime.TypedString;

/**
 * Created by apta on 6/19/2017.
 */

public interface ApiInterface {



    @GET("/truckerList")
    public void getTruckerList(Callback<TruckerListResponse> response);

    @GET("/truckerDeliveries")
    public void getDeliveriesList(@Query("accountId") int accountId,@Query("userId") int userId,Callback<MyDeliveryResponse> response);

    @GET("/bookingListWithQuotes")
    public void getBookingsList(@Query("accountId") int accountId,@Query("userId") int userId,Callback<OfferListResponse> response);


    @POST("/adminLogin")
    public void signIn(@Body TypedString task, Callback<SignInModel> response);

    @GET("/settings")
    public void getsettings(Callback<SettingsResponse> response);


    @POST("/settingsUpdate")
    public void settings(@Body TypedString task, Callback<SignInModel> response);

    @GET("/listHaulifiedUser")
    public void getUserList(Callback<UserListResponse> response);

    @POST("/haulifiedAddUser")
    public void addUser(@Body TypedString task, Callback<UserListResponse> response);

    @PUT("/users")
    public void editUser(@Body TypedString task, Callback<UserListResponse> response);

    @POST("/singleCommissionCalculator")
    public void commissioncalculator(@Body TypedString task,Callback<SignInModel> response);

    @POST("/multipleCommissionCalculator")
    public void commissioncalculatorpublishAll(@Body TypedString task,Callback<SignInModel> response);


    //
    @POST("/remove_users")
    public void deletUserList(@Body TypedString task, Callback<SignUpResponse> response);

    @GET("/fillterBookingList")
    public void getOfferList(Callback<OfferListResponse> response);

    @PUT("/updateStatus")
 public void truckerStatus(@Body TypedString task, Callback<MyDeliveryResponse> response);

    @POST("/forget_password_email")
    public void signForgetEmail(@Body TypedString task, Callback<SignInModel> response);

    @POST("/forget_password_reset")
    public void signForget(@Body TypedString task, Callback<SignInModel> response);


}
