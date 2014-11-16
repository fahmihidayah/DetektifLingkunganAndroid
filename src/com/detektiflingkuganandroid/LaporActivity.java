package com.detektiflingkuganandroid;

import com.engine.LaporEngine;
import com.framework.common_utilities.ViewSetterUtilities;
import com.models.Constantstas;
import com.models.DataSingleton;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class LaporActivity extends FragmentActivity implements Constantstas{

	
	private void initialComponent(){
		View customActionBar = getLayoutInflater().inflate(
				R.layout.custom_laporan_action_bar, null);
		 getActionBar().setDisplayShowHomeEnabled(false);
		 getActionBar().setDisplayShowTitleEnabled(false);
		 getActionBar().setDisplayShowCustomEnabled(true);
		 getActionBar().setCustomView(customActionBar);
		ViewSetterUtilities.getImageView(customActionBar, R.id.imageButtonBack)
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						 onBackPressed();
					}
				});
	}

	private void initialLocation(){
		GPSTracker gpsTracker = new GPSTracker(this);
		
		if (gpsTracker.canGetLocation())
		{
			String stringLatitude = String.valueOf(gpsTracker.latitude);
			
			
			String stringLongitude = String.valueOf(gpsTracker.longitude);
			
//			String country = gpsTracker.getCountryName(this);
//			String city = gpsTracker.getLocality(this);
//			String postalCode = gpsTracker.getPostalCode(this);
			String addressLine = gpsTracker.getAddressLine(this);
		}
		else
		{
			// can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
			gpsTracker.showSettingsAlert();
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lapor_fragment);
		initialComponent();
	}
	
}
