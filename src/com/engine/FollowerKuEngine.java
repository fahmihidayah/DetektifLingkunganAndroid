package com.engine;

import java.util.ArrayList;

import org.json.JSONObject;

import android.widget.Toast;

import com.detektiflingkuganandroid.FollowerKuFragment;
import com.framework.rest_clients.MyRestClient;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.models.Constantstas;
import com.models.DataSingleton;
import com.models.User;
import com.models.UserHelper;
import com.response.ListUserResponse;

public class FollowerKuEngine implements Constantstas{
	private FollowerKuFragment followerKuFragment;
	private ArrayList<UserHelper> listUser = new ArrayList<UserHelper>();
	
	public FollowerKuEngine(FollowerKuFragment followerKuFragment) {
		super();
		this.followerKuFragment = followerKuFragment;
	}
	
	
	public void requestListFollower(){
		RequestParams params = new RequestParams();
		params.put("userId", DataSingleton.getInstance().getUser().getIdUser() + "");
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		MyRestClient.post(API_GET_FOLLOWER, params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject response) {
				Toast.makeText(followerKuFragment.getActivity(), "data loaded", Toast.LENGTH_LONG).show();
				ListUserResponse listUserResponse = new Gson().fromJson(response.toString(),ListUserResponse.class);
				listUser.clear();
				listUser.addAll(listUserResponse.getData());
				followerKuFragment.customAdapter.notifyDataSetChanged();
			}
		});
	}
	
	public ArrayList<UserHelper> getListUser() {
		return listUser;
	}

}
