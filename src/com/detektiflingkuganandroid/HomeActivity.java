package com.detektiflingkuganandroid;

import com.models.Constantstas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.widget.Toast;


public class HomeActivity extends FragmentActivity implements Constantstas{

	private Fragment fragment;
	
	private void initialComponent(){
		HomeFragment mainFragment = new HomeFragment();
		setFragment(mainFragment, false);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity_1);
		initialComponent();
	}
	
	public void setFragment(Fragment fragment, boolean isToBackStack){
		this.fragment = fragment;
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.frameLayoutMain, fragment);
		if(isToBackStack){
			transaction.addToBackStack(null);
		}

		transaction.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//	    fragment.onActivityResult(requestCode, resultCode, data);
//	}
}