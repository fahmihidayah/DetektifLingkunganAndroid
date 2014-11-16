package com.detektiflingkuganandroid;

import com.framework.common_utilities.ViewSetterUtilities;
import com.models.DataSingleton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

@SuppressLint("ResourceAsColor")
public class HomeFragment extends Fragment {

	private Button buttonLaporan, buttonMap, buttonConversation;
	private View rootView;

	private void initialComponent() {
		buttonLaporan = (Button) rootView.findViewById(R.id.buttonHome);
		buttonMap = (Button) rootView.findViewById(R.id.buttonMap);
		buttonConversation = (Button) rootView
				.findViewById(R.id.buttonDiscover);

		View customActionBar = getActivity().getLayoutInflater().inflate(R.layout.custom_home_action_bar, null);
        getActivity().getActionBar().setDisplayShowHomeEnabled(false);
        getActivity().getActionBar().setDisplayShowTitleEnabled(false);
        getActivity().getActionBar().setDisplayShowCustomEnabled(true);
        getActivity().getActionBar().setCustomView(customActionBar);
        ViewSetterUtilities.getImageButton(customActionBar, R.id.ImageButtonProfileMenu)
        	.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Long id = DataSingleton.getInstance().getUser().getId() ;
					((HomeActivity)getActivity()).setFragment(new ProfileFragment(id), true);
				}
			});
		ViewSetterUtilities.getImageButton(customActionBar, R.id.imageButtonShot)
			.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					startActivity(new Intent(getActivity(), LaporActivity.class));
				}
			});
		buttonLaporan.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				MainFragment mainFragment = new MainFragment();
				changeFragment(mainFragment);
				buttonLaporan.setBackgroundResource(R.color.red_act);
				buttonMap.setBackgroundResource(R.color.dark_menu);
				buttonConversation.setBackgroundResource(R.color.dark_menu);
			}
		});

		buttonMap.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				MapFragment mapFragment = new MapFragment();
				changeFragment(mapFragment);
				buttonLaporan.setBackgroundResource(R.color.dark_menu);
				buttonMap.setBackgroundResource(R.color.red_act);
				buttonConversation.setBackgroundResource(R.color.dark_menu);
			}
		});

		buttonConversation.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ConversationFragment conversationFragment = new ConversationFragment();
				changeFragment(conversationFragment);
				buttonLaporan.setBackgroundResource(R.color.dark_menu);
				buttonMap.setBackgroundResource(R.color.dark_menu);
				buttonConversation.setBackgroundResource(R.color.red_act);
			}
		});
		
		MainFragment mainFragment = new MainFragment();
		changeFragment(mainFragment);
		buttonLaporan.setBackgroundResource(R.color.red_act);
		buttonMap.setBackgroundResource(R.color.dark_menu);
		buttonConversation.setBackgroundResource(R.color.dark_menu);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.home_fragment, null);
		initialComponent();
		return rootView;
	}
	
	public void changeFragment(Fragment fragment){
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.frameLayoutHome, fragment);
//		if(isToBackStack){
//			transaction.addToBackStack(null);
//		}
		transaction.commit();
	}

}
