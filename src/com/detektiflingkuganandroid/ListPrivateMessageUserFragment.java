package com.detektiflingkuganandroid;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.engine.ImageViewHandler;
import com.framework.adapter.CustomAdapter;
import com.framework.common_utilities.ViewSetterUtilities;
import com.models.DataSingleton;
import com.models.PrivateMessage;
import com.orm.query.Select;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class ListPrivateMessageUserFragment extends Fragment implements Observer{
	View rootView;
	public ListView listViewUsersPrivateMessage;
	public CustomAdapter<PrivateMessage> customAdapter;
	List<PrivateMessage> listPrivateMessages;
	private void initialComponent(){
		
		View customActionBar = getActivity().getLayoutInflater().inflate(
				R.layout.custom_private_message_action_bar, null);
		getActivity().getActionBar().setDisplayShowHomeEnabled(false);
		getActivity().getActionBar().setDisplayShowTitleEnabled(false);
		getActivity().getActionBar().setDisplayShowCustomEnabled(true);
		getActivity().getActionBar().setCustomView(customActionBar);
		ViewSetterUtilities.getImageView(customActionBar, R.id.imageButtonBack)
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						getActivity().onBackPressed();
					}
				});
		ViewSetterUtilities.getImageView(customActionBar, R.id.imageButtonNewMessage)
		.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				((HomeActivity)getActivity()).setFragment(new ListFollowerFragment(), true);
			}
		});
		listViewUsersPrivateMessage = (ListView) rootView.findViewById(R.id.listViewUsersPrivateMessage);
		listPrivateMessages = DataSingleton.getInstance().getListPrivateMessages();
		customAdapter = new CustomAdapter<PrivateMessage>(getActivity(), R.layout.private_message_user_item_layout, listPrivateMessages) {
			ImageViewHandler  imageViewHandler ;
			@Override
			public void initialComponent() {
				imageViewHandler = new ImageViewHandler(getActivity());
				super.initialComponent();
			}
			@Override
			public void setViewItems(View view, PrivateMessage data) {
				ImageView  imageViewUserProfile = (ImageView) view.findViewById(R.id.imageViewUserProfile);
				try{
					imageViewHandler.getImageLoader().displayImage(data.getUser().getImageProfilePath().getUrlImange(), imageViewUserProfile, imageViewHandler.getDisplayImageOptionsProfile());	
				}
				catch(NullPointerException exception){
					
				}
				
				ViewSetterUtilities.setTextToView(view, R.id.textViewName, data.getUser().getName());
				if(data.getListChatMessage().size() > 0){
					ViewSetterUtilities.setTextToView(view, R.id.textViewLastChat, data.getListChatMessage().get(data.getListChatMessage().size() - 1).getMessageData());	
				}
				if(data.getUnreadMessage() > 0){
					ViewSetterUtilities.setTextToView(view, R.id.textViewUnreadMessage, data.getUnreadMessage() + " pesan belum terbaca");
					view.setBackgroundResource(R.color.green_button_act);
				}
				else {
					ViewSetterUtilities.setTextToView(view, R.id.textViewUnreadMessage, data.getUnreadMessage() + " pesan belum terbaca").setVisibility(View.GONE);
					view.setBackgroundResource(R.color.white);
				}
			}
		};
		listViewUsersPrivateMessage.setAdapter(customAdapter);
		listViewUsersPrivateMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				PrivateMessage privateMessage = DataSingleton.getInstance().getListPrivateMessages().get(arg2); 
				((HomeActivity)getActivity()).setFragment(new PrivateMessageUserFragment(privateMessage), true);
				
			}
		});
		DataSingleton.getInstance().addObserver(this);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.list_private_message_fragment, null);
		initialComponent();
		return rootView;
	}

	@Override
	public void onDestroy() {
		DataSingleton.getInstance().deleteObserver(this);
		super.onDestroy();
	}
	
	@Override
	public void update(Observable observable, Object data) {
		customAdapter.notifyDataSetChanged();
		Toast.makeText(getActivity(), "new message", Toast.LENGTH_LONG).show();
	}

}
