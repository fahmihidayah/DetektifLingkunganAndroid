package com.detektiflingkuganandroid;

import com.detektiflingkuganandroid.R.color;
import com.engine.DetailUserProfileEngine;
import com.models.Constantstas;
import com.models.DataSingleton;

import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * unused class
 * @author fahmi
 *
 */
@Deprecated
@SuppressLint({ "ValidFragment", "ResourceAsColor" })
public class DetailUserProfileActivity extends FragmentActivity implements Constantstas{

	public TextView textViewStatus, textViewName ,textViewFollower, textViewFollowing;
	public Button buttonFollow, buttonLaporanKu, buttonFollowerKu;
	public ImageButton imageButtonEditStatus;
	public ImageView imageViewProfile;
	
	public FollowerKuFragment fragmentFollowerKu;
	public LaporanKuFragment fragmentLaporanKu;
	
	private DetailUserProfileEngine detailUserProfileEngine;
	
	private void initialComponent(){
		
		textViewStatus = (TextView) findViewById(R.id.textViewStatus);
		textViewName = (TextView) findViewById(R.id.textViewName);
//		textViewFollower = (TextView) findViewById(R.id.textViewFollower);
//		textViewFollowing = (TextView) findViewById(R.id.textViewFollowing);
		imageButtonEditStatus = (ImageButton) findViewById(R.id.imageButtonEdit);
		imageViewProfile = (ImageView) findViewById(R.id.imageViewProfile);
		buttonFollow = (Button) findViewById(R.id.buttonFollow);
//		buttonFollowerKu = (Button) findViewById(R.id.buttonFollowerKu);
//		buttonLaporanKu = (Button) findViewById(R.id.buttonLaporanKu);
//		detailUserProfileEngine = new DetailUserProfileEngine(this);
		detailUserProfileEngine.getUserDetail();
		
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getActionBar().setCustomView(R.layout.custom_user_detail_profile_action_bar);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setDisplayShowCustomEnabled(true);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_user_profile_activity_1);
		initialComponent();
	}
	
	public void onClickFollow(View view){
		detailUserProfileEngine.follow();
	}
	
	public void onClickEditStatus(View view){
		UpdateStatusDialog updateStatusDialog = new UpdateStatusDialog();
		updateStatusDialog.show(getSupportFragmentManager(), "update status");
	}
	

	public class UpdateStatusDialog extends DialogFragment{
	private EditText editTextStatus;
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater layoutInflater = getActivity().getLayoutInflater();
		View view = layoutInflater.inflate(R.layout.update_status_dialog, null);
		
		editTextStatus = (EditText) view.findViewById(R.id.editTextServerStatus);
		editTextStatus.setText(detailUserProfileEngine.getUser().getStatus());
		builder.setView(view)
			.setTitle("Update Status")
			.setPositiveButton("Simpan", new  DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					detailUserProfileEngine.changeStatus(editTextStatus.getText().toString());
					Toast.makeText(getActivity(), "status updated", Toast.LENGTH_LONG).show();
				}
			})
			.setNegativeButton("Batal", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int which) {
					
				}
				
			});
		return builder.create();
	}
}
	public void onClickSwitchTab(View view){
//		Fragment newFragment = null;
//		if(view == buttonLaporanKu){
//			if(fragmentLaporanKu == null){
//				fragmentLaporanKu = new LaporanKuFragment();
//			}
//			newFragment = fragmentLaporanKu;
//			buttonLaporanKu.setBackgroundResource(color.grey_act);
//			buttonFollowerKu.setBackgroundResource(color.grey_pas);
//		}
//		else {
//			if(fragmentFollowerKu == null){
//				fragmentFollowerKu  = new FollowerKuFragment();
//			}
//			newFragment = fragmentFollowerKu;
//			buttonLaporanKu.setBackgroundResource(color.grey_pas);
//			buttonFollowerKu.setBackgroundResource(color.grey_act);
//		}
//		
//		FragmentTransaction transaction = getFragmentManager().beginTransaction();
//
//		// Replace whatever is in the fragment_container view with this fragment,
//		// and add the transaction to the back stack
//		transaction.replace(R.id.frameLayoutUser, newFragment);
//		transaction.addToBackStack(null);
//
//		// Commit the transaction
//		transaction.commit();
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}

}
