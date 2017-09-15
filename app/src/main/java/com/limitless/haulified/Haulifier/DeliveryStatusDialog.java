package com.limitless.haulified.Haulifier;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.limitless.haulified.Haulifier.Activity.BaseActivity;
import com.limitless.haulified.Haulifier.Activity.TruckerList;
import com.limitless.haulified.Haulifier.common.AppConstants;
import com.limitless.haulified.Haulifier.common.CustomDialog;
import com.limitless.haulified.Haulifier.common.Preference;
import com.limitless.haulified.Haulifier.common.Test;
import com.limitless.haulified.Haulifier.interfaces.ApiInterface;
import com.limitless.haulified.Haulifier.model.MyDeliveryResponse;
import com.limitless.haulified.Haulifier.utils.NetworkUtility;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;


public class DeliveryStatusDialog implements OnClickListener {
	private RelativeLayout mainLayout;
	private Context mContext;
	private CustomDialog customDialog;
	private ImageView ivClosePop;
	private ListView  listView;
	int order_id;
	AlertDialog dialog_spot;
	Preference prefe;

	public DeliveryStatusDialog(Context context,int order_id)
	{
		this.mContext = context;
		this.order_id=order_id;
	}

	public void showItemPopupNew()
	{

		mainLayout = (RelativeLayout) ((BaseActivity)mContext).inflater.inflate(com.limitless.haulified.Haulifier.R.layout.activity_status_dialog, null);

		initializeControls();

		customDialog = new CustomDialog(mContext, mainLayout,
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		customDialog.setCancelable(false);
		customDialog.setCanceledOnTouchOutside(true);

		customDialog.show();
	}

	private void initializeControls(){
		ivClosePop 	= (ImageView) mainLayout.findViewById(com.limitless.haulified.Haulifier.R.id.ivClosePop);
		listView    = (ListView)  mainLayout.findViewById(com.limitless.haulified.Haulifier.R.id.lvListView);

		ivClosePop.setOnClickListener(this);

		listView.setAdapter(new ArrayAdapter<String>(((TruckerList)mContext), com.limitless.haulified.Haulifier.R.layout.dialog_status, com.limitless.haulified.Haulifier.R.id.tvNavText, getStatus()));

	listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			if (NetworkUtility.isNetworkConnectionAvailable(mContext)) {

				 dialog_spot = new SpotsDialog(mContext);
				dialog_spot.show();
				deliveryStatus(order_id,getStatus().get(position));


			} else {
				Toast.makeText(mContext, com.limitless.haulified.Haulifier.R.string.no_internet,Toast.LENGTH_SHORT).show();
			}

		}
	});
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == com.limitless.haulified.Haulifier.R.id.ivClosePop)
		{
			if(customDialog != null)
				customDialog.dismiss();
		}
	}


	private List<String> getStatus()
	{
		List<String> arrayList=new ArrayList<String>();
		arrayList.add("ACTIVE");
		arrayList.add("INACTIVE");
		arrayList.add("SUSPEND");

		return arrayList;
	}

	private void deliveryStatus(int orderId, String status) {
		prefe =new Preference(mContext);
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
		JSONObject _obj = new JSONObject();
		//preparing request
		try {

			_obj.put("userId", orderId);
			_obj.put("status", status);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Test request = new Test(_obj.toString());
		api.truckerStatus(request, new Callback<MyDeliveryResponse>() {
			@Override
			public void success(MyDeliveryResponse myDeliveryResponse, retrofit.client.Response response) {

				if (myDeliveryResponse.getCode() == 201) {
					if (myDeliveryResponse != null) {
						customDialog.dismiss();
						dialog_spot.dismiss();
						Intent i=new Intent(mContext,TruckerList.class);
						mContext.startActivity(i);
						((BaseActivity)mContext).finish();
					}
				}
				else{

				}}

			@Override
			public void failure(RetrofitError error) {

			}
		});

	}
}