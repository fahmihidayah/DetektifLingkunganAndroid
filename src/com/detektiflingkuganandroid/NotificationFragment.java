package com.detektiflingkuganandroid;


import java.util.ArrayList;
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
import com.models.Notif;
import com.response.ListNotificaionResponse;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

public class NotificationFragment extends Fragment implements Constantstas{
	
	@InjectView(R.id.listViewNotifications) ListView listViewNotifications;
	
	@OnItemClick(R.id.listViewNotifications)
	public void onItemClickListViewNotification(int index){
		
	}
	
	List<Notif> listNotifs  = new ArrayList<Notif>();
	CustomAdapter<Notif> customAdapter;
	
	
	
	private void initialComponent(){
		customAdapter = new CustomAdapter<Notif>(getActivity(), R.layout.item_layout_notification, listNotifs) {
			
			DetektivUtilities detektivUtilities;
			@Override
			public void initialComponent() {
				detektivUtilities = new DetektivUtilities(getContext());
				super.initialComponent();
			}
			
			@Override
			public void setViewItems(View view, Notif data) {
				ViewSetterUtilities.setTextToView(view, R.id.textViewInfoNotif, data.getNotifInfo());
				ImageView imageView = (ImageView) view.findViewById(R.id.imageViewProfile);
				detektivUtilities.getImageLoader().displayImage(data.getUser().getImageProfilePath().getUrlImange(), imageView,
						detektivUtilities.getDisplayImageOptionsProfile());
			}
		};
		
		listViewNotifications.setAdapter(customAdapter);
		requestListNotification();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.notification_fragment, null);
		ButterKnife.inject(this,rootView);
		initialComponent();
		return rootView;
	}
	
	public void requestListNotification(){
		RequestParams params = new RequestParams();
		
		params.put("userId", DataSingleton.getInstance().getUser().getIdUser() + "");
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		
		final ProgressDialogFactory dialogFactory = new ProgressDialogFactory(getActivity());
		MyRestClient.post(API_GET_LIST_NOTIFICATION, params, new JsonHttpResponseHandler(){
			@Override
			public void onProgress(int bytesWritten, int totalSize) {
				dialogFactory.show("loading ", false);
				super.onProgress(bytesWritten, totalSize);
			}
			@Override
			public void onSuccess(JSONObject response) {
				ListNotificaionResponse listNotificaionResponse = new Gson().fromJson(response.toString(), ListNotificaionResponse.class);
				listNotifs.clear();
				listNotifs.addAll(listNotificaionResponse.getData());
				customAdapter.notifyDataSetChanged();
				dialogFactory.dismiss();
			}
			
			@Override
			public void onFailure(Throwable e, JSONObject errorResponse) {
				dialogFactory.dismiss();
			}
		});
	}

}
