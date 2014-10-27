package com.engine;

import java.util.ArrayList;

import org.json.JSONObject;

import com.detektiflingkuganandroid.DetailLaporanActivity;
import com.framework.rest_clients.MyRestClient;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.models.Constantstas;
import com.models.DataSingleton;
import com.models.Komentar;
import com.models.Laporan;
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
	
	
}
