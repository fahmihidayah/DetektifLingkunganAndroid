package com.detektiflingkuganandroid;

import com.engine.LoginEngine;
import com.models.Constantstas;
import com.models.DataSingleton;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity implements Constantstas{

	private LoginEngine loginEngine ;
	private EditText editTextUserName;
	private EditText editTextPassword;
	
	private void initialComponent(){
		loginEngine = new LoginEngine(this);
		editTextPassword = (EditText) findViewById(R.id.editTextPassword);
		editTextUserName = (EditText) findViewById(R.id.editTextUserName);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		this.initialComponent();
	}

	public void onClickLogin(View view){
		loginEngine.login(editTextUserName.getText().toString(), 
				editTextPassword.getText().toString());
	}
	
	public void onClickDaftar(View view){
		
	}
	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}

}
