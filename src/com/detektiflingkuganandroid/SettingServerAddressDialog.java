package com.detektiflingkuganandroid;

import com.models.DataSingleton;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SettingServerAddressDialog extends DialogFragment{
	private EditText editTextServerAddress;
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater layoutInflater = getActivity().getLayoutInflater();
		View view = layoutInflater.inflate(R.layout.setting_server_address_dialog, null);
		
		editTextServerAddress = (EditText) view.findViewById(R.id.editTextServerAddress);
		editTextServerAddress.setText(DataSingleton.getInstance().getServerAddress());
		builder.setView(view)
			.setTitle("Setting Server")
			.setPositiveButton("Simpan", new  DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					DataSingleton.getInstance().setServerAddress(editTextServerAddress.getText().toString());
					DataSingleton.getInstance().saveToFile(getActivity());
					Toast.makeText(getActivity(), "Server disetting", Toast.LENGTH_LONG).show();
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
