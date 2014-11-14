package com.engine;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;

import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.detektiflingkuganandroid.LaporActivity;
import com.framework.rest_clients.MyRestClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.models.Constantstas;
import com.models.DataSingleton;

public class LaporEngine implements Constantstas{
	private LaporActivity laporActivity;
	private String imagePath;
	private Bitmap bitmapImage;
	public LaporEngine(LaporActivity laporActivity) {
		super();
		this.laporActivity = laporActivity;
		imagePath = laporActivity.getIntent().getStringExtra("image_path");
		if(imagePath == null){
			bitmapImage = (Bitmap) laporActivity.getIntent().getExtras().get("data");
			laporActivity.imageViewImageLaporan.setImageBitmap(bitmapImage);
		}
		else {
			laporActivity.imageViewImageLaporan.setImageBitmap(BitmapFactory.decodeFile(imagePath));	
		}
		
	}
	
	public void uploadLaporan(String dataLaporan, Double longitude, Double latitude, String kategori){
		RequestParams params = new RequestParams();
		params.put("dataLaporan", dataLaporan);
		params.put("userId", DataSingleton.getInstance().getUser().getId() + "");
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		params.put("longitude", "" +longitude);
		params.put("latitude", "" + latitude);
		params.put("katagoriLaporan", kategori);
		try {
			if(imagePath == null){
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, out);
				byte[] data = out.toByteArray();
				ByteArrayInputStream istream = new ByteArrayInputStream(data);
				params.put("picture", istream, "image");
			}
			else {
				params.put("picture", new File(imagePath));	
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MyRestClient.post(API_INSERT_LAPORAN, params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject response) {
				Toast.makeText(laporActivity, "success submit laporan", Toast.LENGTH_LONG).show();
			}
			@Override
			public void onFailure(Throwable e, JSONObject errorResponse) {
				Toast.makeText(laporActivity, "error submit laporan" + errorResponse.toString(), Toast.LENGTH_LONG).show();
			}
		});
	}
	
}
