package com.detektiflingkuganandroid;


import com.detektiflingkuganandroid.MainFragment.OnClickPantau;
import com.engine.DetektivUtilities;
import com.engine.ProgressDialogFactory;
import com.framework.adapter.CustomAdapter;
import com.framework.common_utilities.ViewSetterUtilities;
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

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class DiscoverFragment extends Fragment implements Constantstas{
	View rootView;
	@InjectView(R.id.listViewLaporan) ListView listViewLaporan;
	
	CustomAdapter<Laporan> customAdapter;
	List<Laporan> listLaporan = new ArrayList<Laporan>();
	
	private void initialComponent(){
		customAdapter = new CustomAdapter<Laporan>(getActivity(),R.layout.laporan_item_layout_1, listLaporan) {

			DetektivUtilities imageViewHandler;

			@Override
			public void initialComponent() {
				imageViewHandler = new DetektivUtilities(getActivity());
				super.initialComponent();
			}

			@Override
			public void setViewItems(View view, final Laporan data) {
				ImageView imageViewImageLaporan = (ImageView) view
						.findViewById(R.id.imageViewImageLaporan);
				ImageView imageViewUserProfile = (ImageView) view
						.findViewById(R.id.imageViewProfile);
				imageViewUserProfile.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						((HomeActivity)getActivity()).setFragment(new ProfileFragment(data.getUser().getIdUser()), true);
					}
				});
				imageViewHandler.getImageLoader().displayImage(data.getListImagePath().get(0).getUrlImange(),
						imageViewImageLaporan, imageViewHandler.getDisplayImageOptionsDetail());	
				imageViewHandler.getImageLoader().displayImage(data.getUser().getImageProfilePath().getUrlImange(),
						imageViewUserProfile, imageViewHandler.getDisplayImageOptionsProfile());
				ViewSetterUtilities.setTextToView(view, R.id.textViewName, data.getUser().getName());
				ViewSetterUtilities.setTextToView(view, R.id.textViewDataLaporan, data.getDataLaporan());
				// jumlah lihat
				//ViewSetterUtilities.setTextToView(view, R.id.textViewDataLaporan, data.getDataLaporan());
				ViewSetterUtilities.setTextToView(view, R.id.textViewJudulLaporan, data.getJudulLaporan());
				ViewSetterUtilities.setTextToView(view, R.id.textViewJumlahComment, data.getJumlahKomentar() + "");
				ViewSetterUtilities.setTextToView(view, R.id.textViewJumlahPatau, data.getJumlahUserPemantau() + "");
				ViewSetterUtilities.setTextToView(view, R.id.textViewJumlahLihat, data.getViewer() +"");
				ViewSetterUtilities.getImageView(view, R.id.imageButtonShare)
					.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							
						}
					});
				ViewSetterUtilities.getImageView(view, R.id.imageButtonLihat)
				.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						((HomeActivity)getActivity()).setFragment(new DetailLaporanFragment(data), true);
					}
				});
				ViewSetterUtilities.getImageView(view, R.id.imageButtonComment)
				.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						((HomeActivity)getActivity()).setFragment(new DetailLaporanFragment(data), true);
					}
				});
				
				final ImageButton imageButtonPantau = (ImageButton) ViewSetterUtilities.getImageView(view, R.id.imageButtonPantau);
				if(data.isPantau()){
					imageButtonPantau.setImageResource(R.drawable.ic_pantau_act);
				}
				else {
					imageButtonPantau.setImageResource(R.drawable.ic_pantau);
				}
				imageButtonPantau.setOnClickListener(new OnClickPantau(data));
				
				
			}
		};
		listViewLaporan.setAdapter(customAdapter);
		requestListDiscoverLaporan();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.discover_fragment, null);
		ButterKnife.inject(this, rootView);
		initialComponent();
		return rootView;
	}
	
	public void requestListDiscoverLaporan(){
		RequestParams params = new RequestParams();
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		params.put("userId", DataSingleton.getInstance().getUser().getIdUser() + "");
		params.put("type", "d");
		final ProgressDialogFactory dialogFactory = new ProgressDialogFactory(getActivity());
		MyRestClient.post(API_LIST_LAPORAN, params, new JsonHttpResponseHandler(){
			@Override
			public void onProgress(int bytesWritten, int totalSize) {
				dialogFactory.show("Loadlaporan", false);
			}
			
			@Override
			public void onSuccess(JSONObject response) {
				ListLaporanResponse listLaporanResponse = new Gson().fromJson(response.toString(), ListLaporanResponse.class);
				listLaporan.clear();
				listLaporan.addAll(listLaporanResponse.getData());
				customAdapter.notifyDataSetChanged();
				dialogFactory.dismiss();
			}
			
			@Override
			public void onFailure(Throwable e, JSONObject errorResponse) {
				dialogFactory.dismiss();
			}
		});
	}
	
public class OnClickPantau implements View.OnClickListener {
		
	Laporan data;
		public OnClickPantau(Laporan data) {
			super();
			this.data = data;
		}
		@Override
		public void onClick(View v) {
			String api;
			if(data.isPantau()){
				 api = API_UNPANTAU;
			}
			else {
				api = API_PANTAU;
			}
			RequestParams params = new RequestParams();
			params.put("userId", DataSingleton.getInstance().getUser().getIdUser() + "");
			params.put("laporanId", data.getIdLaporan() + "");
			params.put("authKey", DataSingleton.getInstance().getAuthKey());
			Toast.makeText(getActivity(), api + params.toString(), Toast.LENGTH_LONG).show();
			MyRestClient.post(api, params, new JsonHttpResponseHandler(){
				@Override
				public void onSuccess(JSONObject response) {
					LaporanResponse laporanResponse = new Gson().fromJson(response.toString(), LaporanResponse.class);
//					LaporanHelper laporanHelper = listLaporan.get(listLaporan.indexOf(laporanResponse.getData()));
					data.setPantau(laporanResponse.getData().isPantau());
					data.setJumlahUserPemantau(laporanResponse.getData().getJumlahUserPemantau());
					DataSingleton.getInstance().saveToFile(getActivity());
					customAdapter.notifyDataSetChanged();
					
					Toast.makeText(getActivity(), "pantau", Toast.LENGTH_LONG).show();
				}
				@Override
				public void onFailure(Throwable e, JSONObject errorResponse) {
					Toast.makeText(getActivity(), "error", Toast.LENGTH_LONG).show();
				}
			});
		}
	}

}
