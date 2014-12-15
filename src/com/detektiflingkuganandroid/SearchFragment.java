package com.detektiflingkuganandroid;


import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.detektiflingkuganandroid.HomeFragment.ViewHolder;
import com.engine.ProgressDialogFactory;
import com.framework.common_utilities.ViewSetterUtilities;
import com.framework.rest_clients.MyRestClient;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.models.Constantstas;
import com.models.DataSingleton;
import com.response.ListLaporanResponse;
import com.response.ListUserResponse;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchFragment extends Fragment implements Constantstas{
	
	View rootView;
	
	@InjectView(R.id.buttonLaporan)
	Button buttonLaporan;
	@InjectView(R.id.buttonUser)
	Button buttonUser;
	
	@OnClick(R.id.buttonLaporan)
	public void onClickLaporan(View view){
		type = "laporan";
		buttonUser.setBackgroundResource(R.color.dark_menu);
		buttonLaporan.setBackgroundResource(R.color.red_act);
	}
	
	@OnClick(R.id.buttonUser)
	public void onClickUser(View view){
		type = "user";
		
		buttonUser.setBackgroundResource(R.color.red_act);
		buttonLaporan.setBackgroundResource(R.color.dark_menu);
	}
	
	private String type = "laporan";
	
	private void initialComponent(){
		View customActionBar = getActivity().getLayoutInflater().inflate(
				R.layout.custom_search_action_bar, null);
		getActivity().getActionBar().setDisplayShowHomeEnabled(false);
		getActivity().getActionBar().setDisplayShowTitleEnabled(false);
		getActivity().getActionBar().setDisplayShowCustomEnabled(true);
		getActivity().getActionBar().setCustomView(customActionBar);
		ViewHolder viewHolder = new ViewHolder(customActionBar);
		customActionBar.setTag(viewHolder);
		onClickLaporan(null);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.search_fragment, null);
		ButterKnife.inject(this, rootView);
		initialComponent();
		return rootView;
	}
	
	public class ViewHolder{
		
		@InjectView(R.id.editTextSearch)
		EditText editTextSearch;
		
		@OnClick(R.id.imageButtonBack)
		public void onClickBack(View view){
			getActivity().onBackPressed();
		}
		
		@OnClick(R.id.imageButtonSearch)
		public void onClikcSaerch(View view){
			RequestParams params = new RequestParams();
			params.put("key_word", editTextSearch.getText().toString());
			params.put("type", type);
			params.put("userId",DataSingleton.getInstance().getUser().getIdUser() + "");
			params.put("authKey", DataSingleton.getInstance().getAuthKey());
			final ProgressDialogFactory dialogFactory = new ProgressDialogFactory(getActivity());
			dialogFactory.show("Loading...", false);
			MyRestClient.post(API_FIND, params, new JsonHttpResponseHandler(){
				@Override
				public void onProgress(int bytesWritten, int totalSize) {
					
				}
				
				@Override
				public void onSuccess(JSONObject response) {
					if(type.equalsIgnoreCase("laporan")){
						ListLaporanResponse listLaporanResponse = new Gson().fromJson(response.toString(), ListLaporanResponse.class);
						Toast.makeText(getActivity(), "laporan", Toast.LENGTH_LONG).show();
						setFragment(new ResultSearchFragment(listLaporanResponse.getData(), "laporan"));
					}
					else {
						ListUserResponse listUserResponse = new Gson().fromJson(response.toString(), ListUserResponse.class);
						Toast.makeText(getActivity(), "user", Toast.LENGTH_LONG).show();
						setFragment(new ResultSearchFragment(listUserResponse.getData(), "user"));
					}
					dialogFactory.dismiss();
				}
				
				@Override
				public void onFailure(Throwable e, JSONObject errorResponse) {
					Toast.makeText(getActivity(), "error", Toast.LENGTH_LONG).show();
					dialogFactory.dismiss();
				}
			});
		}
		
		
		public ViewHolder(View view){
			ButterKnife.inject(this, view);
		}
	}
	
	public void setFragment(Fragment fragment) {
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.frameLayoutSearchResult, fragment);
		transaction.commit();
	}

}
