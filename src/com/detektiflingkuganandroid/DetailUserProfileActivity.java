package com.detektiflingkuganandroid;

import com.engine.DetailUserProfileEngine;
import com.models.Constantstas;
import com.models.DataSingleton;

import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
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
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class DetailUserProfileActivity extends FragmentActivity implements Constantstas{

	public TextView textViewStatus, textViewName ,textViewFollowerFollowing;;
	public Button buttonFollow;
	public ImageButton imageButtonEditStatus;

	private DetailUserProfileEngine detailUserProfileEngine;
	
	private void initialComponent(){
		
		textViewStatus = (TextView) findViewById(R.id.textViewStatus);
		textViewName = (TextView) findViewById(R.id.textViewName);
		textViewFollowerFollowing = (TextView) findViewById(R.id.textViewFollowerAndFollowing);
		imageButtonEditStatus = (ImageButton) findViewById(R.id.imageButtonEdit);
		buttonFollow = (Button) findViewById(R.id.buttonFollow);
		detailUserProfileEngine = new DetailUserProfileEngine(this);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_user_profile_activity);
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

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}

}
