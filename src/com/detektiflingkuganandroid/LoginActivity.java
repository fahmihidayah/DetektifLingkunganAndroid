package com.detektiflingkuganandroid;

import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.engine.ProgressDialogFactory;
import com.framework.gcm.GcmDataHandler;
import com.framework.gcm.OnFinishListenner;
import com.framework.rest_clients.MyRestClient;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.models.Constantstas;
import com.models.DataSingleton;
import com.response.LoginResponse;

import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends FragmentActivity implements Constantstas, OnFinishListenner{

	@InjectView(R.id.editTextUserName) EditText editTextUserName;
	@InjectView(R.id.editTextPassword) EditText editTextPassword;
	GcmDataHandler gcmDataHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		ButterKnife.inject(this);
		gcmDataHandler = new GcmDataHandler(this);
		gcmDataHandler.setSenderId(SENDER_ID);
		gcmDataHandler.setOnFinishListenner(this);
	}

	@OnClick(R.id.buttonLogin)
	public void onClickLogin(View view){
		RequestParams params = new RequestParams();
		params.put("userName", editTextUserName.getText().toString());
		params.put("password", editTextPassword.getText().toString());
		
		final ProgressDialogFactory dialogFactory = new ProgressDialogFactory(this);
		
		MyRestClient.post(API_LOGIN, params, new JsonHttpResponseHandler(){
			
			@Override
			public void onProgress(int bytesWritten, int totalSize) {
				dialogFactory.show("Login...", false);
			}
			
			@Override
			public void onSuccess(JSONObject response) {
				dialogFactory.dismiss();
				LoginResponse loginResponse = new Gson().fromJson(response.toString(), LoginResponse.class);
				DataSingleton.getInstance().setUser(loginResponse.getData().getUser());
				Toast.makeText(LoginActivity.this, "user id " + DataSingleton.getInstance().getUser().getIdUser(), Toast.LENGTH_LONG).show();
				DataSingleton.getInstance().setLogin(true);
				DataSingleton.getInstance().setAuthKey(loginResponse.getData().getAuthKey());
				DataSingleton.getInstance().saveToFile(LoginActivity.this);
				gcmDataHandler.registerDevice();
				
			}
			
			@Override
			public void onFailure(Throwable e, JSONObject errorResponse) {
				Toast.makeText(LoginActivity.this,"error", Toast.LENGTH_LONG).show();
				dialogFactory.dismiss();
			}
		});
	}
	
	@OnClick(R.id.textViewDaftar)
	public void onClickDaftar(View view){
		startActivity(new Intent(this, DaftarActivity.class));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_screen, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.setting_server:
			SettingServerAddressDialog addressDialog = new SettingServerAddressDialog();
			addressDialog.show(getSupportFragmentManager(), "setting_server");
			break;

		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

	@Override
	public void finishRegister(String registerID) {
		RequestParams params = new RequestParams();
		params.put("userId", DataSingleton.getInstance().getUser().getIdUser() + "");
		params.put("gcmId", registerID);
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		final ProgressDialogFactory dialogFactory = new ProgressDialogFactory(this);
		
		MyRestClient.post(API_UPDATE_GCM_ID, params, new JsonHttpResponseHandler(){
			
			@Override
			public void onProgress(int bytesWritten, int totalSize) {
				dialogFactory.show("registering gcm id...", false);
			}
			
			@Override
			public void onSuccess(JSONObject response) {
				dialogFactory.dismiss();
				Toast.makeText(LoginActivity.this, "Login", Toast.LENGTH_LONG).show();
				LoginActivity.this.startActivity(new Intent(LoginActivity.this, HomeActivity.class));
				LoginActivity.this.finish();
			}
			
			@Override
			public void onFailure(Throwable e, JSONObject errorResponse) {
				dialogFactory.dismiss();
			}
		});
	}

}
