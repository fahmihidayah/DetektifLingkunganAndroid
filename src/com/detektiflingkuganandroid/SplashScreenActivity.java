package com.detektiflingkuganandroid;

import com.models.Constantstas;
import com.models.DataSingleton;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class SplashScreenActivity extends FragmentActivity implements Constantstas{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen_activity);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				DataSingleton.getInstance().loadFromFile(SplashScreenActivity.this);
				if(DataSingleton.getInstance().isLogin()){
					startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));					
				}
				else {
					startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
				} 
				finish();
			}
		}, DELAYED_SPLASH_SCREEN_TIME);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
