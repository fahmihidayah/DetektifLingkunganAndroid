package com.engine;

import org.apache.http.Header;
import org.json.JSONObject;

import android.content.Intent;
import android.widget.Toast;

import com.detektiflingkuganandroid.LoginActivity;
import com.detektiflingkuganandroid.MainActivity;
import com.framework.rest_clients.MyRestClient;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.models.Constantstas;
import com.models.DataSingleton;
import com.models.User;
import com.response.LoginResponse;
import com.response.StringResponse;

public class LoginEngine implements Constantstas{
	private LoginActivity loginActivity;

	public LoginEngine(LoginActivity loginActivity) {
		super();
		this.loginActivity = loginActivity;
	}

	public void login(String userName, String password) {
		RequestParams params = new RequestParams();
		params.put("userName", userName);
		params.put("password", password);
		Toast.makeText(loginActivity, "user login", Toast.LENGTH_LONG).show();
		MyRestClient.post(API_LOGIN, params, new JsonHttpResponseHandler(){
			
			@Override
			public void onSuccess(JSONObject response) {
				LoginResponse loginResponse = new Gson().fromJson(response.toString(), LoginResponse.class);
				DataSingleton.getInstance().setUser(loginResponse.getData().getUser());
				Toast.makeText(loginActivity, "user id " + DataSingleton.getInstance().getUser().getId(), Toast.LENGTH_LONG).show();
				DataSingleton.getInstance().setLogin(true);
				DataSingleton.getInstance().setAuthKey(loginResponse.getData().getAuthKey());
				DataSingleton.getInstance().saveToFile(loginActivity);
				loginActivity.startActivity(new Intent(loginActivity, MainActivity.class));
				loginActivity.finish();
			}
			
			@Override
			public void onFailure(Throwable e, JSONObject errorResponse) {
				//StringResponse stringResponse = new Gson().fromJson(errorResponse.toString(), StringResponse.class);
				Toast.makeText(loginActivity,"error", Toast.LENGTH_LONG).show();
			}
		});
	}
	
	

}
