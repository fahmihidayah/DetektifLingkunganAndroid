package com.detektiflingkuganandroid;

import java.util.List;

import org.json.JSONObject;

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
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.response.LaporanResponse;
import com.response.ListLaporanResponse;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainFragment extends Fragment implements Constantstas,
		OnRefreshListener {

	private List<LaporanHelper> listLaporan = DataSingleton.getInstance().getListDataLaporan();
	public View rootView;
	private static final int SELECT_PICTURE = 1;
	private static int TAKE_PICTURE = 5;
	

	private String selectedImagePath = "";
	public SwipeRefreshLayout mSwipeRefreshLayout;
	public ListView listViewLaporan;
	public CustomAdapter<LaporanHelper> customAdapter;

	private void initialComponent() {
		mSwipeRefreshLayout = (SwipeRefreshLayout) rootView
				.findViewById(R.id.swipe_container);
		mSwipeRefreshLayout.setOnRefreshListener(this);

		listViewLaporan = (ListView) rootView
				.findViewById(R.id.listViewLaporan);
		customAdapter = new CustomAdapter<LaporanHelper>(getActivity(),
				R.layout.laporan_item_layout_1, listLaporan) {

			DetektivUtilities imageViewHandler;

			@Override
			public void initialComponent() {
				imageViewHandler = new DetektivUtilities(getActivity());
				super.initialComponent();
			}

			@Override
			public void setViewItems(View view, final LaporanHelper data) {
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
				imageViewImageLaporan.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						((HomeActivity)getActivity()).setFragment(new ZoomImageFragment(data.getListImagePath().get(0).getUrlImange()), true);
					}
				});
				
			}
		};
		listViewLaporan.setAdapter(customAdapter);

		listViewLaporan.setOnScrollListener(new AbsListView.OnScrollListener() {
			private int preLast;

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				switch (view.getId()) {
				case android.R.id.list:

					// Make your calculation stuff here. You have all your
					// needed info from the parameters of this function.

					// Sample calculation to determine if the last
					// item is fully visible.
					final int lastItem = firstVisibleItem + visibleItemCount;
					if (lastItem == totalItemCount) {
						requestLaporan(OLDEST);
						// if(preLast!=lastItem){ //to avoid multiple calls for
						// last item
						//
						// preLast = lastItem;
						// }
					}
				}
			}
		});
		requestLaporan(FIRST);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.main_activity, null);
		initialComponent();
		return rootView;
	}

	@Override
	public void onRefresh() {
		requestLaporan(NEWEST);
	}

	
	/**
	 * inner class
	 */
	
	public class OnClickPantau implements View.OnClickListener {
		
		LaporanHelper data;
		public OnClickPantau(LaporanHelper data) {
			super();
			this.data = data;
		}
		@Override
		public void onClick(View v) {
			if(data.isPantau()){
				pantau(data, API_UNPANTAU);
			}
			else {
				pantau(data, API_PANTAU);
			}
		}
	}
	
	public void requestLaporan(final String type) {
		RequestParams params = new RequestParams();
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		String idLaporan = "";
		if(!type.equalsIgnoreCase("f")){
			if(listLaporan.size() > 0){
				if(type.equalsIgnoreCase("h")){
					idLaporan = listLaporan.get(0).getIdLaporan() + "";	
				}else {
					idLaporan = listLaporan.get(listLaporan.size() - 1).getIdLaporan() + "";
				}
				
			}
		}
		params.put("laporanId", idLaporan);
		params.put("userId", DataSingleton.getInstance().getUser().getIdUser() + "");
		params.put("type", type);
		final ProgressDialogFactory dialogFactory = new ProgressDialogFactory(getActivity());
		MyRestClient.post(API_LIST_LAPORAN, params,
				new JsonHttpResponseHandler() {
			
					@Override
					public void onProgress(int bytesWritten, int totalSize) {
						dialogFactory.show("Load laporan...", false);
					}
					@Override
					public void onSuccess(JSONObject response) {
						ListLaporanResponse listLaporanResponse = new Gson().fromJson(response.toString(), ListLaporanResponse.class);
						if(type.equalsIgnoreCase(FIRST)){
							listLaporan.clear();
							listLaporan.addAll(listLaporanResponse.getData());	
						}
						else if(type.equalsIgnoreCase(NEWEST)){
							List<LaporanHelper> listLaporanTemp = listLaporanResponse.getData();
							int i = 0;
							for (LaporanHelper laporan : listLaporanTemp) {
								listLaporan.add(i, laporan);
								i++;
							}
						}
						
						Toast.makeText(getActivity(), "Data updated " + listLaporanResponse.getData().size(), Toast.LENGTH_LONG).show();
						mSwipeRefreshLayout.setRefreshing(false);
						customAdapter.notifyDataSetChanged();
						dialogFactory.dismiss();
					}

					@Override
					public void onFailure(Throwable e, JSONObject errorResponse) {
						mSwipeRefreshLayout.setRefreshing(false);
						Toast.makeText(getActivity(), "Error update data", Toast.LENGTH_LONG).show();
						dialogFactory.dismiss();
					}
				});
	}
	

	public void pantau(final Laporan data, String api){
		RequestParams params = new RequestParams();
		params.put("userId", DataSingleton.getInstance().getUser().getIdUser() + "");
		params.put("laporanId", data.getIdLaporan() + "");
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		final ProgressDialogFactory dialogFactory = new ProgressDialogFactory(getActivity());
		MyRestClient.post(api, params, new JsonHttpResponseHandler(){
			@Override
			public void onProgress(int bytesWritten, int totalSize) {
				dialogFactory.show("Loading", false);
			}
			@Override
			public void onSuccess(JSONObject response) {
				LaporanResponse laporanResponse = new Gson().fromJson(response.toString(), LaporanResponse.class);
//				LaporanHelper laporanHelper = listLaporan.get(listLaporan.indexOf(laporanResponse.getData()));
				data.setPantau(laporanResponse.getData().isPantau());
				data.setJumlahUserPemantau(laporanResponse.getData().getJumlahUserPemantau());
				DataSingleton.getInstance().saveToFile(getActivity());
				customAdapter.notifyDataSetChanged();
				dialogFactory.dismiss();
			}
			@Override
			public void onFailure(Throwable e, JSONObject errorResponse) {
				Toast.makeText(getActivity(), "error", Toast.LENGTH_LONG).show();
				dialogFactory.dismiss();
			}
		});
	}
}
