package com.framework.gcm;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class GcmIntentService extends Service{

	// need initializations
	
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// ini seharusnya dinamis
//		this.gcmIntentServiceBeans = new GcmIntentServiceBeans(this);
//		
//		String messsage = intent.getStringExtra("message");
//		String nama = intent.getStringExtra("NAMA");
//		
//		gcmIntentServiceBeans.setMessageResponse(messsage);
//		gcmIntentServiceBeans.getMessageToServer();
//		intent.setAction(BROADCAST_CHAT);
		Intent newIntent = new Intent("BROADCAST_RECEIVER_MESSAGE_DETEKTIV_LINGKUNGAN");
		String message = intent.getStringExtra("message");
		newIntent.putExtra("message", message);
		sendBroadcast(newIntent);
		return START_NOT_STICKY;
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
