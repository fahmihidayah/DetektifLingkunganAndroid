package com.detektiflingkuganandroid;

import org.json.JSONObject;

import com.framework.rest_clients.MyRestClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.models.Constantstas;
import com.models.DataSingleton;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import butterknife.ButterKnife;


public class HomeActivity extends FragmentActivity implements Constantstas{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity_1);
		ButterKnife.inject(this);
		View customActionBar = getLayoutInflater().inflate(R.layout.custom_list_user_action_bar, null);
        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setDisplayShowCustomEnabled(true);
        getActionBar().setCustomView(customActionBar);
        setFragment(new HomeFragment(), false);
	}
	public void setFragment(Fragment fragment, boolean isBackStack) {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		if(isBackStack){
			transaction.addToBackStack(null);
		}
		transaction.replace(R.id.frameLayoutMain, fragment);
		transaction.commit();
	}
	
	public class ViewHolder{

		public ViewHolder(View view) {
			ButterKnife.inject(this, view);
		}
}
  @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.logout_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.logout:
			logout();
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}
	
	public void logout(){
		RequestParams params = new RequestParams();
		params.put("authKey", DataSingleton.getInstance().getAuthKey());
		MyRestClient.post(API_LOGOUT, params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject response) {
				Toast.makeText(HomeActivity.this,"logout", Toast.LENGTH_LONG).show();
				DataSingleton.getInstance().setAuthKey("");
				DataSingleton.getInstance().getListDataLaporan().clear();
				DataSingleton.getInstance().setUser(null);
				DataSingleton.getInstance().setLogin(false);
				DataSingleton.getInstance().saveToFile(HomeActivity.this);
				startActivity(new Intent(HomeActivity.this, LoginActivity.class));
				finish();
			}
			
			@Override
			public void onFailure(Throwable e, JSONObject errorResponse) {
				Toast.makeText(HomeActivity.this,"error", Toast.LENGTH_LONG).show();
			}
		});
	}
	
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
//		Toast.makeText(this, "data",Toast.LENGTH_LONG).show();
		super.onActivityResult(arg0, arg1, arg2);
	};
}
