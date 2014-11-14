package com.detektiflingkuganandroid;
import com.engine.FollowerKuEngine;
import com.framework.adapter.CustomAdapter;
import com.framework.common_utilities.ViewSetterUtilities;
import com.models.User;
import com.models.UserHelper;

import butterknife.ButterKnife;
import butterknife.InjectView;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class FollowerKuFragment extends Fragment{

	public ListView listViewFollowerKu;
	public CustomAdapter<UserHelper> customAdapter;
	public View rootView;
	private FollowerKuEngine followerKuEngine;
	private void initialComponent(){
		followerKuEngine = new FollowerKuEngine(this);
		listViewFollowerKu = (ListView) rootView.findViewById(R.id.listViewFollowerKu);
		customAdapter = new CustomAdapter<UserHelper>(getActivity(), R.layout.user_follower_layout, followerKuEngine.getListUser()) {
			
			@Override
			public void setViewItems(View view, UserHelper data) {
				ViewSetterUtilities.setTextToView(view, R.id.textViewNameUser, data.getName());
//				if(data.getImageProfilePath() == null){
//
//				}
			}
		};
		listViewFollowerKu.setAdapter(customAdapter);
		followerKuEngine.requestListFollower();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_follower_ku, null);
		initialComponent();
		return rootView;
	}


}
