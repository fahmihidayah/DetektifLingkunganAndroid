package com.detektiflingkuganandroid;

import com.models.DataSingleton;

import android.app.Application;

public class DetektifLingkunganApp extends Application{
	@Override
	public void onLowMemory() {
		DataSingleton.getInstance().saveToFile(getApplicationContext());
		super.onLowMemory();
	}
	
	@Override
	public void onCreate() {
		DataSingleton.getInstance().loadFromFile(getApplicationContext());
		super.onCreate();
	}
	
	
}
