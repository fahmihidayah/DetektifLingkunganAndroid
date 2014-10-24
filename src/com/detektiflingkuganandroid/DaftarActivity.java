package com.detektiflingkuganandroid;

import com.engine.DaftarEngine;
import com.models.Constantstas;
import com.models.DataSingleton;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class DaftarActivity extends Activity implements Constantstas{

	private DaftarEngine daftarEngine;
	public EditText editTextName, editTextUserName, editTextPassword, editTextEmail;
	
	private void initialComponent(){
		daftarEngine = new DaftarEngine(this);
		editTextEmail = (EditText) findViewById(R.id.editTextEmail);
		editTextUserName = (EditText) findViewById(R.id.editTextUserName);
		editTextPassword = (EditText) findViewById(R.id.editTextPassword);
		editTextName = (EditText) findViewById(R.id.editTextName);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.daftar_activity);
		initialComponent();
	}
	
	public void onClickDaftar(View view){
		daftarEngine.daftar(editTextName.getText().toString(),
				editTextUserName.getText().toString(), 
				editTextPassword.getText().toString(), 
				editTextEmail.getText().toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
