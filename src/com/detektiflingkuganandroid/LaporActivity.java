package com.detektiflingkuganandroid;

import com.engine.LaporEngine;
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

	public ImageView imageViewImageLaporan;
	public EditText editTextDataLaporan, editTextLongitude, editTextLatitude;
	public Spinner spinnerKategori;
	private LaporEngine laporEngine;
	private void initialComponent(){
		imageViewImageLaporan = (ImageView) findViewById(R.id.imageViewImageLaporan);
		laporEngine = new LaporEngine(this);
		editTextDataLaporan = (EditText) findViewById(R.id.editTextDataLaporan);
		editTextLongitude = (EditText) findViewById(R.id.editTextLongitude);
		editTextLatitude = (EditText) findViewById(R.id.editTextLatitude);
		spinnerKategori = (Spinner) findViewById(R.id.spinnerKategori);
		initialLocation();
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
			editTextLatitude.setText(stringLatitude);
			editTextLongitude.setText(stringLongitude);
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
		setContentView(R.layout.lapor_activity);
		initialComponent();
	}
	
	public void onClickLapor(View view){
		laporEngine.uploadLaporan(editTextDataLaporan.getText().toString(), 
				Double.parseDouble(editTextLongitude.getText().toString()),
				Double.parseDouble(editTextLatitude.getText().toString()),
				spinnerKategori.getSelectedItem().toString());
	}

}
