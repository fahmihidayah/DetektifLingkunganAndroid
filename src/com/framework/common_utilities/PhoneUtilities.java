package com.framework.common_utilities;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;

public class PhoneUtilities {

	public static void simpleSendMessage(String message, String number){
		SmsManager smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage(number, null, message, null, null);
	}
	
	public static void sendMessage(String message, String number){
		
	}
	
	public static void call(Context context, String number){
		Intent callIntent = new Intent(Intent.ACTION_CALL);
		callIntent.setData(Uri.parse("tel:" + number));
		context.startActivity(callIntent);
	}

}
