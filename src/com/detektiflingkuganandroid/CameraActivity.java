package com.detektiflingkuganandroid;

import com.models.Constantstas;
import com.models.DataSingleton;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class CameraActivity extends FragmentActivity implements Constantstas{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

}
