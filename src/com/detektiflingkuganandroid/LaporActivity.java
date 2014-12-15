package com.detektiflingkuganandroid;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;

import com.engine.ProgressDialogFactory;
import com.framework.adapter.CustomAdapter;
import com.framework.common_utilities.ViewSetterUtilities;
import com.framework.image_handler.CropImageHandler;
import com.framework.rest_clients.MyRestClient;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.models.Constantstas;
import com.models.DataSingleton;
import com.models.ImagePath;
import com.models.ImageUpload;
import com.models.Laporan;
import com.response.LaporanResponse;
import com.svenkapudija.imagechooser.AlertDialogImageChooser;
import com.svenkapudija.imagechooser.ImageChooser;
import com.svenkapudija.imagechooser.ImageChooserListener;
import com.svenkapudija.imagechooser.StorageOption;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class LaporActivity extends FragmentActivity implements Constantstas {

	@InjectView(R.id.gridViewImageLaporan) GridView gridViewImageLaporan;
	
	private CustomAdapter<ImageUpload> customAdapter;
	@InjectView(R.id.editTextJudulLaporan) EditText editTextJudulLaporan;
	@InjectView(R.id.editTextDataLaporan) EditText editTextDataLaporan;
	
	@InjectView(R.id.spinnerKategori) Spinner spinnerKategori;
	
	@OnItemClick(R.id.gridViewImageLaporan)
	public void onClickLaporan(int position){
		if (listImageUploads.get(position).isAddImage()) {
			MenuPictureDialog menuPictureDialog = new MenuPictureDialog();
			menuPictureDialog.show(getSupportFragmentManager(), "menu");
//			chooser.show();]
			
		} else {
			Toast.makeText(LaporActivity.this, "edit", 1000).show();
		}
	}
	
	
	CropImageHandler cropImageHandler;
	String latitude, longitude;
	
	private ArrayList<ImageUpload> listImageUploads = new ArrayList<ImageUpload>();

	ProgressDialogFactory dialogFactory;
	int sizeImage;
	ImageChooser chooser;
	private void initialComponent() {
		cropImageHandler = new CropImageHandler(this);
//		chooser = new AlertDialogImageChooser(this, 1000);
//		chooser.saveImageTo(StorageOption.SD_CARD_APP_ROOT, "myDirectory", "myFabulousImage");
		
		ImageUpload imageUpload = new ImageUpload();
		imageUpload.setResource(R.drawable.ic_add);
		imageUpload.setAddImage(true);
		listImageUploads.add(imageUpload);
		
		View customActionBar = getLayoutInflater().inflate(
				R.layout.custom_laporan_action_bar, null);
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setDisplayShowCustomEnabled(true);
		getActionBar().setCustomView(customActionBar);
		ViewSetterUtilities.getImageView(customActionBar, R.id.imageButtonBack)
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						onBackPressed();
					}
				});
		customAdapter = new CustomAdapter<ImageUpload>(this,
				R.layout.screen_shot_image_laporan_layout,
				listImageUploads) {

			@Override
			public void setViewItems(View view, ImageUpload data) {
				 final BitmapFactory.Options options = new BitmapFactory.Options();
				 options.inSampleSize = 8;
				if (data.isAddImage()) {
					ViewSetterUtilities
							.setResToImageView(view,
									R.id.imageViewScreenShotLaporan,
									data.getResource());
				} else {
					ViewSetterUtilities.setUriToImageView(view, R.id.imageViewScreenShotLaporan, 
							data.getUri());
				}

			}
		};
		gridViewImageLaporan.setAdapter(customAdapter);
		
		initialLocation();
	}

	private void initialLocation() {
		GPSTracker gpsTracker = new GPSTracker(this);

		if (gpsTracker.canGetLocation()) {
			 latitude = String.valueOf(gpsTracker.latitude);

			longitude = String.valueOf(gpsTracker.longitude);

			// String country = gpsTracker.getCountryName(this);
			// String city = gpsTracker.getLocality(this);
			// String postalCode = gpsTracker.getPostalCode(this);
			String addressLine = gpsTracker.getAddressLine(this);
		} else {
			// can't get location
			// GPS or Network is not enabled
			// Ask user to enable GPS/network in settings
			gpsTracker.showSettingsAlert();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lapor_fragment);
		ButterKnife.inject(this);
		initialComponent();
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
//		Toast.makeText(this, "request code " + requestCode + " result code " + resultCode,Toast.LENGTH_LONG).show();
		if(requestCode == CropImageHandler.GALERY_IMAGE_ACTIVITY_REQUEST_CODE){
			if(resultCode == Activity.RESULT_OK){
				cropImageHandler.setUriFromIntentResult(data);
				cropImageHandler.showCrop();	
			}
		}
		if(requestCode == CropImageHandler.CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE){
			if(resultCode == Activity.RESULT_OK){
				cropImageHandler.showCrop();
			}
		}
		if(requestCode == CropImageHandler.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
			if(resultCode == Activity.RESULT_OK){
				addImage(cropImageHandler.getFileUri());
				cropImageHandler = new CropImageHandler(this);
			}
		}
		
	}

	public void onClickSubmit(View view) {
		if(listImageUploads.size() <= 1){
			Toast.makeText(this, "Image laporan belum ada", Toast.LENGTH_LONG).show();
			return;
		}
		RequestParams params = new RequestParams();
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		params.put("userId", DataSingleton.getInstance().getUser().getIdUser() + "");
		params.put("judulLaporan", editTextJudulLaporan.getText().toString());
		params.put("longitude", longitude);
		params.put("latitude", latitude);
		params.put("katagoriLaporan", spinnerKategori.getSelectedItem().toString());
		params.put("dataLaporan", editTextDataLaporan.getText().toString());
		 dialogFactory = new ProgressDialogFactory(this);
		MyRestClient.post(API_INSERT_LAPORAN, params, new JsonHttpResponseHandler(){
			@Override
			public void onProgress(int bytesWritten, int totalSize) {
				dialogFactory.show("submit laporan", false);
			}
			@Override
			public void onSuccess(JSONObject response) {
				LaporanResponse laporanResponse = new Gson().fromJson(response.toString(), LaporanResponse.class);
				Laporan laporan = laporanResponse.getData();
				sizeImage = listImageUploads.size();
				for (ImageUpload e : listImageUploads) {
					if(!e.isAddImage()){
						submitImageLaporan(laporan, e.getUri());	
					}
				}
				Toast.makeText(LaporActivity.this, "sucess insert laporan", Toast.LENGTH_LONG).show();
			}
			@Override
			public void onFailure(Throwable e, JSONObject errorResponse) {
				// TODO Auto-generated method stub
				Toast.makeText(LaporActivity.this, "fail insert laporan", Toast.LENGTH_LONG).show();
				dialogFactory.dismiss();
			}
		});
	}

	private void submitImageLaporan(Laporan laporan, Uri imagePath){
		RequestParams params = new RequestParams();
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		params.put("laporanId", laporan.getIdLaporan()+"");
		
		Bitmap bitmap;
		try {
			bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imagePath));
			ByteArrayOutputStream  arrayOutputStream = new ByteArrayOutputStream();
			bitmap.compress(CompressFormat.JPEG, 100, arrayOutputStream);		
			byte [] bmdata = arrayOutputStream.toByteArray();
			InputStream is = new  ByteArrayInputStream(bmdata);
