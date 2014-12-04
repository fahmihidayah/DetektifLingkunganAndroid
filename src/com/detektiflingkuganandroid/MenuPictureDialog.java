package com.detektiflingkuganandroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;

public class MenuPictureDialog extends DialogFragment{
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    builder
	           .setItems(R.array.menu_gambar, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int which) {
	            	   switch (which) {
					case 0:
						Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
						 
				        // start camera activity
				        startActivityForResult(intent, 1000);
						break;
					case 1:
						  Intent i = new Intent(
			                        Intent.ACTION_PICK,
			                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			                 
			                startActivityForResult(i, 2000);
						break;
					case 2:
						break;
					default:
						break;
					}
	           }
	    });
	    return builder.create();
	}
}
