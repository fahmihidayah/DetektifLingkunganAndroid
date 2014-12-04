package com.detektiflingkuganandroid;

import com.engine.LaporEngine;
import com.framework.adapter.CustomAdapter;
import com.framework.common_utilities.ViewSetterUtilities;
import com.models.Constantstas;
import com.models.DataSingleton;
import com.models.ImageUpload;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
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

	private GridView gridViewImageLaporan;
	private CustomAdapter<ImageUpload> customAdapter;
	private EditText editTextJudulLaporan, editTextDataLaporan;
	
	private Spinner spinnerKategori;
	String stringLatitude, stringLongitude;
	private LaporEngine laporEngine;

	private void initialComponent() {
		laporEngine = new LaporEngine(this);
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
		gridViewImageLaporan = (GridView) findViewById(R.id.gridViewImageLaporan);
		editTextDataLaporan = (EditText) findViewById(R.id.editTextDataLaporan);
		editTextJudulLaporan = (EditText) findViewById(R.id.editTextJudulLaporan);
		spinnerKategori = (Spinner) findViewById(R.id.spinnerKategori);
		customAdapter = new CustomAdapter<ImageUpload>(this,
				R.layout.screen_shot_image_laporan_layout,
				laporEngine.getListImageUploads()) {

			@Override
			public void setViewItems(View view, ImageUpload data) {
				if (data.isAddImage()) {
					ViewSetterUtilities
							.setResToImageView(view,
									R.id.imageViewScreenShotLaporan,
									data.getResource());
				} else {
					ViewSetterUtilities.setPathToImageView(view, R.id.imageViewScreenShotLaporan, 
							data.getPath());
				}

			}
		};
		gridViewImageLaporan.setAdapter(customAdapter);
		gridViewImageLaporan.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if (laporEngine.getListImageUploads().get(arg2).isAddImage()) {
					MenuPictureDialog menuPictureDialog = new MenuPictureDialog();
					menuPictureDialog.show(getSupportFragmentManager(), "menu");
				} else {
					Toast.makeText(LaporActivity.this, "edit", 1000).show();
				}
			}
		});
		initialLocation();
	}

	private void initialLocation() {
		GPSTracker gpsTracker = new GPSTracker(this);

		if (gpsTracker.canGetLocation()) {
			 stringLatitude = String.valueOf(gpsTracker.latitude);

			stringLongitude = String.valueOf(gpsTracker.longitude);

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
		initialComponent();
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		Intent intent = new Intent(this, LaporActivity.class);

		// if (requestCode == SELECT_PICTURE && null != data) {
		if (data != null) {
			Bundle extras = data.getExtras();
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };
			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();
			laporEngine.addImage(picturePath);
			customAdapter.notifyDataSetChanged();
		}

	}

	public void onClickSubmit(View view) {
		laporEngine.submitLaporan(editTextJudulLaporan.getText().toString(), editTextDataLaporan.getText().toString(), spinnerKategori.getSelectedItem().toString(), stringLongitude, stringLatitude);
	}
}
