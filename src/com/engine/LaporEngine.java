package com.engine;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.detektiflingkuganandroid.LaporActivity;
import com.detektiflingkuganandroid.R;
import com.framework.rest_clients.MyRestClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.models.Constantstas;
import com.models.DataSingleton;
import com.models.ImageUpload;

public class LaporEngine implements Constantstas {
	private LaporActivity laporActivity;
	private String imagePath;
	private Bitmap bitmapImage;

	private ArrayList<ImageUpload> listImageUploads = new ArrayList<ImageUpload>();

	public LaporEngine(LaporActivity laporActivity) {
		super();
		this.laporActivity = laporActivity;
		
		ImageUpload imageUpload = new ImageUpload();
		imageUpload.setPath("");
		imageUpload.setResource(R.drawable.ic_add);
		imageUpload.setAddImage(true);
		listImageUploads.add(imageUpload);

	}

	public ArrayList<ImageUpload> getListImageUploads() {
		return listImageUploads;
	}

	public void addImage(String path){
		ImageUpload imageUpload = new ImageUpload();
		imageUpload.setAddImage(false);
		imageUpload.setPath(path);
		imageUpload.setResource(-1);
		
		ImageUpload imageUpload2 = listImageUploads.remove(listImageUploads.size() - 1);
		listImageUploads.add(imageUpload);
		listImageUploads.add(imageUpload2);
		
	}
	
	public void uploadLaporan(String dataLaporan, Double longitude,
			Double latitude, String kategori, String judulLaporan) {
		RequestParams params = new RequestParams();
		params.put("dataLaporan", dataLaporan);
		params.put("userId", DataSingleton.getInstance().getUser().getId() + "");
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		params.put("longitude", "" + longitude);
		params.put("latitude", "" + latitude);
		params.put("katagoriLaporan", kategori);
		try {
			if (imagePath == null) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, out);
				byte[] data = out.toByteArray();
				ByteArrayInputStream istream = new ByteArrayInputStream(data);
				params.put("picture", istream, "image");
			} else {
				params.put("picture", new File(imagePath));
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MyRestClient.post(API_INSERT_LAPORAN, params,
				new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONObject response) {
						Toast.makeText(laporActivity, "success submit laporan",
								Toast.LENGTH_LONG).show();
					}

					@Override
					public void onFailure(Throwable e, JSONObject errorResponse) {
						Toast.makeText(
								laporActivity,
								"error submit laporan"
										+ errorResponse.toString(),
								Toast.LENGTH_LONG).show();
					}
				});
	}

}
