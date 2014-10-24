package com.engine;

import org.json.JSONArray;
import org.json.JSONObject;

import android.widget.Toast;

import com.detektiflingkuganandroid.DaftarActivity;
import com.framework.rest_clients.MyRestClient;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.models.Constantstas;
import com.response.StringResponse;

public class DaftarEngine implements Constantstas{
	private DaftarActivity daftarActivity;

	public DaftarEngine(DaftarActivity daftarActivity) {
		super();
		this.daftarActivity = daftarActivity;
	}
	
	public void daftar(String name,String userName, String password, String email){
		RequestParams params = new RequestParams();
		params.put("name", name);
		params.put("userName", userName);
		params.put("password", password);
		params.put("email", email);
		MyRestClient.post(API_DAFTAR, params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject response) {
				Toast.makeText(daftarActivity, "Success daftar", Toast.LENGTH_LONG).show();
			}
			@Override
			public void onFailure(Throwable e, JSONArray errorResponse) {
				StringResponse stringResponse = new Gson().fromJson(errorResponse.toString(), StringResponse.class);  
				Toast.makeText(daftarActivity, stringResponse.getData(), Toast.LENGTH_LONG).show();
			}
		});
		
	}

}
