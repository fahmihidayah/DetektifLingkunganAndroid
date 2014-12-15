package com.detektiflingkuganandroid;

import com.google.android.gms.drive.query.internal.NotFilter;
import com.google.gson.Gson;
import com.models.ChatMessage;
import com.models.DataSingleton;
import com.models.Notif;
import com.models.PrivateMessage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
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
		if(chatMessage.getMessageData() != null){
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
			createNotif(chatMessage.getUserSender().name, chatMessage.getMessageData(), context);
			DataSingleton.getInstance().saveToFile(context);
			DataSingleton.getInstance().notifyObserverDataChange();
			return;
		}
//		Toast.makeText(context, message, 1000).show();
			Notif notif = new Gson().fromJson(message, Notif.class);
			createNotif("Detektiv Lingkungan", notif.getNotifInfo(), context);
		
	}
	
	@SuppressLint("NewApi")
	private void createNotif(String title, String content, Context context){
		Intent intent = new Intent(context, HomeActivity.class);
		PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);
		Notification.Builder builder = new Notification.Builder(context)
			.setSmallIcon(R.drawable.ic_logo_sp)
			.setContentTitle(title)
			.setContentText(content)
			.setContentIntent(pIntent)
			.setAutoCancel(true);
		
		
		NotificationManager notificationManager = 
				  (NotificationManager) context.getSystemService(	Activity.NOTIFICATION_SERVICE);

				notificationManager.notify(0, builder.build()); 
	}

}
