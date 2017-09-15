package com.limitless.haulified.Haulifier.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.limitless.haulified.Haulifier.R;

public class Update extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		try{	
			requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	            WindowManager.LayoutParams.FLAG_FULLSCREEN);
							
		}
		catch(Exception er){
			Log.e("Update Activity:", er.getLocalizedMessage());
		}
		
		if(isScreenLarge()) {
	        // width > height, better to use Landscape
	        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	    } else {
	        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	    }
	
	setContentView(R.layout.update);
		
		Button download = (Button)findViewById(R.id.download);
		download.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					
				Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id=com.starkey"));
				startActivity(intent);
				finish();
				
				}
		});
		
		Button cancel = (Button)findViewById(R.id.cancel);
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
	}
	public boolean isScreenLarge() {
	    final int screenSize = getResources().getConfiguration().screenLayout
	            & Configuration.SCREENLAYOUT_SIZE_MASK;
	    return screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE
	            || screenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE;
	}
	}
