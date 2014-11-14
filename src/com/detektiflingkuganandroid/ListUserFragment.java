package com.detektiflingkuganandroid;


import com.engine.ListUserEngine;
import com.framework.adapter.CustomAdapter;
import com.framework.common_utilities.ViewSetterUtilities;
import com.models.Constantstas;
import com.models.User;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

@SuppressLint("ValidFragment")
public class ListUserFragment extends Fragment implements Constantstas{
	
	private ListUserEngine listUserEngine;
	
	public String mode;
	public View rootView;
	public CustomAdapter<User> customAdapter;
	public ListView listViewUser;
	
	public ListUserFragment(String mode) {
		this.mode = mode;
	}

	private void initialComponent(){
		listUserEngine = new ListUserEngine(this);
		View customActionBar = getActivity().getLayoutInflater().inflate(R.layout.custom_list_user_action_bar, null);
        getActivity().getActionBar().setDisplayShowHomeEnabled(false);
        getActivity().getActionBar().setDisplayShowTitleEnabled(false);
        getActivity().getActionBar().setDisplayShowCustomEnabled(true);
        getActivity().getActionBar().setCustomView(customActionBar);
        ViewSetterUtilities.setTextToView(customActionBar, R.id.textViewActionBarTitle, (mode.equalsIgnoreCase(MODE_FOLLOWER) ? "Follower": "Following" ));
        
		listViewUser = (ListView) rootView.findViewById(R.id.listViewUser);
		customAdapter = new CustomAdapter<User>(getActivity(),R.layout.user_item_layout, listUserEngine.getListUser()) {
			
			@Override
			public void setViewItems(View view, User data) {
				ViewSetterUtilities.setTextToView(view, R.id.textViewNameUser, data.getName());
			}
		};
		listViewUser.setAdapter(customAdapter);
		listViewUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// change fragment to detail user profile
			}
		});
		listUserEngine.requestListUser(mode);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.list_user_fragment, null);
		initialComponent();
		return rootView;
	}

}
