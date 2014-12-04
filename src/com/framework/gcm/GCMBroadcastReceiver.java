package com.framework.gcm;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

public class GCMBroadcastReceiver extends WakefulBroadcastReceiver{
	
	@Override
	public void onReceive(Context context, Intent intent) {
        ComponentName comp = new ComponentName(context.getPackageName(),
                GcmIntentService.class.getName());
        
//        Intent intentBaru = new Intent(context, GcmIntentService.class);
        Log.d("dapat", "get pesan");
        
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
		
	}
}
