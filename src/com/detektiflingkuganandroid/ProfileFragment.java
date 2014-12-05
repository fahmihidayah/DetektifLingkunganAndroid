package com.detektiflingkuganandroid;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.engine.DetektivUtilities;
import com.engine.ProgressDialogFactory;
import com.framework.adapter.CustomAdapter;
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
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.response.ListLaporanResponse;
import com.response.UserResponse;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class ProfileFragment extends Fragment implements Constantstas {


	private static int TAKE_PICTURE = 0;
	private static int SELECT_PICTURE = 1;

	private Long userId;
	public View rootView;
	@InjectView(R.id.buttonFollower) Button buttonFollower;
	@InjectView(R.id.buttonFollow) Button buttonFollow;
	@InjectView(R.id.buttonFollowing) Button buttonFollowing;
	
	@InjectView(R.id.imageButtonEditStatus) ImageButton imageButtonEditStatus;
	@InjectView(R.id.imageViewProfile) ImageView imageViewProfile;
	@InjectView(R.id.gridViewLaporan) GridView gridViewLaporan;
	
	@InjectView(R.id.textViewName) TextView textViewName;
	@InjectView(R.id.textViewStatus) TextView textViewStatus;
	
	@OnClick(R.id.buttonFollow)
	public void follow(){
		apiFollow = API_FOLLOW;
		if(user.getIsFollowing()){
			apiFollow = API_UNFOLLOW;
		}
		
		RequestParams params = new RequestParams();
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		params.put("userId", DataSingleton.getInstance().getUser().getIdUser() + "");
		params.put("followedUserId", user.getIdUser() + "");
		
		final ProgressDialogFactory dialogFactory = new ProgressDialogFactory(getActivity());
		
		MyRestClient.post(apiFollow, params, new JsonHttpResponseHandler(){
			@Override
			public void onProgress(int bytesWritten, int totalSize) {
				dialogFactory.show("loading ..", false);
			}
			
			@Override
			public void onSuccess(JSONObject response) {
				if(user.getIsFollowing()){
					buttonFollow.setText("Follow me");
					user.setJumlahFollowerUser(user.getJumlahFollowerUser()-1);
					buttonFollower.setText(user.getJumlahFollowerUser()+ "\nFollower");
					user.setIsFollowing(false);
				}
				else {
					user.setJumlahFollowerUser(user.getJumlahFollowerUser()+1);
					buttonFollower.setText(user.getJumlahFollowerUser() + "\nFollower");
					buttonFollow.setText("Unfollow me");
					user.setIsFollowing(true);
				}
				dialogFactory.dismiss();
			}
			
			@Override
			public void onFailure(Throwable e, JSONObject errorResponse) {
				dialogFactory.dismiss();
				Toast.makeText(getActivity(), "error"  , Toast.LENGTH_LONG).show();
			}
		});
	}
	
	@OnClick(R.id.buttonFollowing)
	public void onClickFollowing(View v) {
		((HomeActivity) getActivity()).setFragment(new ListUserFragment(MODE_FOLLOWING,  user), true);
	}
	
	@OnClick(R.id.buttonFollower)
	public void onClickFollower(View v) {
		((HomeActivity) getActivity()).setFragment(new ListUserFragment(MODE_FOLLOWER, user), true);
	}
	
	@OnClick(R.id.imageButtonEditStatus)
	public void onClickEditStatus(View view){
		UpdateStatusDialog updateStatusDialog = new UpdateStatusDialog();
		updateStatusDialog.show(getFragmentManager(), "update_status");
	}
	
	@OnClick(R.id.imageViewProfile) 
	public void onClickImageViewProfile(View v){
		if (ownProfile) {
			MenuPictureDialog menuPictureDialog = new MenuPictureDialog();
			menuPictureDialog.show(getFragmentManager(),
					"menu_picture_dialog");
		}
	}
	
	public DetektivUtilities imageViewHandler;
	public CustomAdapter<Laporan> customAdapter;
	private ArrayList<Laporan> listLaporan = new ArrayList<Laporan>();
	private User user = new User();
	private boolean ownProfile;
	private String apiFollow;
	
	// message to fragment pass here
	public ProfileFragment(Long userId) {
		this.userId = userId;
	}

	private void initialComponent() {
//		detailUserProfileEngine = new DetailUserProfileEngine(this, userId);
		user.idUser = userId;
		if(DataSingleton.getInstance().getUser().getIdUser().equals(userId)){
			ownProfile = true;
			user = DataSingleton.getInstance().getUser();
		}
		imageViewHandler = new DetektivUtilities(getActivity(), 70);
		View customActionBar = getActivity().getLayoutInflater().inflate(
				R.layout.custom_user_detail_profile_action_bar, null);
		getActivity().getActionBar().setDisplayShowHomeEnabled(false);
		getActivity().getActionBar().setDisplayShowTitleEnabled(false);
		getActivity().getActionBar().setDisplayShowCustomEnabled(true);
		getActivity().getActionBar().setCustomView(customActionBar);
		ViewSetterUtilities.getImageView(customActionBar, R.id.imageButtonBack)
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						getActivity().onBackPressed();
					}
				});
		
		customAdapter = new CustomAdapter<Laporan>(getActivity(),
				R.layout.screen_shot_image_laporan_layout,
				listLaporan) {

			private DisplayImageOptions displayImageOptions;

			@Override
			public void initialComponent() {
				displayImageOptions = new DisplayImageOptions.Builder()
						// .showImageOnLoading(R.drawable.ic_action)
						.showImageForEmptyUri(R.drawable.ic_action)
						// .showImageOnFail(R.drawable.ic_action)
						.cacheInMemory(true).cacheOnDisk(true)
						.considerExifParams(true)
						.displayer(new RoundedBitmapDisplayer(0))
						// .postProcessor(new BitmapProcessor() {
						//
						// @Override
						// public Bitmap process(Bitmap arg0) {
						// return Bitmap.createScaledBitmap(arg0, 50, 50,
						// false);
						// }
						// })
						.build();
				super.initialComponent();
			}

			@Override
			public void setViewItems(View view, Laporan data) {
				imageViewHandler.getImageLoader().displayImage(
						data.getListImagePath().get(0).getUrlImange(),
						(ImageView) view
								.findViewById(R.id.imageViewScreenShotLaporan),
						displayImageOptions);
			}
		};
		gridViewLaporan.setAdapter(customAdapter);
		
		if (ownProfile) {
			buttonFollow.setVisibility(View.GONE);
		}
		else {
			imageButtonEditStatus.setVisibility(View.GONE);
		}
		getUserDetail();
		requestListLaporan();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.detail_user_profile_activity_1,
				null);
		ButterKnife.inject(this, rootView);
		initialComponent();
		return rootView;
	}

	public class UpdateStatusDialog extends DialogFragment {
		private EditText editTextStatus;

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			LayoutInflater layoutInflater = getActivity().getLayoutInflater();
			View view = layoutInflater.inflate(R.layout.update_status_dialog,
					null);

			editTextStatus = (EditText) view
					.findViewById(R.id.editTextServerStatus);
			editTextStatus.setText(user
					.getStatus());
			builder.setView(view)
					.setTitle("Update Status")
					.setPositiveButton("Simpan",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									changeStatus(editTextStatus
													.getText().toString());
									Toast.makeText(getActivity(),
											"status updated", Toast.LENGTH_LONG)
											.show();
								}
							})
					.setNegativeButton("Batal",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {

								}

							});
			return builder.create();
		}
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// // Intent intent = new Intent(getActivity(),
		// // UserProfilePictureActivity.class);
		// Toast.makeText(getActivity(), "req code " + requestCode,
		// Toast.LENGTH_LONG).show();
		// // if (requestCode == SELECT_PICTURE && null != data) {
		if (data != null) {
			Bundle extras = data.getExtras();

			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };
			Cursor cursor = getActivity().getContentResolver().query(
					selectedImage, filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();
			YesNoDialog yesNoDialog = new YesNoDialog(picturePath);
			yesNoDialog.show(getFragmentManager(), "yes_no");
		}

	}

	public class MenuPictureDialog extends DialogFragment {
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setItems(R.array.menu_gambar,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							switch (which) {
							case 0:
								Intent intent = new Intent(
										MediaStore.ACTION_IMAGE_CAPTURE);

								// start camera activity
								ProfileFragment.this.startActivityForResult(
										intent, TAKE_PICTURE);
								break;
							case 1:
								// Intent intent = new Intent();
								// intent.setType("image/*");
								// intent.setAction(Intent.ACTION_GET_CONTENT);
								// startActivityForResult(Intent.createChooser(intent,
								// "Select Picture"), SELECT_PICTURE);
								Intent i = new Intent(
										Intent.ACTION_PICK,
										android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
								// getActivity().startActivityFromFragment(ProfileFragment.this,
								// i, SELECT_PICTURE);
								ProfileFragment.this.startActivityForResult(i,
										SELECT_PICTURE);
								break;
							case 2:
								break;
							default:
								break;
							}
						}
					});
			return builder.create();
		}

	}

	public class YesNoDialog extends DialogFragment {
		private String imagePath;
		
		public YesNoDialog(String imagePath) {
			super();
			this.imagePath = imagePath;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle("Konfirmasi");
			builder.setMessage("Apakah anda ingin mengganti gambar profile?");
			builder.setPositiveButton("Yes", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					changeUserImageProfile(imagePath);
				}
			});
			builder.setNegativeButton("No", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					
				}
			});
			
			return builder.create();
		}
	}
	

	public void getUserDetail(){
		
		RequestParams params = new RequestParams();
			params.put("authKey", DataSingleton.getInstance().getAuthKey());
			params.put("userId",user.getIdUser()+"");
			params.put("followerUserId", DataSingleton.getInstance().getUser().getIdUser() + "");
			
			final ProgressDialogFactory dialogFactory = new ProgressDialogFactory(getActivity());
			
			MyRestClient.post(API_USER_DETAIL, params, new JsonHttpResponseHandler(){
				
				@Override
				public void onProgress(int bytesWritten, int totalSize) {
					dialogFactory.show("Load user detail...",false);
				}
				
				@Override
				public void onSuccess(JSONObject response) {
					
					UserResponse userResponse = new Gson().fromJson(response.toString(), UserResponse.class);
					user = userResponse.getData();
					textViewStatus.setText(user.getStatus());
					textViewName.setText(user.getName());
					imageViewHandler.getImageLoader().displayImage(user.getImageProfilePath().getUrlImange(), 
							imageViewProfile, imageViewHandler.getDisplayImageOptionsProfile());
					buttonFollower.setText(user.getJumlahFollowerUser() + "\nFollower");
					buttonFollowing.setText(user.getJumlahFollowingUser() + "\nFollowing");
					if(!user.getIsFollowing()){
						buttonFollow.setText("Follow me");
					}
					else {
						buttonFollow.setText("Unfollow me");
					}
					dialogFactory.dismiss();
				}
				
				@Override
				public void onFailure(Throwable e, JSONObject errorResponse) {
					dialogFactory.dismiss();
				}
			});
	}
	
	public void changeUserImageProfile(final String imagePath){
		RequestParams params = new RequestParams();
		params.put("userId", user.getIdUser() + "");
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		Toast.makeText(getActivity(), params.toString(), 1000).show();
		File imageFile = new File(imagePath);
		final ProgressDialogFactory dialogFactory = new ProgressDialogFactory(getActivity());
		if(imageFile.exists()){
			try {
				params.put("picture", imageFile);
				
				MyRestClient.post(API_CHANGE_USER_PROF_PICT, params, new JsonHttpResponseHandler(){
					
					@Override
					public void onProgress(int bytesWritten, int totalSize) {
						dialogFactory.show("Upload image", false);
					}
					
					@Override
					public void onSuccess(JSONObject response) {
						//detailUserProfileActivity.imageViewProfile.setImageBitmap(BitmapFactory.decodeFile(imagePath));
						UserResponse userResponse = new Gson().fromJson(response.toString(), UserResponse.class);
						imageViewHandler.getImageLoader().displayImage(userResponse.getData().getImageProfilePath().getUrlImange(), 
								imageViewProfile, imageViewHandler.getDisplayImageOptionsProfile());
						Toast.makeText(getActivity(), "profile pict change", Toast.LENGTH_LONG).show();
						dialogFactory.dismiss();
					}
					@Override
					public void onFailure(Throwable e, JSONObject errorResponse) {
						Toast.makeText(getActivity(), "fail upload image", Toast.LENGTH_LONG).show();
						super.onFailure(e, errorResponse);
						dialogFactory.dismiss();
					}
				});
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			
		}
	}
	public void requestListLaporan(){
		RequestParams params = new RequestParams();
		params.put("userId", user.getIdUser() + "");
		params.put("type", "o");
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		final ProgressDialogFactory dialogFactory = new ProgressDialogFactory(getActivity());
		MyRestClient.post(API_LIST_LAPORAN, params, new JsonHttpResponseHandler(){
			
			@Override
			public void onProgress(int bytesWritten, int totalSize) {
				dialogFactory.show("Request list laporan...", false);
			}
			
			@Override
			public void onSuccess(JSONObject response) {
				ListLaporanResponse listLaporanResponse = new Gson().fromJson(response.toString(), ListLaporanResponse.class);
				listLaporan.clear();
				listLaporan.addAll(listLaporanResponse.getData());
				customAdapter.notifyDataSetChanged();
				dialogFactory.dismiss();
			}
			
			@Override
			public void onFailure(Throwable e, JSONObject errorResponse) {
				dialogFactory.dismiss();
			}
			
		});
	}
	
	public void changeStatus(final String status){
		RequestParams params = new RequestParams();
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		params.put("userId", DataSingleton.getInstance().getUser().getIdUser() + "");
		params.put("status", status);
		final ProgressDialogFactory dialogFactory = new ProgressDialogFactory(getActivity());
		MyRestClient.post(API_UPDATE_STATUS, params, new JsonHttpResponseHandler(){
			
			@Override
			public void onProgress(int bytesWritten, int totalSize) {
				dialogFactory.show("Set status", false);
			}
			
			@Override
			public void onSuccess(JSONObject response) {
				user.setStatus(status);
				textViewStatus.setText(user.getStatus());
				dialogFactory.dismiss();
			}
			
			@Override
			public void onFailure(Throwable e, JSONObject errorResponse) {
				dialogFactory.dismiss();
			}
		});
	}
}
