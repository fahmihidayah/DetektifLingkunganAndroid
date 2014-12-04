package com.detektiflingkuganandroid;

import com.models.Constantstas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import butterknife.ButterKnife;


public class HomeActivity extends FragmentActivity implements Constantstas{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity_1);
		ButterKnife.inject(this);
		View customActionBar = getLayoutInflater().inflate(R.layout.custom_list_user_action_bar, null);
        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setDisplayShowCustomEnabled(true);
        getActionBar().setCustomView(customActionBar);
        setFragment(new HomeFragment(), false);
	}
	public void setFragment(Fragment fragment, boolean isBackStack) {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		if(isBackStack){
			transaction.addToBackStack(null);
		}
		transaction.replace(R.id.frameLayoutMain, fragment);
		transaction.commit();
	}
	
	public class ViewHolder{

		public ViewHolder(View view) {
			ButterKnife.inject(this, view);
		}
		
		
	}
	
}
