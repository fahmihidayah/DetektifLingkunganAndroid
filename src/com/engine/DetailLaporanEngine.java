package com.engine;

import java.util.ArrayList;

import org.json.JSONObject;

import android.content.Intent;
import android.widget.Toast;

import com.detektiflingkuganandroid.DetailLaporanActivity;
import com.detektiflingkuganandroid.DetailLaporanFragment;
import com.detektiflingkuganandroid.DetailUserProfileActivity;
import com.detektiflingkuganandroid.R;
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
import com.response.LaporanResponse;
import com.response.ListKomentarResponse;

public class DetailLaporanEngine implements Constantstas{
	private DetailLaporanFragment detailLaporanActivity;
	private ArrayList<Komentar> listKomentar = new ArrayList<Komentar>();
	private Laporan laporan;
	
	public DetailLaporanEngine(DetailLaporanFragment detailLaporanActivity, Laporan laporan) {
		super();
		this.detailLaporanActivity = detailLaporanActivity;
		this.laporan = laporan;
//		laporan = (Laporan) detailLaporanActivity.getIntent().getSerializableExtra("laporan");
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
	
	public void pantau(){
		String api = (laporan.isPantau() ? API_UNPANTAU: API_PANTAU); 
		RequestParams params = new RequestParams();
		params.put("idUser", DataSingleton.getInstance().getUser().getId() + "");
		params.put("idLaporan", laporan.getId() + "");
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		Toast.makeText(detailLaporanActivity.getActivity(), api + params.toString(), Toast.LENGTH_LONG).show();
		MyRestClient.post(api, params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject response) {
				LaporanResponse laporanResponse = new Gson().fromJson(response.toString(), LaporanResponse.class);
//				LaporanHelper laporanHelper = listLaporan.get(listLaporan.indexOf(laporanResponse.getData()));
				laporan.setPantau(laporanResponse.getData().isPantau());
				laporan.setJumlahUserPemantau(laporanResponse.getData().getJumlahUserPemantau());
				DataSingleton.getInstance().saveToFile(detailLaporanActivity.getActivity());
				detailLaporanActivity.customAdapter.notifyDataSetChanged();
//				detailLaporanActivity.buttonPantau.setBackgroundDrawable(R.color.green_button_act);
				Toast.makeText(detailLaporanActivity.getActivity(), "pantau", Toast.LENGTH_LONG).show();
			}
			@Override
			public void onFailure(Throwable e, JSONObject errorResponse) {
				Toast.makeText(detailLaporanActivity.getActivity(), "error", Toast.LENGTH_LONG).show();
			}
		});
	}
	
	public void view(){
		RequestParams params = new RequestParams();
		params.put("idLaporan", laporan.getId() + "");
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		MyRestClient.post(API_VIEW, params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject response) {
				// success view
//				Toast.makeText(detailLaporanActivity.getActivity(), "success",Toast.LENGTH_LONG).show();
			}
			@Override
			public void onFailure(Throwable e, JSONObject errorResponse) {
				// TODO Auto-generated method stub
//				Toast.makeText(detailLaporanActivity.getActivity(), "fail",Toast.LENGTH_LONG).show();
			}
		});
	}
	
	public void showFriendProfile(int idxKomentar){
//		User user = listKomentar.get(idxKomentar).getUser();
//		Intent intent= new Intent(detailLaporanActivity, DetailUserProfileActivity.class);
//		if(user.getId().equals(DataSingleton.getInstance().getUser().getId())){
//			intent.putExtra("own_profile", true);
//		}
//		else {
//			intent.putExtra("own_profile", false);
//		}
//		intent.putExtra("user", user);
//		detailLaporanActivity.startActivity(intent);
		
	}
	
	
}
