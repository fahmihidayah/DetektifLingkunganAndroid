package com.detektiflingkuganandroid;


import butterknife.ButterKnife;
import butterknife.OnClick;

import com.detektiflingkuganandroid.HomeFragment.ViewHolder;
import com.framework.common_utilities.ViewSetterUtilities;
import com.loopj.android.http.RequestParams;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class SearchFragment extends Fragment{
	
	View rootView;
	
	private void initialComponent(){
		View customActionBar = getActivity().getLayoutInflater().inflate(
				R.layout.custom_search_action_bar, null);
		getActivity().getActionBar().setDisplayShowHomeEnabled(false);
		getActivity().getActionBar().setDisplayShowTitleEnabled(false);
		getActivity().getActionBar().setDisplayShowCustomEnabled(true);
		getActivity().getActionBar().setCustomView(customActionBar);
		ViewHolder viewHolder = new ViewHolder(customActionBar);
		customActionBar.setTag(viewHolder);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.search_fragment, null);
		ButterKnife.inject(this, rootView);
		initialComponent();
		return rootView;
	}
	
	public class ViewHolder{
		
		@OnClick(R.id.imageButtonBack)
		public void onClickBack(View view){
			getActivity().onBackPressed();
		}
		
		@OnClick(R.id.imageButtonSearch)
		public void onClikcSaerch(View view){
			RequestParams params = new RequestParams();
			
		}
		
		
		public ViewHolder(View view){
			ButterKnife.inject(this, view);
		}
	}

}
