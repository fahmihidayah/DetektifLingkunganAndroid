package com.detektiflingkuganandroid;

import com.models.Constantstas;
import com.models.DataSingleton;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.ImageView;

public class UserProfilePictureActivity extends FragmentActivity implements Constantstas{

	private ImageView imageViewProfilePict;
	private boolean camera;
	
	private void initialComponent(){
		imageViewProfilePict = (ImageView) findViewById(R.id.imageViewProfilePict);
		
		camera = getIntent().getBooleanExtra("camera", false);
		if(camera){
			// start camera with code
		}
		else {
			
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_profile_picture_activity);
	
	}

}
