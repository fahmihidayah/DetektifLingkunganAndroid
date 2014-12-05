package com.engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.json.JSONObject;

import android.app.DownloadManager.Request;
import android.app.ProgressDialog;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Toast;
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
		.displayer(new RoundedBitmapDisplayer(70))
		.build();
		User currentUser = DataSingleton.getInstance().getUser();
		user.setIdUser(id);
		if(currentUser.getIdUser().compareTo(id)==0){
			ownProfile = true;
		}
		else {
			ownProfile = false;
		}
	}
	
	public void getUserDetail(){
		
		RequestParams params = new RequestParams();
			params.put("authKey", DataSingleton.getInstance().getAuthKey());
			params.put("userId",user.getIdUser()+"");
			params.put("followerUserId", DataSingleton.getInstance().getUser().getIdUser() + "");
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
					if(!user.getIsFollowing()){
						detailUserProfileActivity.buttonFollow.setText("Follow me");
					}
					else {
						detailUserProfileActivity.buttonFollow.setText("Unfollow me");
					}
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
		params.put("userId", DataSingleton.getInstance().getUser().getIdUser() + "");
		params.put("followedUserId", user.getIdUser() + "");
		Toast.makeText(detailUserProfileActivity.getActivity(), params.toString() + " " + apiFollow, 1000).show();
		MyRestClient.post(apiFollow, params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject response) {
				if(user.getIsFollowing()){
					detailUserProfileActivity.buttonFollow.setText("Follow me");
					user.setJumlahFollowerUser(user.getJumlahFollowerUser()-1);
					detailUserProfileActivity.buttonFollower.setText(user.getJumlahFollowerUser()+ "\nFollower");
					user.setIsFollowing(false);
				}
				else {
					user.setJumlahFollowerUser(user.getJumlahFollowerUser()+1);
					detailUserProfileActivity.buttonFollower.setText(user.getJumlahFollowerUser() + "\nFollower");
					detailUserProfileActivity.buttonFollow.setText("Unfollow me");
					user.setIsFollowing(true);
				}
				
			}
			
			@Override
			public void onFailure(Throwable e, JSONObject errorResponse) {
				Toast.makeText(detailUserProfileActivity.getActivity(), "error"  , Toast.LENGTH_LONG).show();
			}
		});
	}
	
	public void requestListLaporan(){
		RequestParams params = new RequestParams();
		params.put("userId", user.getIdUser() + "");
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
		params.put("userId", DataSingleton.getInstance().getUser().getIdUser() + "");
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
	
	public void changeUserImageProfile(final String imagePath){
		RequestParams params = new RequestParams();
		params.put("userId", user.getIdUser() + "");
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		Toast.makeText(detailUserProfileActivity.getActivity(), params.toString(), 1000).show();
		File imageFile = new File(imagePath);
		final ProgressDialog progress = new ProgressDialog(detailUserProfileActivity.getActivity());
		if(imageFile.exists()){
			try {
				params.put("picture", imageFile);
				
				MyRestClient.post(API_CHANGE_USER_PROF_PICT, params, new JsonHttpResponseHandler(){
					
					@Override
					public void onProgress(int bytesWritten, int totalSize) {
						progress.setMessage("Uploading image... ");
						progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
						progress.setIndeterminate(true);
						progress.show();
					}
					
					@Override
					public void onSuccess(JSONObject response) {
						//detailUserProfileActivity.imageViewProfile.setImageBitmap(BitmapFactory.decodeFile(imagePath));
						
						imageLoader.displayImage(imagePath, 
								detailUserProfileActivity.imageViewProfile, displayImageOptions);
						Toast.makeText(detailUserProfileActivity.getActivity(), "profile pict change", Toast.LENGTH_LONG).show();
						progress.dismiss();
					}
					@Override
					public void onFailure(Throwable e, JSONObject errorResponse) {
						Toast.makeText(detailUserProfileActivity.getActivity(), "fail upload image", Toast.LENGTH_LONG).show();
						super.onFailure(e, errorResponse);
						progress.dismiss();
					}
				});
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
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
