package com.framework.gcm;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

public class GcmDataHandler {
	private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
	private final static String APP_SHARED_PREFERENCES = "GIZI_SHARED";
	private final static int MAX_REQUEST = 5;
	private int currentRequest = 0;
	private final static String GCM_ID_SHARED_PREFERENCES = "GCM_ID";
	static final String TAG = "GCMHandler";
	
	private String senderId;
	private GoogleCloudMessaging gcm;
	private Activity activity;
    private String gcmRegId;
    private OnFinishListenner onFinishListenner;
    
	
	public GcmDataHandler(Activity activity) {
		super();
		this.activity = activity;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getGcmRegId() {
		return gcmRegId;
	}

	public void setGcmRegId(String gcmRegId) {
		this.gcmRegId = gcmRegId;
	}

	public OnFinishListenner getOnFinishListenner() {
		return onFinishListenner;
	}

	public void setOnFinishListenner(OnFinishListenner onFinishListenner) {
		this.onFinishListenner = onFinishListenner;
	}
	
	class RegisterBackground extends AsyncTask<String,String,String>{

		@Override
		protected String doInBackground(String... arg0) {
			try {
                if (gcm == null) {
                    gcm = GoogleCloudMessaging.getInstance(activity);
                }
                gcmRegId = gcm.register(senderId);
                
            } catch (IOException ex) {
            	
            } 
			return gcmRegId;
        }
		@Override
		protected void onPostExecute(String result) {
//			Toast.makeText(activity, "Reg id " + gcmRegId, Toast.LENGTH_LONG).show();
			if(gcmRegId.equalsIgnoreCase("")){
				if(currentRequest < MAX_REQUEST){
					Toast.makeText(activity, "Try to get gcm id again", Toast.LENGTH_LONG).show();
					gcm = GoogleCloudMessaging.getInstance(activity);
					new RegisterBackground().execute();	
				}
				else {
					Toast.makeText(activity, "Please try again", Toast.LENGTH_LONG).show();
				}
			}
			else {
				onFinishListenner.finishRegister(gcmRegId);
				SharedPreferences prefs = activity.getSharedPreferences(APP_SHARED_PREFERENCES, Context.MODE_PRIVATE);
				prefs.edit().putString(GCM_ID_SHARED_PREFERENCES, gcmRegId);
			}
		}
	}
	
	public void registerDevice(){
		if(checkPlayServices()){
			SharedPreferences prefs = activity.getSharedPreferences(APP_SHARED_PREFERENCES, Context.MODE_PRIVATE);
			gcmRegId = prefs.getString(GCM_ID_SHARED_PREFERENCES, "");
			if(gcmRegId.equalsIgnoreCase("")){
				gcm = GoogleCloudMessaging.getInstance(activity);
				new RegisterBackground().execute();	
			}
			else {
				if(gcmRegId.equalsIgnoreCase("")){
					if(currentRequest < MAX_REQUEST){
						Toast.makeText(activity, "Try to get gcm id again", Toast.LENGTH_LONG).show();
						gcm = GoogleCloudMessaging.getInstance(activity);
						new RegisterBackground().execute();	
					}
					else {
						Toast.makeText(activity, "Please try again", Toast.LENGTH_LONG).show();
					}
				}
				else {
					onFinishListenner.finishRegister(gcmRegId);	
				}
				
			}
			
		}
	}
	
	private boolean checkPlayServices() {
	    int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
	    if (resultCode != ConnectionResult.SUCCESS) {
	        if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
	            GooglePlayServicesUtil.getErrorDialog(resultCode, activity,
	                    PLAY_SERVICES_RESOLUTION_REQUEST).show();
	        } else {
	            Log.i(TAG, "This device is not supported.");
	        }
	        return false;
	    }
	    return true;
	}
	
}
