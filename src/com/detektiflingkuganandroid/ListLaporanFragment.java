package com.detektiflingkuganandroid;
import com.engine.FollowerKuEngine;
import com.framework.adapter.CustomAdapter;
import com.framework.common_utilities.ViewSetterUtilities;
import com.models.User;
import com.models.UserHelper;

import butterknife.ButterKnife;
import butterknife.InjectView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class ListLaporanFragment extends Fragment{

	public View rootView;
	
	private void initialComponent(){
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_follower_ku, null);
		initialComponent();
		return rootView;
	}


}
