package com.detektiflingkuganandroid;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.framework.common_utilities.ViewSetterUtilities;
import com.models.DataSingleton;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

@SuppressLint("ResourceAsColor")
public class HomeFragment extends Fragment {

	@InjectView(R.id.buttonHome)
	Button buttonLaporan;
	@InjectView(R.id.buttonMap)
	Button buttonMap;
	@InjectView(R.id.buttonDiscover)
	Button buttonConversation;

	@OnClick(R.id.buttonHome)
	public void onClickHome(Button button) {
		MainFragment mainFragment = new MainFragment();
		changeFragment(mainFragment);
		buttonLaporan.setBackgroundResource(R.color.red_act);
		buttonMap.setBackgroundResource(R.color.dark_menu);
		buttonConversation.setBackgroundResource(R.color.dark_menu);
	}

	@OnClick(R.id.buttonMap)
	public void onClickMap(Button button) {
		MapFragment mapFragment = new MapFragment();
		changeFragment(mapFragment);
		buttonLaporan.setBackgroundResource(R.color.dark_menu);
		buttonMap.setBackgroundResource(R.color.red_act);
		buttonConversation.setBackgroundResource(R.color.dark_menu);
	}

	@OnClick(R.id.buttonDiscover)
	public void onClickDiscover(Button button) {
		DiscoverFragment conversationFragment = new DiscoverFragment();
		changeFragment(conversationFragment);
		buttonLaporan.setBackgroundResource(R.color.dark_menu);
		buttonMap.setBackgroundResource(R.color.dark_menu);
		buttonConversation.setBackgroundResource(R.color.red_act);
	}

	private View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.home_fragment, null);
		ButterKnife.inject(this, rootView);
		View customActionBar = getActivity().getLayoutInflater().inflate(
				R.layout.custom_home_action_bar, null);
		getActivity().getActionBar().setDisplayShowHomeEnabled(false);
		getActivity().getActionBar().setDisplayShowTitleEnabled(false);
		getActivity().getActionBar().setDisplayShowCustomEnabled(true);
		getActivity().getActionBar().setCustomView(customActionBar);
		ViewHolder viewHolder = new ViewHolder(customActionBar, getActivity());
		customActionBar.setTag(viewHolder);
		onClickHome(null);
		return rootView;
	}

	public void changeFragment(Fragment fragment) {
		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();
		transaction.replace(R.id.frameLayoutHome, fragment);
		transaction.commit();
	}

	public static class ViewHolder {
		Activity activity;

		@OnClick(R.id.ImageButtonProfileMenu)
		public void onClickImageButtonProfileMenu(ImageButton button) {
			Long id = DataSingleton.getInstance().getUser().getIdUser();
			((HomeActivity) activity)
					.setFragment(new ProfileFragment(id), true);
		}

		@OnClick(R.id.imageButtonShot)
		public void onClickImageButtonShot(ImageButton button) {
			activity.startActivity(new Intent(activity, LaporActivity.class));
		}

		@OnClick(R.id.imageButtonPrivateMessage)
		public void onClickImageButtonPrivateMessage(ImageButton button) {
			((HomeActivity) activity).setFragment(
					new ListPrivateMessageUserFragment(), true);
		}

		@OnClick(R.id.imageButtonNotif)
		public void onClickImageButtonNotif(ImageButton button) {
			((HomeActivity) activity).setFragment(
					new NotificationFragment(), true);
		}

		@OnClick(R.id.imageButtonSearch)
		public void onClickImageButtonSearch(ImageButton button) {
			((HomeActivity) activity).setFragment(
					new SearchFragment(), true);
		}

		public ViewHolder(View view, Activity activity) {
			ButterKnife.inject(this, view);
			this.activity = activity;
		}
	}

}
