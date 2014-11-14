package com.engine;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.content.Intent;
import android.widget.Toast;

import com.detektiflingkuganandroid.DetailLaporanActivity;
import com.detektiflingkuganandroid.LoginActivity;
import com.detektiflingkuganandroid.MainActivity;
import com.detektiflingkuganandroid.MainFragment;
import com.framework.rest_clients.MyRestClient;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.models.Constantstas;
import com.models.DataSingleton;
import com.models.Laporan;
import com.models.LaporanHelper;
import com.response.LaporanResponse;
import com.response.ListLaporanResponse;

public class MainEngine implements Constantstas {
	private MainFragment mainActivity;
	private List<LaporanHelper> listLaporan = DataSingleton.getInstance().getListDataLaporan();

	public MainEngine(MainFragment mainActivity) {
		super();
		this.mainActivity = mainActivity;
	}

	public void requestLaporan(String type) {
		RequestParams params = new RequestParams();
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		String idLaporan = "";
		if(!type.equalsIgnoreCase("f")){
			if(listLaporan.size() > 0){
				if(type.equalsIgnoreCase("h")){
					idLaporan = listLaporan.get(0).getId() + "";	
				}else {
					idLaporan = listLaporan.get(listLaporan.size() - 1).getId() + "";
				}
				
			}
		}
		params.put("idLaporan", idLaporan);
		params.put("idUser", DataSingleton.getInstance().getUser().getId() + "");
		params.put("type", type);
//		Toast.makeText(mainActivity, "auth : " + DataSingleton.getInstance().getAuthKey() + "\n"  + 
//		"idLaporan " + idLaporan + "\ntype : " + type, Toast.LENGTH_LONG).show();
		MyRestClient.post(API_LIST_LAPORAN, params,
				new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONObject response) {
						ListLaporanResponse listLaporanResponse = new Gson().fromJson(response.toString(), ListLaporanResponse.class);
						listLaporan.addAll(listLaporanResponse.getData());
						Toast.makeText(mainActivity.getActivity(), "Data updated " + listLaporanResponse.getData().size(), Toast.LENGTH_LONG).show();
						mainActivity.mSwipeRefreshLayout.setRefreshing(false);
						mainActivity.customAdapter.notifyDataSetChanged();
					}

					@Override
					public void onFailure(Throwable e, JSONObject errorResponse) {
						mainActivity.mSwipeRefreshLayout.setRefreshing(false);
						Toast.makeText(mainActivity.getActivity(), "Error update data", Toast.LENGTH_LONG).show();
					}
				});
	}
	
	public List<LaporanHelper> getListLaporan() {
		return listLaporan;
	}
	
	public void logout(){
		RequestParams params = new RequestParams();
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		MyRestClient.post(API_LOGOUT, params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject response) {
				Toast.makeText(mainActivity.getActivity(),"logout", Toast.LENGTH_LONG).show();
				DataSingleton.getInstance().setAuthKey("");
				DataSingleton.getInstance().getListDataLaporan().clear();
				DataSingleton.getInstance().setUser(null);
				DataSingleton.getInstance().setLogin(false);
				DataSingleton.getInstance().saveToFile(mainActivity.getActivity());
				mainActivity.startActivity(new Intent(mainActivity.getActivity(), LoginActivity.class));
				mainActivity.getActivity().finish();
			}
			
			@Override
			public void onFailure(Throwable e, JSONObject errorResponse) {
				Toast.makeText(mainActivity.getActivity(),"error", Toast.LENGTH_LONG).show();
			}
		});
	}
	
	public void pantau(final Laporan data, String api){
		RequestParams params = new RequestParams();
		params.put("idUser", DataSingleton.getInstance().getUser().getId() + "");
		params.put("idLaporan", data.getId() + "");
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		Toast.makeText(mainActivity.getActivity(), api + params.toString(), Toast.LENGTH_LONG).show();
		MyRestClient.post(api, params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject response) {
				LaporanResponse laporanResponse = new Gson().fromJson(response.toString(), LaporanResponse.class);
//				LaporanHelper laporanHelper = listLaporan.get(listLaporan.indexOf(laporanResponse.getData()));
				data.setPantau(laporanResponse.getData().isPantau());
				data.setJumlahUserPemantau(laporanResponse.getData().getJumlahUserPemantau());
				DataSingleton.getInstance().saveToFile(mainActivity.getActivity());
				mainActivity.customAdapter.notifyDataSetChanged();
				
				Toast.makeText(mainActivity.getActivity(), "pantau", Toast.LENGTH_LONG).show();
			}
			@Override
			public void onFailure(Throwable e, JSONObject errorResponse) {
				Toast.makeText(mainActivity.getActivity(), "error", Toast.LENGTH_LONG).show();
			}
		});
	}
	
	public void showDetailLaporan(Laporan data){
		Intent intent = new Intent(mainActivity.getActivity(), DetailLaporanActivity.class);
		intent.putExtra("laporan", data);
		mainActivity.startActivity(intent);
	}
}
