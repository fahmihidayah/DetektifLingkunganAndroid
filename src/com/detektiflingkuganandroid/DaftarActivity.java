package com.detektiflingkuganandroid;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.framework.rest_clients.MyRestClient;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.models.Constantstas;
import com.response.StringResponse;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DaftarActivity extends Activity implements Constantstas{

	@InjectView(R.id.editTextName) EditText editTextName;
	@InjectView(R.id.editTextUserName)EditText editTextUserName; 
	@InjectView(R.id.editTextPassword)EditText editTextPassword;
	@InjectView(R.id.editTextEmail)EditText editTextEmail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.daftar_activity);
		ButterKnife.inject(this);
	}
	
	public void onClickDaftar(View view){
		RequestParams params = new RequestParams();
		params.put("name", editTextName.getText().toString());
		params.put("userName", editTextUserName.getText().toString());
		params.put("password", editTextPassword.getText().toString());
		params.put("email", editTextEmail.getText().toString());
		MyRestClient.post(API_DAFTAR, params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject response) {
				Toast.makeText(DaftarActivity.this, "Success daftar", Toast.LENGTH_LONG).show();
			}
			@Override
			public void onFailure(Throwable e, JSONArray errorResponse) {
				StringResponse stringResponse = new Gson().fromJson(errorResponse.toString(), StringResponse.class);  
				Toast.makeText(DaftarActivity.this, stringResponse.getData(), Toast.LENGTH_LONG).show();
			}
		});
	}

}
