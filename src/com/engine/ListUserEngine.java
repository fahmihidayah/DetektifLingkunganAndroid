package com.engine;

import java.util.ArrayList;

import org.json.JSONObject;

import android.widget.Toast;

import com.detektiflingkuganandroid.ListUserFragment;
import com.framework.rest_clients.MyRestClient;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.models.Constantstas;
import com.models.DataSingleton;
import com.models.User;
import com.response.ListUserResponse;

public class ListUserEngine implements Constantstas{
	
	private ListUserFragment listUserFragment;
	private ArrayList<User> listUser = new ArrayList<User>();
	private User user;
	public ListUserEngine(ListUserFragment listUserFragment, User user) {
		super();
		this.listUserFragment = listUserFragment;
		this.user = user;
	}
	
	public void requestListUser(String mode){
		RequestParams params = new RequestParams();
		params.put("userId",user.getIdUser() + "");
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		params.put("mode", mode);
		Toast.makeText(listUserFragment.getActivity(), "mode " + mode, 1000).show();
		MyRestClient.post(API_GET_FOLLOWER, params,new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject response) {
				ListUserResponse listUserResponse = new Gson().fromJson(response.toString(), ListUserResponse.class);
				listUser.clear();
				listUser.addAll(listUserResponse.getData());
				listUserFragment.customAdapter.notifyDataSetChanged();
			}
			
			@Override
			public void onFailure(Throwable e, JSONObject errorResponse) {
				Toast.makeText(listUserFragment.getActivity(), "error", Toast.LENGTH_LONG).show();
			}
		});
	}
	
	public void showProfile(){
		
	}

	public ArrayList<User> getListUser() {
		return listUser;
	}
}
