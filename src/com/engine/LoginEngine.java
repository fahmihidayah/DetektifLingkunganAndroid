package com.engine;

import org.apache.http.Header;
import org.json.JSONObject;

import android.widget.Toast;

import com.detektiflingkuganandroid.LoginActivity;
import com.framework.rest_clients.MyRestClient;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.models.Constantstas;
import com.models.User;
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
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				User user = new Gson().fromJson(response.toString(), User.class);
				Toast.makeText(loginActivity, "user login", Toast.LENGTH_LONG).show();
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, JSONObject errorResponse) {
				StringResponse stringResponse = new Gson().fromJson(errorResponse.toString(), StringResponse.class);
				Toast.makeText(loginActivity, stringResponse.getData(), Toast.LENGTH_LONG).show();
			}
		});
	}

}
