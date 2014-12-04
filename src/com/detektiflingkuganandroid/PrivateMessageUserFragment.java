package com.detektiflingkuganandroid;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.framework.adapter.CustomAdapter;
import com.framework.common_utilities.ViewSetterUtilities;
import com.framework.rest_clients.MyRestClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.models.ChatMessage;
import com.models.Constantstas;
import com.models.DataSingleton;
import com.models.PrivateMessage;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class PrivateMessageUserFragment extends Fragment implements Constantstas{
	View rootView;
	@InjectView(R.id.listViewChatMessage) ListView listViewChatMessage;
	public CustomAdapter<ChatMessage> customAdapter;
	@InjectView(R.id.editTextMessage) EditText editTextMessage;
	@InjectView(R.id.buttonSend) Button buttonSend;
	
	@OnClick(R.id.buttonSend)
	public void onClick(View v) {
		RequestParams params = new RequestParams();
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		params.put("messageData", editTextMessage.getText().toString());
		params.put("userReceiverId", privateMessage.getUser().getIdUser() + "");
		params.put("userSenderId", DataSingleton.getInstance().getUser().getIdUser() + "");
		MyRestClient.post(API_SEND_MESSAGE, params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject response) {
				ChatMessage chatMessage = new ChatMessage(); //new Gson().fromJson(response.toString(), ChatMessage.class);
				chatMessage.setUserReceiver(privateMessage.getUser());
				chatMessage.setUserSender(DataSingleton.getInstance().getUser());
				chatMessage.setStatus(STATUS_READ);
				chatMessage.setIsSendMessage(true);
				chatMessage.setMessageData(editTextMessage.getText().toString());
				privateMessage.getListChatMessage().add(chatMessage);
				customAdapter.notifyDataSetChanged();
				editTextMessage.setText("");
					privateMessage.isOld = false;
					DataSingleton.getInstance().saveToFile(getActivity());
					
			}
		});
	}
	
	public boolean isNew = false;
	
	private PrivateMessage privateMessage;
	
	
	public PrivateMessageUserFragment(PrivateMessage privateMessage) {
		super();
		this.privateMessage = privateMessage;
	}

	private void initialComponent(){
		readAllMessage();
		
		
		List<PrivateMessage> listPrivateMessages = DataSingleton.getInstance().getListPrivateMessages();
		int index = listPrivateMessages.indexOf(privateMessage);
		if(index != -1){
			privateMessage = listPrivateMessages.get(index);
		}
		customAdapter = new CustomAdapter<ChatMessage>(getActivity(), R.layout.private_message_item_layout,privateMessage.getListChatMessage()) {
			
			@Override
			public void setViewItems(View view, ChatMessage data) {
				TextView textViewChatMessage = ViewSetterUtilities.setTextToView(view, R.id.textViewMessage, data.getMessageData());
				if(!data.getIsSendMessage()){
					textViewChatMessage.setBackgroundResource(R.drawable.ic_chat_red);
					RelativeLayout.LayoutParams params = 
						    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
						        RelativeLayout.LayoutParams.WRAP_CONTENT);
						params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
						textViewChatMessage.setLayoutParams(params);
				}
			}
		};
		listViewChatMessage.setAdapter(customAdapter);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.private_message_fragment, null);
		ButterKnife.inject(this, rootView);
		initialComponent();
		return rootView;
	}

	public void readAllMessage(){
		
		
			ArrayList<ChatMessage> listChatMessage = privateMessage.getListChatMessage();
			for (ChatMessage chatMessage : listChatMessage) {
				chatMessage.setStatus(STATUS_READ);
			}
			privateMessage.setUnreadMessage(0);
			if(!privateMessage.isOld){
			DataSingleton.getInstance().saveToFile(getActivity());	
		}
		
	}

}
