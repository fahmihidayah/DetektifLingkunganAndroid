package com.engine;

import java.util.ArrayList;

import org.json.JSONObject;

import android.app.DownloadManager.Request;
import android.view.View;
import android.widget.Toast;

import com.detektiflingkuganandroid.DetailUserProfileActivity;
import com.detektiflingkuganandroid.ImageDownloader;
import com.detektiflingkuganandroid.ProfileFragment;
import com.detektiflingkuganandroid.R;
import com.framework.common_utilities.ViewSetterUtilities;
import com.framework.rest_clients.MyRestClient;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.models.Constantstas;
import com.models.DataSingleton;
import com.models.Laporan;
import com.models.User;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.response.ListLaporanResponse;
import com.response.UserResponse;

public class DetailUserProfileEngine implements Constantstas{
	private ProfileFragment detailUserProfileActivity;
	private ArrayList<Laporan> listLaporan = new ArrayList<Laporan>();
	private User user = new User();
	private boolean ownProfile;
	private String apiFollow;
	
	private ImageLoader imageLoader;
	private DisplayImageOptions displayImageOptions;
	
	public DetailUserProfileEngine(ProfileFragment detailUserProfileActivity, Long id) {
		super();
		this.detailUserProfileActivity = detailUserProfileActivity;
		imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(detailUserProfileActivity.getActivity().getBaseContext()));
		displayImageOptions = new DisplayImageOptions.Builder()
								.showImageOnLoading(R.drawable.ic_profile_menu)
		.showImageForEmptyUri(R.drawable.ic_profile_menu)
		.showImageOnFail(R.drawable.ic_profile_menu)
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
		.displayer(new RoundedBitmapDisplayer(50))
		.build();
		User currentUser = DataSingleton.getInstance().getUser();
		user.setId(id);
		if(currentUser.getId() == id){
			ownProfile = true;
		}
		else {
			ownProfile = false;
		}
	}
	
	public void getUserDetail(){
		
		RequestParams params = new RequestParams();
			params.put("authKey", DataSingleton.getInstance().getAuthKey());
			params.put("idUser",user.getId()+"");
			params.put("idUserFollower", DataSingleton.getInstance().getUser().getId() + "");
			MyRestClient.post(API_USER_DETAIL, params, new JsonHttpResponseHandler(){
				@Override
				public void onSuccess(JSONObject response) {
					UserResponse userResponse = new Gson().fromJson(response.toString(), UserResponse.class);
					user = userResponse.getData();
					detailUserProfileActivity.textViewStatus.setText(user.getStatus());
					detailUserProfileActivity.textViewName.setText(user.getName());
					imageLoader.displayImage(user.getImageProfilePath().getUrlImange(), 
							detailUserProfileActivity.imageViewProfile, displayImageOptions);
					detailUserProfileActivity.buttonFollower.setText(user.getJumlahFollowerUser() + "\nFollower");
					detailUserProfileActivity.buttonFollowing.setText(user.getJumlahFollowingUser() + "\nFollowing");
					
				}
			});
		
	}
	 
	
	public void follow(){
		apiFollow = API_FOLLOW;
		if(user.getIsFollowing()){
			apiFollow = API_UNFOLLOW;
		}
		
		
		RequestParams params = new RequestParams();
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		params.put("idUser", DataSingleton.getInstance().getUser().getId() + "");
		params.put("idUserFollow", user.getId() + "");
		MyRestClient.post(apiFollow, params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject response) {
				
			}
			
			@Override
			public void onFailure(Throwable e, JSONObject errorResponse) {
				
			}
		});
	}
	
	public void requestListLaporan(){
		RequestParams params = new RequestParams();
		params.put("idUser", user.getId() + "");
		params.put("type", "o");
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		MyRestClient.post(API_LIST_LAPORAN, params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject response) {
				ListLaporanResponse listLaporanResponse = new Gson().fromJson(response.toString(), ListLaporanResponse.class);
				listLaporan.clear();
				listLaporan.addAll(listLaporanResponse.getData());
				detailUserProfileActivity.customAdapter.notifyDataSetChanged();
//				Toast.makeText(detailUserProfileActivity.getActivity(), "jumlah data ada " + listLaporan.size(), Toast.LENGTH_LONG).show();
//				//detailUserProfileActivity.customAdapter.notifyDataSetChanged();
			}
			
		});
	}
	
	public void changeStatus(final String status){
		RequestParams params = new RequestParams();
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		params.put("idUser", DataSingleton.getInstance().getUser().getId() + "");
		params.put("status", status);
		MyRestClient.post(API_UPDATE_STATUS, params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject response) {
				user.setStatus(status);
				detailUserProfileActivity.textViewStatus.setText(user.getStatus());
			}
			
			@Override
			public void onFailure(Throwable e, JSONObject errorResponse) {
			}
		});
	}
	
	
	public User getUser() {
		return user;
	}
	
	public boolean isOwnProfile() {
		return ownProfile;
	}

	public ArrayList<Laporan> getListLaporan() {
		return listLaporan;
	}
	
	public ImageLoader getImageLoader() {
		return imageLoader;
	}
	public DisplayImageOptions getDisplayImageOptions() {
		return displayImageOptions;
	}
}
