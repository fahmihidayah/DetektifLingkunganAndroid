package com.detektiflingkuganandroid;

import java.util.ArrayList;

import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.engine.DetektivUtilities;
import com.framework.adapter.CustomAdapter;
import com.framework.common_utilities.ViewSetterUtilities;
import com.framework.rest_clients.MyRestClient;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.models.Constantstas;
import com.models.DataSingleton;
import com.models.Komentar;
import com.models.Laporan;
import com.models.LaporanHelper;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.response.KomentarResponse;
import com.response.LaporanResponse;
import com.response.ListKomentarResponse;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class DetailLaporanFragment extends Fragment implements Constantstas {

	@InjectView(R.id.imageViewProfile) public ImageView imageViewProfile;
	@InjectView(R.id.imageViewImageLaporan) public ImageView imageViewLaporan;
	@InjectView(R.id.textViewName) public TextView textViewUserName;
	@InjectView(R.id.textViewDataLaporan) public TextView textViewDataLaporan;
	@InjectView(R.id.textViewTime) public TextView textViewTime;
	@InjectView(R.id.textViewJudulLaporan) public TextView textViewJudulLaporan;
	
	@InjectView(R.id.listViewKomentar) public ListView listViewKomentar;
	@InjectView(R.id.buttonComment) public Button buttonComment;
	@InjectView(R.id.buttonPantau) public Button buttonPantau;
	@InjectView(R.id.editTextComment) public EditText editTextComment;
	
	@OnClick(R.id.buttonComment)
	public void onClickComment(Button button){
		RequestParams params = new RequestParams();
		params.put("laporanId", laporan.getIdLaporan() + "");
		params.put("dataKomentar", editTextComment.getText().toString());
		params.put("userId", DataSingleton.getInstance().getUser().getIdUser() + "");
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		MyRestClient.post(API_INSERT_KOMENTAR, params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject response) {
				KomentarResponse komentarResponse = new Gson().fromJson(response.toString(), KomentarResponse.class);
				listKomentar.add(komentarResponse.getData());
				editTextComment.setText("");
				customAdapter.notifyDataSetChanged();
			}
			
		});
	}
	
	@OnClick(R.id.buttonPantau)
	public void onClickPantau(Button button){
		String api = (laporan.isPantau() ? API_UNPANTAU: API_PANTAU); 
		RequestParams params = new RequestParams();
		params.put("userId", DataSingleton.getInstance().getUser().getIdUser() + "");
		params.put("laporanId", laporan.getIdLaporan() + "");
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		MyRestClient.post(api, params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject response) {
				LaporanResponse laporanResponse = new Gson().fromJson(response.toString(), LaporanResponse.class);
				laporan.setPantau(laporanResponse.getData().isPantau());
				laporan.setJumlahUserPemantau(laporanResponse.getData().getJumlahUserPemantau());
				DataSingleton.getInstance().saveToFile(DetailLaporanFragment.this.getActivity());
				if(laporan.isPantau()){
					Drawable img = DetailLaporanFragment.this.getActivity().getResources().getDrawable( R.drawable.ic_centang );
					buttonPantau.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
				}
				else {
					buttonPantau.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
				}
				Toast.makeText(DetailLaporanFragment.this.getActivity(), "pantau", Toast.LENGTH_LONG).show();
			}
			@Override
			public void onFailure(Throwable e, JSONObject errorResponse) {
				Toast.makeText(DetailLaporanFragment.this.getActivity(), "error", Toast.LENGTH_LONG).show();
			}
		});
	}
	
	DetektivUtilities imageViewHandler;
	
	public CustomAdapter<Komentar> customAdapter;
	public View rootView;
	public LaporanHelper laporan;
	private ArrayList<Komentar> listKomentar = new ArrayList<Komentar>();


	public DetailLaporanFragment(LaporanHelper laporan) {
		this.laporan = laporan;
	}

	private void initialComponent() {
		imageViewHandler = new DetektivUtilities(getActivity());
		View customActionBar = getActivity().getLayoutInflater().inflate(
				R.layout.custom_detail_laporan_action_bar, null);
		getActivity().getActionBar().setDisplayShowHomeEnabled(false);
		getActivity().getActionBar().setDisplayShowTitleEnabled(false);
		getActivity().getActionBar().setDisplayShowCustomEnabled(true);
		getActivity().getActionBar().setCustomView(customActionBar);
		ViewHolder viewHolder = new ViewHolder(customActionBar);
		customActionBar.setTag(viewHolder);
		
		customAdapter = new CustomAdapter<Komentar>(getActivity(), R.layout.komentar_item_layout, listKomentar ) {
			
			@Override
			public void setViewItems(View view, Komentar data) {
				ImageView imageViewProfile = (ImageView) view.findViewById(R.id.imageViewProfile);
				imageViewHandler.getImageLoader().displayImage(data.getUser().getImageProfilePath().getUrlImange(),imageViewProfile, imageViewHandler.getDisplayImageOptionsProfile());
				ViewSetterUtilities.setTextToView(view, R.id.textViewUserKomentar, data.getUser().getName());
				ViewSetterUtilities.setTextToView(view, R.id.textViewKomentarUser, data.getDataKomentar());
			}
		};
		
		listViewKomentar.setAdapter(customAdapter);
		
		// di asumsikan setiap image memiliki 1 gambar
		textViewUserName.setText(laporan.getUser().getName());
		textViewDataLaporan.setText(laporan.getDataLaporan());
		textViewJudulLaporan.setText(laporan.getJudulLaporan());
		
		imageViewHandler.getImageLoader().displayImage(laporan.getUser().getImageProfilePath().getUrlImange(),imageViewProfile, imageViewHandler.getDisplayImageOptionsProfile());
		imageViewHandler.getImageLoader().displayImage(laporan.getListImagePath().get(0).getUrlImange(),imageViewLaporan, imageViewHandler.getDisplayImageOptionsDetail());
		requestListKomentar();
		view();
		if(laporan.isPantau()){
			Drawable img = getActivity().getResources().getDrawable( R.drawable.ic_centang );
			buttonPantau.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
		}
		else {
			buttonPantau.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.detail_laporan_fragment, null);
		ButterKnife.inject(this, rootView);
		initialComponent();
		return rootView;
	}
	
	public void requestListKomentar(){
		RequestParams params = new RequestParams();
		params.put("laporanId", laporan.getIdLaporan()+"");
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		MyRestClient.post(API_LIST_KOMENTAR, params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject response) {
				ListKomentarResponse listKomentarResponse  = new Gson().fromJson(response.toString(), ListKomentarResponse.class);
				listKomentar.clear();
				listKomentar.addAll(listKomentarResponse.getData());
				customAdapter.notifyDataSetChanged();
			}
		});
	}
	
	public void view(){
		RequestParams params = new RequestParams();
		params.put("laporanId", laporan.getIdLaporan() + "");
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		MyRestClient.post(API_VIEW, params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject response) {
			}
			@Override
			public void onFailure(Throwable e, JSONObject errorResponse) {
			}
		});
	}
	
	public class ViewHolder{
		@OnClick(R.id.imageButtonBack)
		public void onClickBack(View view){
			getActivity().onBackPressed();
		}
		public ViewHolder(View view){
			ButterKnife.inject(this, view);
		}
	}

}
