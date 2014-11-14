package com.detektiflingkuganandroid;

import com.engine.DetailUserProfileEngine;
import com.framework.adapter.CustomAdapter;
import com.framework.common_utilities.ViewSetterUtilities;
import com.google.android.gms.drive.events.ChangeEvent;
import com.models.Constantstas;
import com.models.DataSingleton;
import com.models.Laporan;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.process.BitmapProcessor;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
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
public class ProfileFragment extends Fragment implements Constantstas{

	private DetailUserProfileEngine detailUserProfileEngine;

	private static int TAKE_PICTURE = 0;
	private static int SELECT_PICTURE = 1;
	
	private Long userId;
	
	public View rootView;
	public Button buttonFollower;
	public Button buttonFollowing;
	public Button buttonFollow;
	public ImageButton imageButtonEditStatus;
	public ImageView imageViewProfile;
	public GridView gridViewLaporan;
	public CustomAdapter<Laporan> customAdapter;
	public TextView textViewStatus, textViewName;
	
	// message to fragment pass here
	public ProfileFragment(Long userId) {
		this.userId = userId;
	}

	private void initialComponent() {
		detailUserProfileEngine = new DetailUserProfileEngine(this, userId);
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
		
		buttonFollow = (Button) rootView.findViewById(R.id.buttonFollow);
		buttonFollower = (Button) rootView.findViewById(R.id.buttonFollower);
		buttonFollowing = (Button) rootView.findViewById(R.id.buttonFollowing);
		imageButtonEditStatus = (ImageButton) rootView
				.findViewById(R.id.imageButtonEditStatus);
		textViewName = (TextView) rootView.findViewById(R.id.textViewName);
		textViewStatus =  (TextView) rootView.findViewById(R.id.textViewStatus);
		imageViewProfile = (ImageView) rootView.findViewById(R.id.imageViewProfile);
		gridViewLaporan = (GridView) rootView.findViewById(R.id.gridViewLaporan);
		buttonFollow.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		buttonFollower.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((HomeActivity)getActivity()).setFragment(new ListUserFragment(MODE_FOLLOWER), true);
			}
		});
		
		buttonFollowing.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((HomeActivity)getActivity()).setFragment(new ListUserFragment(MODE_FOLLOWING), true);
			}
		});
		
		imageButtonEditStatus.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				UpdateStatusDialog updateStatusDialog = new UpdateStatusDialog();
				updateStatusDialog.show(getFragmentManager(), "update_status");
			}
		});
		
		imageViewProfile.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(detailUserProfileEngine.isOwnProfile()){
					MenuPictureDialog menuPictureDialog = new MenuPictureDialog();
					menuPictureDialog.show(getFragmentManager(), "menu_picture_dialog");
				}
			}
		});
		
		customAdapter = new CustomAdapter<Laporan>(getActivity(), R.layout.screen_shot_image_laporan_layout, detailUserProfileEngine.getListLaporan()) {
			
			private DisplayImageOptions displayImageOptions;
			
			@Override
			public void initialComponent() {
				displayImageOptions =new DisplayImageOptions.Builder()
//				.showImageOnLoading(R.drawable.ic_action)
				.showImageForEmptyUri(R.drawable.ic_action)
//				.showImageOnFail(R.drawable.ic_action)
				.cacheInMemory(true)
				.cacheOnDisk(true)
				.considerExifParams(true)
				.displayer(new RoundedBitmapDisplayer(0))
//				.postProcessor(new BitmapProcessor() {
//					
//					@Override
//					public Bitmap process(Bitmap arg0) {
//						return Bitmap.createScaledBitmap(arg0, 50, 50, false);
//					}
//				})
				.build();
				super.initialComponent();
			}
			
			@Override
			public void setViewItems(View view, Laporan data) {
				detailUserProfileEngine.getImageLoader().displayImage(data.getImagePath().getUrlImange(), 
						(ImageView)view.findViewById(R.id.imageViewScreenShotLaporan), displayImageOptions);
			}
		};
		gridViewLaporan.setAdapter(customAdapter);
		if (detailUserProfileEngine.isOwnProfile()) {
			buttonFollow.setVisibility(View.GONE);
		}
		detailUserProfileEngine.getUserDetail();
		detailUserProfileEngine.requestListLaporan();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.detail_user_profile_activity_1,
				null);
		initialComponent();
		return rootView;
	}
	

	public class UpdateStatusDialog extends DialogFragment{
	private EditText editTextStatus;
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater layoutInflater = getActivity().getLayoutInflater();
		View view = layoutInflater.inflate(R.layout.update_status_dialog, null);
		
		editTextStatus = (EditText) view.findViewById(R.id.editTextServerStatus);
		editTextStatus.setText(detailUserProfileEngine.getUser().getStatus());
		builder.setView(view)
			.setTitle("Update Status")
			.setPositiveButton("Simpan", new  DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					detailUserProfileEngine.changeStatus(editTextStatus.getText().toString());
					Toast.makeText(getActivity(), "status updated", Toast.LENGTH_LONG).show();
				}
			})
			.setNegativeButton("Batal", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int which) {
					
				}
				
			});
		return builder.create();
	}
}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
//		Intent intent = new Intent(getActivity(), UserProfilePictureActivity.class);
		Toast.makeText(getActivity(), "req code " + resultCode, Toast.LENGTH_LONG).show();
//		if (requestCode == SELECT_PICTURE  && null != data) {
		if (requestCode == 65541 && data != null){
			Bundle extras = data.getExtras();
            
//            intent.putExtras(extras);
            
		}
		if(requestCode == 65537 && data != null){
//			 Uri selectedImage = data.getData();
//	         String[] filePathColumn = { MediaStore.Images.Media.DATA };
//	         Cursor cursor = getContentResolver().query(selectedImage,
//	                    filePathColumn, null, null, null);
//	         cursor.moveToFirst();
//	 
//	            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//	            String picturePath = cursor.getString(columnIndex);
//	            cursor.close();
//	            
//	            intent.putExtra("image_path", picturePath);
		}
//		startActivity(intent);	
        
    }
	
	public class MenuPictureDialog extends DialogFragment{
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		    builder
		           .setItems(R.array.menu_gambar, new DialogInterface.OnClickListener() {
		               public void onClick(DialogInterface dialog, int which) {
		            	   switch (which) {
						case 0:
							Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
							 
					        // start camera activity
							ProfileFragment.this.startActivityForResult(intent, TAKE_PICTURE);
							break;
						case 1:
//							Intent intent = new Intent();
//	                        intent.setType("image/*");
//	                        intent.setAction(Intent.ACTION_GET_CONTENT);
//	                        startActivityForResult(Intent.createChooser(intent,
//	                                "Select Picture"), SELECT_PICTURE);
							  Intent i = new Intent(
				                        Intent.ACTION_PICK,
				                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//				                 getActivity().startActivityFromFragment(ProfileFragment.this, i, SELECT_PICTURE);
				                ProfileFragment.this.startActivityForResult(i, SELECT_PICTURE);
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
}
