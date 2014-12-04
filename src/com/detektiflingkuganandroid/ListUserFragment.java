package com.detektiflingkuganandroid;


import com.engine.ImageViewHandler;
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
import android.widget.ImageView;
import android.widget.ListView;

@SuppressLint("ValidFragment")
public class ListUserFragment extends Fragment implements Constantstas{
	
	private ListUserEngine listUserEngine;
	
	public String mode;
	public View rootView;
	public CustomAdapter<User> customAdapter;
	public ListView listViewUser;
	public User user;
	public ListUserFragment(String mode,User user) {
		this.mode = mode;
		this.user =user;
	}

	private void initialComponent(){
		listUserEngine = new ListUserEngine(this, user);
		View customActionBar = getActivity().getLayoutInflater().inflate(R.layout.custom_list_user_action_bar, null);
        getActivity().getActionBar().setDisplayShowHomeEnabled(false);
        getActivity().getActionBar().setDisplayShowTitleEnabled(false);
        getActivity().getActionBar().setDisplayShowCustomEnabled(true);
        getActivity().getActionBar().setCustomView(customActionBar);
        ViewSetterUtilities.setTextToView(customActionBar, R.id.textViewActionBarTitle, (mode.equalsIgnoreCase(MODE_FOLLOWER) ? "Follower": "Following" ));
        
		listViewUser = (ListView) rootView.findViewById(R.id.listViewUser);
		customAdapter = new CustomAdapter<User>(getActivity(),R.layout.user_item_layout, listUserEngine.getListUser()) {
			ImageViewHandler imageViewHandler;
			@Override
			public void initialComponent() {
				imageViewHandler = new ImageViewHandler(getActivity());
				super.initialComponent();
			}
			@Override
			public void setViewItems(View view, User data) {
				ImageView imageView = (ImageView) view.findViewById(R.id.imageViewProfileUser);
				ViewSetterUtilities.setTextToView(view, R.id.textViewNameUser, data.getName());
				imageViewHandler.getImageLoader().displayImage(data.getImageProfilePath().getUrlImange(), imageView, imageViewHandler.getDisplayImageOptionsProfile());
			}
		};
		listViewUser.setAdapter(customAdapter);
		listViewUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				User user = listUserEngine.getListUser().get(arg2);
				((HomeActivity)getActivity()).setFragment(new ProfileFragment(user.getIdUser()), true);
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
