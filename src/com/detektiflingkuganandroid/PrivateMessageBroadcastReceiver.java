package com.detektiflingkuganandroid;

import com.google.gson.Gson;
import com.models.ChatMessage;
import com.models.DataSingleton;
import com.models.PrivateMessage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract.Contacts.Data;
import android.widget.Toast;

public class PrivateMessageBroadcastReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
//		Toast.makeText(context, "get the message is " + intent.getStringExtra("message"), Toast.LENGTH_LONG).show();
		handleMessage(intent.getStringExtra("message") , context);
	}
	
	private void handleMessage(String message, Context context){
		ChatMessage chatMessage = new Gson().fromJson(message, ChatMessage.class);
		chatMessage.setIsSendMessage(false);
		PrivateMessage privateMessage = new PrivateMessage();
		privateMessage.setUser(chatMessage.getUserSender());
		privateMessage.setIdPrivateMessage(chatMessage.getUserSender().getIdUser());
		privateMessage.getListChatMessage().add(chatMessage);
		int indexPm = DataSingleton.getInstance().getListPrivateMessages().indexOf(privateMessage);
		if(indexPm == -1){
			DataSingleton.getInstance().getListPrivateMessages().add(privateMessage);
			privateMessage.countUnreadMessage();
			
		}
		else {
			privateMessage  = DataSingleton.getInstance().getListPrivateMessages().get(indexPm);
			privateMessage.getListChatMessage().add(chatMessage);
			privateMessage.countUnreadMessage();
		}
		Toast.makeText(context, "index " + indexPm + " id " + privateMessage.getIdPrivateMessage(), 1000).show();
		privateMessage.isOld = true;
		DataSingleton.getInstance().saveToFile(context);
		DataSingleton.getInstance().notifyObserverDataChange();
	}

}
