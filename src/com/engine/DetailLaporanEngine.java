package com.engine;

import java.util.ArrayList;

import org.json.JSONObject;

import android.content.Intent;
import android.widget.Toast;

import com.detektiflingkuganandroid.DetailLaporanActivity;
import com.detektiflingkuganandroid.DetailUserProfileActivity;
import com.framework.rest_clients.MyRestClient;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.models.Constantstas;
import com.models.DataSingleton;
import com.models.Komentar;
import com.models.Laporan;
import com.models.User;
import com.response.KomentarResponse;
import com.response.ListKomentarResponse;

public class DetailLaporanEngine implements Constantstas{
	private DetailLaporanActivity detailLaporanActivity;
	private ArrayList<Komentar> listKomentar = new ArrayList<Komentar>();
	private Laporan laporan;
	
	public DetailLaporanEngine(DetailLaporanActivity detailLaporanActivity) {
		super();
		this.detailLaporanActivity = detailLaporanActivity;
		laporan = (Laporan) detailLaporanActivity.getIntent().getSerializableExtra("laporan");
	}
	
	public void requestListKomentar(){
		RequestParams params = new RequestParams();
		params.put("idLaporan", laporan.getId()+"");
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		MyRestClient.post(API_LIST_KOMENTAR, params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject response) {
				ListKomentarResponse listKomentarResponse  = new Gson().fromJson(response.toString(), ListKomentarResponse.class);
				listKomentar.clear();
				listKomentar.addAll(listKomentarResponse.getData());
				detailLaporanActivity.customAdapter.notifyDataSetChanged();
			}
		});
	}
	
	public ArrayList<Komentar> getListKomentar() {
		return listKomentar;
	}
	
	public void comment(String comment){
		RequestParams params = new RequestParams();
		params.put("idLaporan", laporan.getId() + "");
		params.put("dataKomentar", comment);
		params.put("idUser", DataSingleton.getInstance().getUser().getId() + "");
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		MyRestClient.post(API_INSERT_KOMENTAR, params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject response) {
				KomentarResponse komentarResponse = new Gson().fromJson(response.toString(), KomentarResponse.class);
				listKomentar.add(komentarResponse.getData());
				detailLaporanActivity.customAdapter.notifyDataSetChanged();
			}
			
		});
	}
	
	public void showFriendProfile(int idxKomentar){
		User user = listKomentar.get(idxKomentar).getUser();
		Intent intent= new Intent(detailLaporanActivity, DetailUserProfileActivity.class);
		if(user.getId().equals(DataSingleton.getInstance().getUser().getId())){
			intent.putExtra("own_profile", true);
		}
		else {
			intent.putExtra("own_profile", false);
		}
		intent.putExtra("user", user);
		detailLaporanActivity.startActivity(intent);
		
	}
	
	
}
