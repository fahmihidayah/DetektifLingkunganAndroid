package com.engine;

import org.json.JSONObject;

import android.app.DownloadManager.Request;
import android.view.View;
import android.widget.Toast;

import com.detektiflingkuganandroid.DetailUserProfileActivity;
import com.framework.rest_clients.MyRestClient;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.models.Constantstas;
import com.models.DataSingleton;
import com.models.User;
import com.response.UserResponse;

public class DetailUserProfileEngine implements Constantstas{
	private DetailUserProfileActivity detailUserProfileActivity;
	private User user;
	private boolean ownProfile;
	private String apiFollow;
	public DetailUserProfileEngine(
			DetailUserProfileActivity detailUserProfileActivity) {
		super();
		this.detailUserProfileActivity = detailUserProfileActivity;
		ownProfile = detailUserProfileActivity.getIntent().getBooleanExtra("own_profile", false);
		user = (User) detailUserProfileActivity.getIntent().getSerializableExtra("user");
		if(ownProfile){
			detailUserProfileActivity.buttonFollow.setVisibility(View.GONE);
		}else {
			detailUserProfileActivity.imageButtonEditStatus.setVisibility(View.GONE);
		}
		detailUserProfileActivity.textViewName.setText(user.getName());
		detailUserProfileActivity.textViewStatus.setText(user.getStatus());
		detailUserProfileActivity.textViewFollowerFollowing.setText("Follower : " + user.getJumlahFollowerUser() + " Following : " + user.getJumlahFollowingUser());
		getUserDetail(user.getId() + "");
	}
	
	public void getUserDetail(String idUser){
		
		RequestParams params = new RequestParams();
		if(idUser != null){
			params.put("authKey", DataSingleton.getInstance().getAuthKey());
			params.put("idUser",idUser);
			params.put("idUserFollower", DataSingleton.getInstance().getUser().getId() + "");
			MyRestClient.post(API_USER_DETAIL, params, new JsonHttpResponseHandler(){
				@Override
				public void onSuccess(JSONObject response) {
					UserResponse userResponse = new Gson().fromJson(response.toString(), UserResponse.class);
					user = userResponse.getData();
					detailUserProfileActivity.textViewName.setText(user.getName());
					detailUserProfileActivity.textViewStatus.setText(user.getStatus());
					detailUserProfileActivity.textViewFollowerFollowing.setText("Follower : " + user.getJumlahFollowerUser() + " Following : " + user.getJumlahFollowingUser());
					if(user.getIsFollowing()){
						detailUserProfileActivity.buttonFollow.setText("Unfollow");	
					}
					else {
						detailUserProfileActivity.buttonFollow.setText("Follow");
					}
				}
			});
		}
	}
	
	public void follow(){
		apiFollow = API_FOLLOW;
		if(user.getIsFollowing()){
			apiFollow = API_UNFOLLOW;
		}
		
		
		RequestParams params = new RequestParams();
		params.put("authKLey", DataSingleton.getInstance().getAuthKey());
		params.put("idUser", DataSingleton.getInstance().getUser().getId() + "");
		params.put("idUserFollow", user.getId() + "");
		Toast.makeText(detailUserProfileActivity, params.toString() + " " + apiFollow, 1000).show();
		MyRestClient.post(apiFollow, params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject response) {
				if(apiFollow.equalsIgnoreCase(API_FOLLOW)){
					detailUserProfileActivity.buttonFollow.setText("Follow");
					return;
				}
				detailUserProfileActivity.buttonFollow.setText("Unfollow");
				Toast.makeText(detailUserProfileActivity, "Follow", 1000).show();
			}
		});
	}
	
	public void changeStatus(final String status){
		RequestParams params = new RequestParams();
		params.put("authKLey", DataSingleton.getInstance().getAuthKey());
		params.put("idUser", DataSingleton.getInstance().getUser().getId() + "");
		params.put("status", status);
		MyRestClient.post(API_UPDATE_STATUS, params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject response) {
				Toast.makeText(detailUserProfileActivity, "status updated", Toast.LENGTH_LONG).show();
				DataSingleton.getInstance().getUser().setStatus(status);
				DataSingleton.getInstance().saveToFile(detailUserProfileActivity);
			}
		});
	}
	
	public User getUser() {
		return user;
	}

}
