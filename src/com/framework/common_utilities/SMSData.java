package com.framework.common_utilities;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SMSData {
	private String message;
	private String number;

	public SMSData(Intent intent) {
		message = "";
		number = "";
		Bundle extras = intent.getExtras();
		if (extras != null) {
			Object[] smsextras = (Object[]) extras.get("pdus");

			for (int i = 0; i < smsextras.length; i++) {
				SmsMessage smsmsg = SmsMessage.createFromPdu((byte[]) smsextras[i]);
				message = message + smsmsg.getMessageBody().toString();
				number = smsmsg.getOriginatingAddress();
			}

		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
