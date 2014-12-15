package com.framework.image_handler;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat")
public class CropImageHandler {
	public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 200;
	public static final int GALERY_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	public static final int CROP_IMAGE_ACTIVITY_REQUEST_CODE = 300;
	public static String APP_FOLDER = "CROP";
	public static final int MEDIA_TYPE_IMAGE = 1;
	public static final int MEDIA_TYPE_VIDEO = 2;
	public static int ACTIVITY = 0;
	public static int FRAGMENT = 1;
	
	
	private Activity activity;
	private Fragment fragment;
	private Context context;
	
	public int type;
	private Uri fileUri;

	public CropImageHandler(Activity activity) {
		super();
		this.activity = activity;
		type = ACTIVITY;
		context = activity;
	}
	
	public CropImageHandler(Fragment fragment) {
		this.fragment = fragment;
		type = FRAGMENT;
		context = fragment.getActivity();
	}

	private static Uri getOutputMediaFileUri(int type) {
		return Uri.fromFile(getOutputMediaFile(type));
	}

	private static File getOutputMediaFile(int type) {
		File mediaStorageDir = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				APP_FOLDER);
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d("MyCameraApp", "failed to create directory");
				return null;
			}
		}

		// Create a media file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());
		File mediaFile;
		if (type == MEDIA_TYPE_IMAGE) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "IMG_" + timeStamp + ".jpg");
		} else if (type == MEDIA_TYPE_VIDEO) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "VID_" + timeStamp + ".mp4");
		} else {
			return null;
		}
		return mediaFile;
	}

	public void showCamera() {
		Intent cameraIntent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
		cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
		if(type == ACTIVITY){
			activity.startActivityForResult(cameraIntent,
					CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);	
		}
		else {
			fragment.startActivityForResult(cameraIntent,
					CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
		}
		
	}

	public void showGalery() {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		if(type == ACTIVITY){
			activity.startActivityForResult(
					Intent.createChooser(intent, "Select Picture"),
					GALERY_IMAGE_ACTIVITY_REQUEST_CODE);	
		}
		else {
			fragment.startActivityForResult(
					Intent.createChooser(intent, "Select Picture"),
					GALERY_IMAGE_ACTIVITY_REQUEST_CODE);
		}
		
	}

	public void showCrop() {
		try {
			Intent intent = new Intent("com.android.camera.action.CROP");
			intent.setDataAndType(fileUri, "image/*");
			fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); 
			intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
			intent.putExtra("crop", "true");
			intent.putExtra("aspectX", 1);
			intent.putExtra("aspectY", 1);
			intent.putExtra("outputX", 128);
			intent.putExtra("outputY", 128);
			// intent.putExtra("noFaceDetection", true);
			intent.putExtra("return-data", true);
			if(type == ACTIVITY){
				activity.startActivityForResult(intent, CROP_IMAGE_ACTIVITY_REQUEST_CODE);
			}
			else {
				fragment.startActivityForResult(intent, CROP_IMAGE_ACTIVITY_REQUEST_CODE);
			}
			
		}
		// respond to users whose devices do not support the crop action
		catch (ActivityNotFoundException anfe) {
			// display an error message
			String errorMessage = "Whoops - your device doesn't support the crop action!";
			Toast toast = Toast.makeText(context, errorMessage,
					Toast.LENGTH_SHORT);
			toast.show();
		}
	}

	public Uri getFileUri() {
		return fileUri;
	}
	
	public void setUriFromIntentResult(Intent intent){
		this.fileUri = intent.getData();
	}

}
