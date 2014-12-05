package com.detektiflingkuganandroid;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.adapter.CustomInjectedAdapter;
import com.engine.DetektivUtilities;
import com.framework.adapter.CustomAdapter;
import com.framework.common_utilities.ViewSetterUtilities;
import com.framework.rest_clients.MyRestClient;
import com.google.android.gms.internal.ho;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.models.Constantstas;
import com.models.DataSingleton;
import com.models.PrivateMessage;
import com.models.User;
import com.response.ListUserResponse;

import butterknife.ButterKnife;
import butterknife.InjectView;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListFollowerFragment extends Fragment implements Constantstas {

	@InjectView(R.id.listViewListFollower)
	ListView listViewListFollower;
//	CustomInjectedAdapter<User> customInjectedAdapter;
	CustomAdapter<User> customAdapter;

	List<User> listUser = new ArrayList<User>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.list_follower_fragment, null);
		ButterKnife.inject(this, rootView);

		customAdapter = new CustomAdapter<User>(getActivity(), R.layout.user_item_layout, listUser) {
			DetektivUtilities imageViewHandler;
			@Override
			public void initialComponent() {
				imageViewHandler = new DetektivUtilities(getContext());
				super.initialComponent();
			}
			@Override
			public void setViewItems(View view, User data) {
				ImageView imageViewProfileUser = (ImageView) view.findViewById(R.id.imageViewProfileUser);
				imageViewHandler.getImageLoader().displayImage(data.getImageProfilePath().getUrlImange(), imageViewProfileUser, imageViewHandler.getDisplayImageOptionsProfile());
				ViewSetterUtilities.setTextToView(view, R.id.textViewNameUser, data.getName());
			}
		};
		
		
		listViewListFollower.setAdapter(customAdapter);
		listViewListFollower.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				PrivateMessage privateMessage = new PrivateMessage();
				privateMessage.setIdPrivateMessage(listUser.get(position).getIdUser());
				privateMessage.setUser(listUser.get(position));
				privateMessage.isOld = true;
//				Toast.makeText(getActivity(), "index " + privateMessage.getUserChat().getIdUser(), 1000).show();
				((HomeActivity)getActivity()).setFragment(new PrivateMessageUserFragment(privateMessage), true);
			}
		});
		requestFollower();
		return rootView;
	}

	public void requestFollower() {
		
		RequestParams params = new RequestParams();
		params.put("userId", DataSingleton.getInstance().getUser().getIdUser()
				+ "");
		params.put("mode", "0");
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		MyRestClient.post(API_GET_FOLLOWER, params, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject response) {
				ListUserResponse listUserResponse = new Gson().fromJson(
						response.toString(), ListUserResponse.class);
				listUser.clear();
				listUser.addAll(listUserResponse.getData());
				customAdapter.notifyDataSetChanged();
			}
			@Override
			public void onFailure(Throwable e, JSONObject errorResponse) {
				Toast.makeText(getActivity(), "error", 1000).show();
			}
		});
	}

	static class ViewHolder {
		@InjectView(R.id.imageViewProfileUser)
		ImageView imageViewProfileUser;
		@InjectView(R.id.textViewNameUser)
		TextView textViewNameUser;

		public ViewHolder(View view) {
			ButterKnife.inject(this, view);
		}
	}
}