//				params.put("picture", file);
				params.put("picture", is);
				
				MyRestClient.post(API_INSERT_IMAGE_LAPORAN, params, new JsonHttpResponseHandler()
				{
					@Override
					public void onSuccess(JSONObject response) {
						Toast.makeText(LaporActivity.this, "success insert image", Toast.LENGTH_LONG).show();
						sizeImage--;
						if(sizeImage == 1){
							LaporActivity.this.onBackPressed();
							dialogFactory.dismiss();
						}
					}
					@Override
					public void onFailure(Throwable e, JSONObject errorResponse) {
//						sizeImage--;
//						Toast.makeText(LaporActivity.this, "fail insert image", Toast.LENGTH_LONG).show();
//						if(sizeImage == 0){
//							dialogFactory.dismiss();
//						}
						sizeImage--;
						LaporActivity.this.onBackPressed();
						dialogFactory.dismiss();
					}
					
				});
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public void addImage(Uri path){
		ImageUpload imageUpload = new ImageUpload();
		imageUpload.setAddImage(false);
		imageUpload.setUri(path);
		imageUpload.setResource(-1);
		
		ImageUpload imageUpload2 = listImageUploads.remove(listImageUploads.size() - 1);
		listImageUploads.add(imageUpload);
		listImageUploads.add(imageUpload2);
		customAdapter.notifyDataSetChanged();
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
							cropImageHandler.showCamera();
							break;
						case 1:
							cropImageHandler.showGalery();
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
